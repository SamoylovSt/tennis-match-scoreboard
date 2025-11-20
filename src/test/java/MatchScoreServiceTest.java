import dao.MatchDao;
import dao.PlayerDao;
import dto.MatchBoardDto;
import dto.MatchListDto;
import dto.PlayerScoreDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.MatchScoreService;
import service.MatchService;
import service.TiebreakService;
import util.PlayerScoreDtoManager;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreServiceTest {
    PlayerDao playerDao = new PlayerDao();
    MatchDao matchDao = new MatchDao();
    MatchScoreService matchScoreService;
    MatchService matchService = new MatchService(playerDao, matchDao);
    PlayerScoreDtoManager manager = PlayerScoreDtoManager.getInstance();
    TiebreakService tiebreakService = new TiebreakService();

    @BeforeEach
    void prepare() {
        matchScoreService = new MatchScoreService(matchService, manager, tiebreakService);
    }

    @Test
    void isFinishShouldBeFalse() {


        var playerScoreDto1 = new PlayerScoreDto();
        playerScoreDto1.setName("gey");
        playerScoreDto1.setSets(0);
        playerScoreDto1.setGames(0);
        playerScoreDto1.setPoints(40);

        var playerScoreDto2 = new PlayerScoreDto();
        playerScoreDto2.setName("gey2");
        playerScoreDto2.setSets(0);
        playerScoreDto2.setGames(0);
        playerScoreDto2.setPoints(40);
        MatchBoardDto matchBoardDto = new MatchBoardDto(playerScoreDto1, playerScoreDto2, 0, false);

        MatchBoardDto result = matchScoreService.changeScore("player1", matchBoardDto);

        assertFalse(result.isFinish());

    }

    @Test
    void gamesShouldBeOne() {


        var playerScoreDto1 = new PlayerScoreDto();
        playerScoreDto1.setName("gey");
        playerScoreDto1.setSets(0);
        playerScoreDto1.setGames(0);
        playerScoreDto1.setPoints(40);

        var playerScoreDto2 = new PlayerScoreDto();
        playerScoreDto2.setName("gey2");
        playerScoreDto2.setSets(0);
        playerScoreDto2.setGames(0);
        playerScoreDto2.setPoints(40);
        MatchBoardDto matchBoardDto = new MatchBoardDto(playerScoreDto1, playerScoreDto2, 0, false);

        MatchBoardDto result = matchScoreService.changeScore("player1", matchBoardDto);

        int expectedValue = 1;
        assertEquals(expectedValue, result.playerScoreDto1().getGames());
    }

    @Test
    void tiebreakShouldBeOne() {

        var playerScoreDto1 = new PlayerScoreDto();
        playerScoreDto1.setName("gey");
        playerScoreDto1.setSets(0);
        playerScoreDto1.setGames(6);
        playerScoreDto1.setPoints(0);

        var playerScoreDto2 = new PlayerScoreDto();
        playerScoreDto2.setName("gey2");
        playerScoreDto2.setSets(0);
        playerScoreDto2.setGames(6);
        playerScoreDto2.setPoints(0);

        MatchBoardDto matchBoardDto = new MatchBoardDto(playerScoreDto1, playerScoreDto2, 0, false);

        MatchBoardDto result = matchScoreService.changeScore("player1", matchBoardDto);

        int expectedValue = 1;
        System.out.println("tiebreak test");
        assertEquals(expectedValue, result.tiebreakOnOff());
    }


}
