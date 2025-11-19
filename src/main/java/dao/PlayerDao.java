package dao;

import entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class PlayerDao {
    private final String SELECT_PLAYER_BY_NAME = "FROM Player p where p.name=:name";

    public Player findPlayerByName(String playerName) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Player> query = em.createQuery(SELECT_PLAYER_BY_NAME, Player.class);
            query.setParameter("name", playerName);
            Player player = query.getSingleResult();
            System.out.println(player.getName() + "  name from findPlayerByName");
            return player;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(String playerName) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Player player = new Player();
            player.setName(playerName);
            em.persist(player);
            System.out.println(playerName + "player create");
        } catch (Exception ex) {
            throw new DaoException("player creation error");
        }
    }
}


