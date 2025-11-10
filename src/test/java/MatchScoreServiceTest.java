import dto.MatchBoardDto;
import dto.PlayerScoreDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.MatchScoreService;
import util.PlayerScoreDtoManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchScoreServiceTest {
    //    PlayerScoreDto playerScoreDto1 = new PlayerScoreDto();
//    PlayerScoreDto playerScoreDto2 = new PlayerScoreDto();
//    PlayerScoreDtoManager playerScoreDtoManager = new PlayerScoreDtoManager();
    MatchScoreService matchScoreService;

    @BeforeEach
    void prepare() {
        matchScoreService = new MatchScoreService();
    }

    @Test
    void boardCalculatorGamesTest() {

        var playerScoreDto = new PlayerScoreDto();
        playerScoreDto.setName("gey");
        playerScoreDto.setSets(1);
        playerScoreDto.setGames(1);
        playerScoreDto.setPoints(40);

        int expectedValue = 2;

        PlayerScoreDto playerScoreDtoResult = matchScoreService.boardCalculator(playerScoreDto, "132");
        assertEquals(expectedValue, playerScoreDtoResult.getGames());
    }

    @Test
    void boardCalculatorSetTest() {

        var playerScoreDto = new PlayerScoreDto();
        playerScoreDto.setName("gey");
        playerScoreDto.setSets(1);
        playerScoreDto.setGames(6);
        playerScoreDto.setPoints(0);

        int expectedValue = 2;

        PlayerScoreDto playerScoreDtoResult = matchScoreService.boardCalculator(playerScoreDto, "132");
        assertEquals(expectedValue, playerScoreDtoResult.getSets());
    }

//    @Test
//    void changeScoreTest() {
//        playerScoreDto1.setName("gey");
//        playerScoreDto1.setSets(0);
//        playerScoreDto1.setGames(6);
//        playerScoreDto1.setPoints(0);
//
//
//        playerScoreDto2.setName("gey2");
//        playerScoreDto2.setSets(0);
//        playerScoreDto2.setGames(6);
//        playerScoreDto2.setPoints(0);
//        var matchBoardDto = new MatchBoardDto();
//        matchBoardDto.setPlayerScoreDto1(playerScoreDto1);
//        matchBoardDto.setPlayerScoreDto2(playerScoreDto2);
//        String uuid = UUID.randomUUID().toString();
//        playerScoreDtoManager.putMatchBoardDto(UUID.fromString(uuid), matchBoardDto);
//        MatchScoreService matchScoreService = new MatchScoreService();
//        MatchBoardDto result = matchScoreService.changeScore("player1", uuid);
//
//        int expectedValue = 0;

    /// /        assertEquals(expectedValue, result.getTiebreakOnOff());
//    }
    @Test
    void boardCalculatorPointsTest() {

        var playerScoreDto = new PlayerScoreDto();
        playerScoreDto.setName("gey");
        playerScoreDto.setSets(1);
        playerScoreDto.setGames(1);
        playerScoreDto.setPoints(30);

        int expectedValue = 40;

        PlayerScoreDto playerScoreDtoResult = matchScoreService.boardCalculator(playerScoreDto, "132");
        assertEquals(expectedValue, playerScoreDtoResult.getPoints());
    }

    @AfterEach
    void deleteData(){

    }
}
