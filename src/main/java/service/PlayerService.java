package service;

import dao.PlayerDao;
import dto.PlayerNameDto;

public class PlayerService {
    PlayerDao playerDao = new PlayerDao();

    public void createPlayer(PlayerNameDto player) {
        if (playerDao.findPlayerByName(player) == null) {
            playerDao.createPlayer(player);
        } else {
            System.out.println("Player already exists");
        }
    }

}
