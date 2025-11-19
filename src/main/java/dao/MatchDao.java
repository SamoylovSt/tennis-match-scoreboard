package dao;

import entity.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class MatchDao {
    private final String GET_MATCHES_FOR_ID = "FROM Match" +
            " m WHERE m.player1.id" +
            " = :id OR m.player2.id = :id ";
    private final String GET_ALL_MATCHES = "FROM Match m " +
            "LEFT JOIN FETCH m.player1 " +
            "LEFT JOIN FETCH m.player2";
    private final String GET_ALL_MATCHES_FOR_PAGINATION = "FROM Match" +
            " ORDER BY id DESC";

    public void save(Match matchForSave) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.persist(matchForSave);
            System.out.println("match saved");
        } catch (Exception ex) {
            throw new DaoException("match creation error");
        }

    }

    public List<Match> findByPlayerId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Match> query = em.createQuery(GET_MATCHES_FOR_ID, Match.class);
            query.setParameter("id", id);
            List<Match> matches = query.getResultList();
            return matches;
        } catch (Exception ex) {
            throw new DaoException("getMatchesForId error");
        }
    }

    public List<Match> getAllMatches() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Match> query = em.createQuery(GET_ALL_MATCHES, Match.class);
           //TODO количство сделать а не лист "COUNT(m) FROM Match m"
            List<Match> matches = query.getResultList();
            return matches;
        } catch (Exception ex) {
            throw new DaoException("Get all matches error");
        }
    }


    public List<Match> getAllMatchesPagination(int page, int pageSize) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Match> query = em.createQuery(GET_ALL_MATCHES_FOR_PAGINATION, Match.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            List<Match> matches = query.getResultList();
            return matches;
        } catch (Exception ex) {
            throw new DaoException("Pagination error");
        }

    }


}
