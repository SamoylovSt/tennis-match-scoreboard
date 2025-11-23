package service;

import dto.MatchBoardDto;
import dto.PlayerScoreDto;
import util.PlayerScoreDtoManager;

public class MatchScoreService {
    private volatile boolean tiebreak;
    private MatchService matchService;
    private PlayerScoreDtoManager manager;
    private TiebreakService tiebreakService;


    public MatchScoreService(MatchService matchService, PlayerScoreDtoManager manager, TiebreakService tiebreakService) {
        this.matchService = matchService;
        this.manager = manager;
        this.tiebreakService = tiebreakService;
    }

    public PlayerScoreDto selectPlayerToChangeScore(String playerWinsPoint, MatchBoardDto matchBoardDto) {
        PlayerScoreDto player1 = matchBoardDto.playerScoreDto1();
        PlayerScoreDto player2 = matchBoardDto.playerScoreDto2();
        PlayerScoreDto temp = new PlayerScoreDto();
        if (playerWinsPoint.equals("player1")) {
            temp = boardCalculator(player1, matchBoardDto);
        } else if (playerWinsPoint.equals("player2")) {
            temp = boardCalculator(player2, matchBoardDto);
        }
        return temp;
    }


    public PlayerScoreDto boardCalculator(PlayerScoreDto player, MatchBoardDto matchBoardDto) {
        PlayerScoreDto temp = new PlayerScoreDto();

        if (player.getPoints() < 30 && player.getGames() <= 5 && player.getSets() != 2) {
            player.setPoints(player.getPoints() + 15);
            temp = player;
        } else if (player.getPoints() == 30) {
            player.setPoints(player.getPoints() + 10);
            temp = player;
        } else if (player.getPoints() == 40 && player.getGames() < 6) {
            player.setPoints(0);
            player.setGames(player.getGames() + 1);
            temp = player;
        } else if (player.getGames() == 6) {
            player.setGames(0);
            player.setPoints(0);
            player.setSets(player.getSets() + 1);
            temp = player;
        } else if (player.getSets() == 2) {
            temp = player;
            System.out.println("player service . sets==2");

            matchService.saveMatch(matchBoardDto, player);
        }
        return temp;
    }

    public synchronized MatchBoardDto changeScore(String playerButton, MatchBoardDto matchBoardDto) {
        System.out.println("matchBoard: " + matchBoardDto);
        PlayerScoreDto player1 = matchBoardDto.playerScoreDto1();
        PlayerScoreDto player2 = matchBoardDto.playerScoreDto2();
        System.out.println(player1);
        System.out.println(player2);

        boolean goTiebreak = (player1.getGames() == 6) &&
                (player2.getGames() == 6);
        if (goTiebreak && tiebreak != true) {
            tiebreak = true;
            manager.playersSetGamesAndPoints0(player1, player2);
            System.out.println("tiebreak GET STARTED");
        }
        if (tiebreak == false) {
            boolean finishMatchPoints = ((player1.getSets() == 1 && player1.getGames() == 5 && player1.getPoints() == 40) ||
                    (player2.getSets() == 1 && player2.getGames() == 5 && player2.getPoints() == 40));
            if (finishMatchPoints) {

            } else {
                if (playerButton.equals("player1")) {
                    System.out.println("button 1");
                    player1 = selectPlayerToChangeScore(playerButton, matchBoardDto);
                    if (player1.getPoints() == 40) {
                        player2.setPoints(0);
                        player1 = selectPlayerToChangeScore(playerButton, matchBoardDto);
                    }
                }
                if (playerButton.equals("player2")) {
                    System.out.println("button 2");
                    player2 = selectPlayerToChangeScore(playerButton, matchBoardDto);
                    if (player2.getPoints() == 40) {
                        player1.setPoints(0);
                        player2 = selectPlayerToChangeScore(playerButton, matchBoardDto);
                    }
                }
            }
        } else if (tiebreak == true) {
            System.out.println("tiebreak=1");
            if (playerButton.equals("player1")) {
                System.out.println("score button 1");
                player1 = tiebreakService.changeTiebreakScore(playerButton, matchBoardDto);
                System.out.println(player1.getGames() + " player 1 games");
                if ((player1.getPoints() - 2) == player2.getPoints()) {
                    tiebreak = false;

                    player1.setSets(player1.getSets() + 1);
                    manager.playersSetGamesAndPoints0(player1, player2);
                }
            }
            if (playerButton.equals("player2")) {
                System.out.println("score button 2");
                player2 = tiebreakService.changeTiebreakScore(playerButton, matchBoardDto);
                System.out.println(player1.getGames() + " player 2 games");
                if (player1.getPoints() == (player2.getPoints() - 2)) {
                    tiebreak = false;

                    player2.setSets(player2.getSets() + 1);
                    manager.playersSetGamesAndPoints0(player1, player2);
                }
            }
        }

        MatchBoardDto matchBoardDto1 = new MatchBoardDto(player1, player2,
                tiebreak, false);
        return matchBoardDto1;
    }

}
