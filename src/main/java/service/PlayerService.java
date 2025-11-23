package service;

import dao.PlayerDao;
import entity.Player;

public class PlayerService {
    PlayerDao playerDao;

    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public void createPlayer(String playerName) {
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
