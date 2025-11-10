package filter;

import dao.DaoException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchesException;

import java.io.IOException;

@WebFilter("/*")
public class ExceptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        try {

            chain.doFilter(request, response);
        } catch (Exception ex) {
            exceptionHandle(ex, req, resp);
        }

    }


    private void exceptionHandle(Exception ex, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (ex instanceof MatchesException) {
            redirect(req, resp, HttpServletResponse.SC_NOT_FOUND, "Not found");
        } else if (ex instanceof DaoException) {
            redirect(req, resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Dao error");
        } else {
            redirect(req, resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
        }
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp,
                          int code, String message) throws ServletException, IOException {
        req.setAttribute("errorCode", code);
        req.setAttribute("errorMessage", message);


            req.getRequestDispatcher("/error.jsp").forward(req, resp);



    }
}
