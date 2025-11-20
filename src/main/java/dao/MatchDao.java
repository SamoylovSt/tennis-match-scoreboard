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
    private final String GET_COUNT_MATCHES = "SELECT COUNT(m) FROM Match m";
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

    public long getAllMatchesCount() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(m) FROM Match m", Long.class);
            System.out.println(query + "match count");
            Long matchesCount = query.getSingleResult();
            return matchesCount;
        } catch (Exception ex) {
            throw new DaoException("Get all matches error");
        }
    }


    public List<Match> getAllMatchesPagination(int offset, int pageSize) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Match> matches = em.createQuery(GET_ALL_MATCHES_FOR_PAGINATION, Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
            return matches;
        } catch (Exception ex) {
            throw new DaoException("Pagination error");
        }

    }


}
