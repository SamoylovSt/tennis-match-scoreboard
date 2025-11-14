package util;

import dto.MatchBoardDto;
import dto.PlayerScoreDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerScoreDtoManager {

    private static PlayerScoreDtoManager instance;
    private Map<UUID, MatchBoardDto> matchCollection = new HashMap<>();

    public PlayerScoreDtoManager() {
    }

    public static PlayerScoreDtoManager getInstance() {
        if (instance == null) {
            instance = new PlayerScoreDtoManager();
        }
        return instance;
    }

    public MatchBoardDto getMatchBoardDtoForId(UUID key) {
        return matchCollection.get(key);
    }

    public void setPlayerScoreDto(UUID key, String player1Name, String player2Name) {
        PlayerScoreDto player = new PlayerScoreDto();
        player.setName(player1Name);
        player.setSets(0);
        player.setGames(0);
        player.setPoints(0);

        PlayerScoreDto player2 = new PlayerScoreDto();
        player2.setName(player2Name);
        player2.setSets(0);
        player2.setGames(0);
        player2.setPoints(0);
        MatchBoardDto matchBoardDto = new MatchBoardDto();
        matchBoardDto.setPlayerScoreDto1(player);
        matchBoardDto.setPlayerScoreDto2(player2);

        matchCollection.put(key, matchBoardDto);
    }

    public void deleteCurrentMatch(String key) {

        matchCollection.remove(key);
        System.out.println(matchCollection.get("delete" + key));

    }

    public void playersSetGamesAndPoints0(PlayerScoreDto player1, PlayerScoreDto player2) {
        player1.setGames(0);
        player1.setPoints(0);
        player2.setGames(0);
        player2.setPoints(0);
    }
}
