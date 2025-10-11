package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        try (EntityManager em = emf.createEntityManager()) {
            System.out.println("ok");
        }
    }
}
