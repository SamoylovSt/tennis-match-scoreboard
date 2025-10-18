package dao;

import entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class PlayerDao {

    public Player findPlayerByName(Player player1) {
        EntityManager em = JPAUtil.getEntutyManager();
        try {
            String name = player1.getName();
            em.getTransaction().begin();
            TypedQuery<Player> query = em.createQuery("select p from Player p where p.name=:name", Player.class);
            query.setParameter("name", name);
            em.getTransaction().commit();
            Player player = query.getSingleResult();
            System.out.println(player.getName() + "  name from findPlayerByName");
            return player;
        } finally {
            em.close();
        }
    }

    public void createPlayer(Player player) {
        EntityManager em = JPAUtil.getEntutyManager();
        try {
            em.getTransaction().begin();
            em.persist(player);
            em.getTransaction().commit();
            System.out.println(findPlayerByName(player) + "созданный игрок");
        } finally {
            em.close();
        }
    }


}


