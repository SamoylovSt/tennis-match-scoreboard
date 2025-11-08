package util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HibernateListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        JPAUtil.initialize(emf);
        System.out.println("Hibernate initialized successfully on Tomcat");
    }
}
