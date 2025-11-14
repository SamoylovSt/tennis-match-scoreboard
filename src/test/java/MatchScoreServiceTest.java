import dto.MatchBoardDto;
import dto.MatchListDto;
import dto.PlayerScoreDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.MatchScoreService;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreServiceTest {

    MatchScoreService matchScoreService;

    @BeforeEach
    void prepare() {
        matchScoreService = new MatchScoreService();
    }

    @Test
    void isFinishShouldBeFalse() {
        MatchBoardDto matchBoardDto = new MatchBoardDto();
        matchBoardDto.setFinish(false);
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

        matchBoardDto.setPlayerScoreDto1(playerScoreDto1);
        matchBoardDto.setPlayerScoreDto2(playerScoreDto2);
        MatchBoardDto result = matchScoreService.changeScore("player1", matchBoardDto);

        assertFalse(result.isFinish());

    }

    @Test
    void gamesShouldBeOne() {
        MatchBoardDto matchBoardDto = new MatchBoardDto();
        matchBoardDto.setFinish(false);
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

        matchBoardDto.setPlayerScoreDto1(playerScoreDto1);
        matchBoardDto.setPlayerScoreDto2(playerScoreDto2);
        MatchBoardDto result = matchScoreService.changeScore("player1", matchBoardDto);

        int expectedValue=1;
        assertEquals(expectedValue,result.getPlayerScoreDto1().getGames());
    }

    @Test
    void tiebreakShouldBeOne() {
        MatchBoardDto matchBoardDto = new MatchBoardDto();
        matchBoardDto.setFinish(false);
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

        matchBoardDto.setPlayerScoreDto1(playerScoreDto1);
        matchBoardDto.setPlayerScoreDto2(playerScoreDto2);
        MatchBoardDto result = matchScoreService.changeScore("player1", matchBoardDto);

        int expectedValue=1;
        System.out.println("tiebreak test");
        assertEquals(expectedValue,result.getTiebreakOnOff());
    }


}
