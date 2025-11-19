package service;

import dao.PlayerDao;
import entity.Player;

public class PlayerService {
    PlayerDao playerDao = new PlayerDao();

    public void createPlayer(String playerName) {

        if (playerDao.findPlayerByName(playerName) == null) {
            playerDao.save(playerName);

        } else {
            System.out.println("Player already exists");
        }
    }
    public Player findPlayerByName(String playerName) {

        return playerDao.findPlayerByName(playerName);

    }
}
