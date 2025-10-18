package service;

import dao.PlayerDao;
import entity.Player;

public class PlayerService {
    PlayerDao playerDao = new PlayerDao();


    public void createPlayer(Player player) {

        if (playerDao.findPlayerByName(player).getId()==null) {
            playerDao.createPlayer(player);
        } else {
            System.out.println("Player already exists");
        }
    }

    public boolean hasPlayerOrNot(Player player) {

        if (playerDao.findPlayerByName(player).getId() == null) {
            return false;
        } else {
            return true;
        }
    }

}
