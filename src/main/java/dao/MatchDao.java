package dao;

import entity.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class MatchDao {


    public void createMatch(Match mathForSave) {
        EntityManager em = JPAUtil.getEntutyManager();

        try {
            em.getTransaction().begin();

            em.persist(mathForSave);
            System.out.println("match saved");
            em.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
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
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error in getMatchesForId: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to get matches for id: " + id + ", Error: " + e.getMessage());
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
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException();
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
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException();
        }
    }


}
