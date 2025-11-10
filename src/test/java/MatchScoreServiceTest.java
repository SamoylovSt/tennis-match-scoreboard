import dto.PlayerScoreDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.MatchScoreService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchScoreServiceTest {

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
