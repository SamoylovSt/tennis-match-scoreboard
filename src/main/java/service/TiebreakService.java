package service;

import dto.MatchBoardDto;
import dto.PlayerScoreDto;
import util.PlayerScoreDtoManager;

import java.util.UUID;

public class TiebreakService {

    public PlayerScoreDto changeTiebreakScore(String playerWinsPoint, MatchBoardDto matchBoardDto) {
        PlayerScoreDto player1 = matchBoardDto.getPlayerScoreDto1();
        PlayerScoreDto player2 = matchBoardDto.getPlayerScoreDto2();
        PlayerScoreDto temp = new PlayerScoreDto();
        if (playerWinsPoint.equals("player1")) {
            temp = tiebreak(player1);
        } else if (playerWinsPoint.equals("player2")) {
            temp = tiebreak(player2);
        }
        return temp;
    }

    public PlayerScoreDto tiebreak(PlayerScoreDto player) {
        PlayerScoreDto temp = new PlayerScoreDto();
        player.setPoints(player.getPoints()+1);
        temp=player;
        return temp;

    }


}

