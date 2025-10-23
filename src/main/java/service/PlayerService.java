package service;

import dao.PlayerDao;
import dto.PlayerNameDto;
import entity.Player;

public class PlayerService {
    PlayerDao playerDao = new PlayerDao();


    public void createPlayer(PlayerNameDto player) {
        if (playerDao.findPlayerByName(player)== null) {
            playerDao.createPlayer(player);
        } else {
            System.out.println("Player already exists");
        }
    }

//    public boolean hasPlayerOrNot(Player player) {
//
//        if (playerDao.findPlayerByName(player).getId() == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }

}
