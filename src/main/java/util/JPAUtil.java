package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class JPAUtil {

    private static EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    public static void initialize(EntityManagerFactory factory) {
        emf = factory;
    }

    public static void createEntityManagerForRequest() {
        if (threadLocal.get() != null) {
            throw new IllegalStateException("EM alreadu exist gor this thread");
        }
        System.out.println("EM created for request");
        EntityManager em = emf.createEntityManager();
        threadLocal.set(em);
    }

    public static EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();
        if (em == null) {
            throw new IllegalStateException("em is not found");
        }
        return em;

    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null) {
            System.out.println("EM closet for request");
            if(em.isOpen()){
                em.close();
            }
            threadLocal.remove();
        }
    }
}
