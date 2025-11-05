package service;

import dto.PlayerScoreDto;
import util.PlayerScoreDtoManager;

import java.util.UUID;

public class TiebreakService {
    PlayerScoreDtoManager manager = PlayerScoreDtoManager.getInstance();

    public PlayerScoreDto changeTiebreakScore(String playerWinsPoint, String key) {
        PlayerScoreDto player1 = manager.getMatchBoardDtoForId(UUID.fromString(key)).getPlayerScoreDto1();
        PlayerScoreDto player2 = manager.getMatchBoardDtoForId(UUID.fromString(key)).getPlayerScoreDto2();
        PlayerScoreDto temp = new PlayerScoreDto();
        if (playerWinsPoint.equals("player1")) {
            temp = tiebreak(player1, key);
        } else if (playerWinsPoint.equals("player2")) {
            temp = tiebreak(player2, key);
        }
        return temp;
    }

    public PlayerScoreDto tiebreak(PlayerScoreDto player, String key) {
        PlayerScoreDto temp = new PlayerScoreDto();

        if (player.getPoints() < 30 && player.getGames() <= 7 && player.getSets() != 2) {
            player.setPoints(player.getPoints() + 15);
            temp = player;
        } else if (player.getPoints() == 30) {
            player.setPoints(player.getPoints() + 10);
            temp = player;
        } else if (player.getPoints() == 40 && player.getGames() < 7) {
            player.setPoints(0);
            player.setGames(player.getGames() + 1);
            temp = player;
        }
        return temp;

    }
}
