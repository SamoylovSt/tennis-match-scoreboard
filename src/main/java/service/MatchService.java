package service;

import dao.MatchDao;
import dao.PlayerDao;
import dto.MatchListDto;
import dto.PlayerNameDto;
import dto.PlayerScoreDto;
import entity.Match;
import entity.Player;
import util.PlayerScoreDtoManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MatchService {
    PlayerDao playerDao = new PlayerDao();
    MatchDao matchDao = new MatchDao();
    //PlayerService playerService = new PlayerService();
    //ошибка когда в одном сервисе вызывается другой
//
    PlayerScoreDtoManager manager = PlayerScoreDtoManager.getInstance();

    //
    public void saveMatch(String keyMatch) {
        Match matchForSave = new Match();
        PlayerScoreDto player1 = manager.getMatchBoardDtoForId(UUID.fromString(keyMatch)).getPlayerScoreDto1();
        PlayerScoreDto player2 = manager.getMatchBoardDtoForId(UUID.fromString(keyMatch)).getPlayerScoreDto2();
        Player playerForMatch1 = playerDao.findPlayerByName(player1.convertToPlayerNameDto(player1));
        Player playerForMatch2 = playerDao.findPlayerByName(player2.convertToPlayerNameDto(player2));
        Player winner = playerForMatch2;
        matchForSave.setPlayer1(playerForMatch1);
        matchForSave.setPlayer2(playerForMatch2);
        matchForSave.setWinner(winner);
        matchDao.createMatch(matchForSave);
    }

    //
    public List<MatchListDto> getMatchForName(String name) {

        PlayerNameDto playerNameDto = new PlayerNameDto();
        playerNameDto.setName(name);
        int playerId = playerDao.findPlayerByName(playerNameDto).getId();
        List<Match> matches = matchDao.getMatchesForId(playerId);
        List<MatchListDto> matchListDto = new ArrayList<>();

        for (Match m : matches) {
            MatchListDto matchListDto1 = new MatchListDto();
            matchListDto1.setPlayerOneName(m.getPlayer1().getName());
            matchListDto1.setPlayerTwoName(m.getPlayer2().getName());
            matchListDto1.setWinnerName(m.getWinner().getName());
            matchListDto.add(matchListDto1);
        }
        return matchListDto;
    }

    public List<MatchListDto> getAllMatch() {
        List<Match> matches = matchDao.getAllMatches();
        List<MatchListDto> matchListDto = new ArrayList<>();

        for (Match m : matches) {
            MatchListDto matchListDto1 = new MatchListDto();
            matchListDto1.setPlayerOneName(m.getPlayer1().getName());
            matchListDto1.setPlayerTwoName(m.getPlayer2().getName());
            matchListDto1.setWinnerName(m.getWinner().getName());
            matchListDto.add(matchListDto1);
        }
        return matchListDto;
    }
}
