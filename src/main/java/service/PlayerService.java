package service;

import dao.MatchDao;
import dao.PlayerDao;
import dto.PlayerNameDto;
import dto.PlayerScoreDto;
import entity.Match;
import entity.Player;
import util.PlayerScoreDtoManager;

import java.util.UUID;

public class PlayerService {
    PlayerDao playerDao = new PlayerDao();
    MatchDao matchDao = new MatchDao();
    MatchService matchService = new MatchService();
    PlayerScoreDtoManager manager = PlayerScoreDtoManager.getInstance();

    public void createPlayer(PlayerNameDto player) {
        if (playerDao.findPlayerByName(player) == null) {
            playerDao.createPlayer(player);
        } else {
            System.out.println("Player already exists");
        }
    }


    public PlayerScoreDto o4ko(String playerWinsPoint, String key) {
        PlayerScoreDto player1 = manager.getMatchBoardDtoForId(UUID.fromString(key)).getPlayerScoreDto1();
        PlayerScoreDto player2 = manager.getMatchBoardDtoForId(UUID.fromString(key)).getPlayerScoreDto2();
        PlayerScoreDto temp = new PlayerScoreDto();
        if (playerWinsPoint.equals("player1")) {
            temp = boardCalculator(player1, key);
        } else if (playerWinsPoint.equals("player2")) {
            temp = boardCalculator(player2, key);
        }
        return temp;
    }

    public PlayerScoreDto boardCalculator(PlayerScoreDto player, String key) {
        PlayerScoreDto temp = new PlayerScoreDto();
        if (player.getPoints() < 30 && player.getGames() <= 5 && player.getSets() != 2) {
            player.setPoints(player.getPoints() + 15);
            temp = player;
        } else if (player.getPoints() == 30) {
            player.setPoints(player.getPoints() + 10);
            temp = player;
        } else if (player.getPoints() == 40 && player.getGames() < 5) {
            player.setPoints(0);
            player.setGames(player.getGames() + 1);
            temp = player;
        } else if (player.getGames() == 5) {
            player.setGames(0);
            player.setPoints(0);
            player.setSets(player.getSets() + 1);
            temp = player;
        } else if (player.getSets() == 2) {
            temp = player;
            System.out.println("player servis . sets==2");
            matchService.saveMatch(key);
            manager.deleteCurrentMatch(key);
        }
        return temp;
    }

    public int getPlayerIdForName(String name) {
        PlayerNameDto playerNameDto = new PlayerNameDto();
        playerNameDto.setName(name);
        Player player = playerDao.findPlayerByName(playerNameDto);
        return player.getId();
    }
}
