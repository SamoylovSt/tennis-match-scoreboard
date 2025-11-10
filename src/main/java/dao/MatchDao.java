package dao;

import entity.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class MatchDao {


    public void createMatch(Match mathForSave) {
        try (EntityManager em = JPAUtil.getEntutyManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(mathForSave);
                System.out.println("match saved");
                transaction.commit();
            } catch (Exception ex) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw new DaoException(ex);
            }
        }
    }

    public List<Match> getMatchesForId(int id) {

        EntityManager em = JPAUtil.getEntutyManager();
        try {
            em.getTransaction().begin();

            TypedQuery<Match> query = em.createQuery("FROM Match m WHERE m.player1.id = :id OR m.player2.id = :id ", Match.class);
            query.setParameter("id", id);
            List<Match> matches = query.getResultList();

            em.getTransaction().commit();
            return matches;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DaoException(ex);
        }
    }

    public List<Match> getAllMatches() {

        EntityManager em = JPAUtil.getEntutyManager();
        try {
            em.getTransaction().begin();

            TypedQuery<Match> query = em.createQuery("FROM Match", Match.class);
            List<Match> matches = query.getResultList();

            em.getTransaction().commit();
            return matches;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DaoException(ex);
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }


    public List<Match> getAllMatchesPagination(int page, int pageSize) {

        EntityManager em = JPAUtil.getEntutyManager();
        try {
            em.getTransaction().begin();

            TypedQuery<Match> query = em.createQuery("FROM Match ORDER BY id DESC", Match.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            List<Match> matches = query.getResultList();

            em.getTransaction().commit();
            return matches;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DaoException(ex);
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }


}
