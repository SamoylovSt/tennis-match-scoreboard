package filter;

import jakarta.persistence.EntityTransaction;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import util.JPAUtil;

import java.io.IOException;

@WebFilter("/*")

public class EntityManagerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();
        if (path.startsWith("/css/") || path.startsWith("/js/") ||
                path.startsWith("/images/") || path.endsWith(".ico")) {
            chain.doFilter(request, response);
            return;
        }

        EntityTransaction transaction = null;
        boolean transactionActive = false;

        try {
            JPAUtil.createEntityManagerForRequest();

            transaction = JPAUtil.getEntityManager().getTransaction();
            transaction.begin();
            transactionActive = true;

            chain.doFilter(request, response);

            if (transactionActive && transaction.isActive()) {
                transaction.commit();
                System.out.println("TRANSACTION COMMITTED SUCCESSFULLY");
                transactionActive = false;
            }
        } catch (Exception e) {
            if (transactionActive && transaction != null && transaction.isActive()){
               try{
                transaction.rollback();
               }catch (Exception rollbackEx){
                   rollbackEx.printStackTrace();
               }
            }
            throw new ServletException("Request processing failed", e);
        }finally {
            try{
                if(transactionActive && transaction != null && transaction.isActive()){
                    transaction.rollback();
                }
            }finally {
                JPAUtil.closeEntityManager();
            }
        }

    }
}
