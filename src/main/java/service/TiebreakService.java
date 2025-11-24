package service;

import dto.MatchBoardDto;
import dto.PlayerScoreDto;
import util.PlayerScoreDtoManager;

import java.util.UUID;

public class TiebreakService {

    public PlayerScoreDto changeTiebreakScore(String playerWinsPoint, MatchBoardDto matchBoardDto) {
        PlayerScoreDto player1 = matchBoardDto.playerScoreDto1();
        PlayerScoreDto player2 = matchBoardDto.playerScoreDto2();
        if(playerWinsPoint.equals("player1")){
            return tiebreak(player1);
        }
        return tiebreak(player2);
    }

    public PlayerScoreDto tiebreak(PlayerScoreDto player) {
        PlayerScoreDto temp = new PlayerScoreDto();
        player.setPoints(player.getPoints()+1);
        temp=player;
        return temp;
    }


}

