package service;

import dao.PlayerDao;
import entity.Player;

public class PlayerService {
    PlayerDao playerDao;

    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public void createPlayer(String playerName) {
//        if (playerDao.findPlayerByName(playerName) == null) {
//            playerDao.save(playerName);
//        } else {
//            System.out.println("Player already exists");
//        }
       try {
           playerDao.save(playerName);
       }catch (Exception e){
           throw  new IllegalArgumentException("player alredy exist (PlayerService)");
       }

    }

    public Player findPlayerByName(String playerName) {

        return playerDao.findPlayerByName(playerName);

    }
}
