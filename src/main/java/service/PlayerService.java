package service;

import dao.PlayerDao;
import dto.PlayerNameDto;
import entity.Player;
import jakarta.persistence.NoResultException;

public class PlayerService {
    PlayerDao playerDao = new PlayerDao();

    public void createPlayer(PlayerNameDto player) {
        if (playerDao.findPlayerByName(player) == null) {
            playerDao.createPlayer(player);
        } else {
            System.out.println("Player already exists");
        }
    }


    public Player findPlayerByName(PlayerNameDto playerNameDto) {

            return playerDao.findPlayerByName(playerNameDto);

    }
}
