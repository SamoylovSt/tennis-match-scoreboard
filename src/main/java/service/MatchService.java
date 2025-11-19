package service;

import dao.MatchDao;
import dao.PlayerDao;
import dto.MatchBoardDto;
import dto.MatchListDto;
import dto.PlayerScoreDto;
import entity.Match;
import entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MatchService {
    PlayerDao playerDao = new PlayerDao();
    MatchDao matchDao = new MatchDao();

    public void saveMatch(MatchBoardDto matchBoardDto) {
        Match matchForSave = new Match();
        System.out.println(" in saveMatch service");
        PlayerScoreDto player1 = matchBoardDto.playerScoreDto1();
        PlayerScoreDto player2 = matchBoardDto.playerScoreDto2();
        Player playerForMatch1 = playerDao.findPlayerByName(player1.getName());
        Player playerForMatch2 = playerDao.findPlayerByName(player2.getName());
        Player winner = playerForMatch2;
        matchForSave.setPlayer1(playerForMatch1);
        matchForSave.setPlayer2(playerForMatch2);
        matchForSave.setWinner(winner);
        System.out.println("saveMatch service complete");
        matchDao.save(matchForSave);
    }

    public List<MatchListDto> getMatchForName(String name) {

        int playerId = playerDao.findPlayerByName(name).getId();
        List<Match> matches = matchDao.findByPlayerId(playerId);
        List<MatchListDto> matchListDto = new ArrayList<>();

        for (Match m : matches) {
            MatchListDto matchListDto1 = new MatchListDto(m.getPlayer1().getName(),
                    m.getPlayer2().getName(),
                    m.getWinner().getName());
            matchListDto.add(matchListDto1);
        }
        return matchListDto;
    }


    public List<MatchListDto> getAllMatchPagination(int page, int pageSize) {
        List<Match> matches = matchDao.getAllMatchesPagination(page, pageSize);
        List<MatchListDto> matchListDto = new ArrayList<>();

        for (Match m : matches) {
            MatchListDto matchListDto1 = new MatchListDto(m.getPlayer1().getName(),
                    m.getPlayer2().getName(),
                    m.getWinner().getName());
            matchListDto.add(matchListDto1);
        }
        return matchListDto;
    }

    public int getTotalMatchCount() {
        try {
            List<Match> matches = matchDao.getAllMatches();
            List<MatchListDto> matchListDto = new ArrayList<>();
            int count = 0;
            for (Match m : matches) {
                MatchListDto matchListDto1 = new MatchListDto(m.getPlayer1().getName(),
                        m.getPlayer2().getName(),
                        m.getWinner().getName());
                matchListDto.add(matchListDto1);
                count++;
            }
            return count;
        } catch (Exception ex) {
            throw new MatchesException("db match counting error");
        }
    }
}


