package util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

//@WebListener
//public class JPAListener implements ServletContextListener {
//
//    public void contextInitialized(ServletContextEvent sce){
//        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
//        JPAUtil.initialize(emf);
//    }
//}
