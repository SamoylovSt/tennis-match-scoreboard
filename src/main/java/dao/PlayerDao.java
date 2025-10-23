package dao;

import dto.PlayerNameDto;
import entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class PlayerDao {

    public Player findPlayerByName(PlayerNameDto player1) {
        EntityManager em = JPAUtil.getEntutyManager();
        try {
            String name = player1.getName();
            em.getTransaction().begin();
            TypedQuery<Player> query = em.createQuery("select p from Player p where p.name=:name", Player.class);
            query.setParameter("name", name);
            Player player = query.getSingleResult();
            System.out.println(player.getName() + "  name from findPlayerByName");
            em.getTransaction().commit();
            return player;
        } catch (Exception e) {
            System.out.println("ERROR in findPlayerByName: " + e.getMessage());
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return null;
        }
//        finally {
//            em.close();
//        }
    }

    public void createPlayer(PlayerNameDto playerDto) {
        EntityManager em = JPAUtil.getEntutyManager();
        try {
            Player player = new Player();
            player.setName(playerDto.getName());
            em.getTransaction().begin();
            em.persist(player);
            em.getTransaction().commit();
            System.out.println(findPlayerByName(playerDto) + "созданный игрок");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        finally {
//            em.close();
//        }
    }


}


