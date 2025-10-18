package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class JPAUtil {

    private static EntityManagerFactory emf;

    public static void initialize(EntityManagerFactory factory) {
       emf = factory;
    }

    public static EntityManager getEntutyManager() {
        System.out.println("em create");
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }
}
