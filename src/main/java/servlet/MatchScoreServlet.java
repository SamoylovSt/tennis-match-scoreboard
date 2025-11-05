package servlet;

import dto.MatchBoardDto;
import dto.PlayerScoreDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchScoreService;
import service.TiebreakService;
import util.PlayerScoreDtoManager;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    int tiebreak;

    MatchScoreService matchScoreService = new MatchScoreService();
    TiebreakService tiebreakService = new TiebreakService();

    PlayerScoreDtoManager manager = PlayerScoreDtoManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        MatchBoardDto matchBoardDto1 = manager.getMatchBoardDtoForId(UUID.fromString(uuid));
        PlayerScoreDto player1 = matchBoardDto1.getPlayerScoreDto1();
        PlayerScoreDto player2 = matchBoardDto1.getPlayerScoreDto2();
        req.setAttribute("player1", player1);
        req.setAttribute("player2", player2);
        req.setAttribute("matchUuid", uuid);
        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        System.out.println("UUID: " + uuid);
        MatchBoardDto matchBoardDto = manager.getMatchBoardDtoForId(UUID.fromString(uuid));
        System.out.println("matchBoard: " + matchBoardDto);
        PlayerScoreDto player1 = matchBoardDto.getPlayerScoreDto1();
        PlayerScoreDto player2 = matchBoardDto.getPlayerScoreDto2();
        System.out.println(player1);
        System.out.println(player2);

        String player = req.getParameter("player");

        if (
                (player1.getGames() == 5 && player1.getPoints() == 40) &&
                        (player2.getGames() == 5 && player2.getPoints() == 40) && tiebreak != 1
        ) {
            tiebreak = 1;
            player1.setGames(0);
            player1.setPoints(0);
            player2.setGames(0);
            player2.setPoints(0);
            System.out.println("tiebreak GET STARTED");
        }
        if (tiebreak == 0) {
            if ((player1.getSets() == 1 && player1.getGames() == 5 && player1.getPoints() == 40) ||
                    (player2.getSets() == 1 && player2.getGames() == 5 && player2.getPoints() == 40)) {
                if (player1.getSets() == 1 && player1.getGames() == 5 && player1.getPoints() == 40) {
                    matchScoreService.changeScore(player, uuid);
                    matchScoreService.changeScore(player, uuid);
                    req.setAttribute("winnerName", player1.getName());
                    req.setAttribute("loserName", player2.getName());
                    req.getRequestDispatcher("/winner.jsp").forward(req, resp);
                } else if (player2.getSets() == 1 && player2.getGames() == 5 && player2.getPoints() == 40) {
                    matchScoreService.changeScore(player, uuid);
                    matchScoreService.changeScore(player, uuid);
                    req.setAttribute("winnerName", player2.getName());
                    req.setAttribute("loserName", player1.getName());
                    req.getRequestDispatcher("/winner.jsp").forward(req, resp);
                }
            } else {
                if (player.equals("player1")) {
                    System.out.println("кнопка 1");
                    player1 = matchScoreService.changeScore(player, uuid);

                }
                if (player.equals("player2")) {
                    System.out.println("кнопка 2");
                    player2 = matchScoreService.changeScore(player, uuid);
                }
            }
        } else if (tiebreak == 1) { /// //////////
            System.out.println("taibreak=1");
            if (player.equals("player1")) {
                System.out.println("score button 1");
                player1 = tiebreakService.changeTiebreakScore(player, uuid);
                System.out.println(player1.getGames() + " player 1 games");
                if (player1.getGames() == 7 || player2.getGames() == 7) {
                    tiebreak = 0;
                    player1.setSets(player1.getSets() + 1);
                    player1.setGames(0);
                    player1.setPoints(0);
                    player2.setGames(0);
                    player2.setPoints(0);
                }
            }
            if (player.equals("player2")) {
                System.out.println("score button 2");
                player2 = tiebreakService.changeTiebreakScore(player, uuid);
                System.out.println(player1.getGames() + " player 2 games");
                if (player2.getGames() == 7 || player2.getGames() == 7) {

                    tiebreak = 0;
                    player2.setSets(player2.getSets() + 1);
                    player2.setGames(0);
                    player2.setPoints(0);
                    player1.setGames(0);
                    player1.setPoints(0);
                }
            }
        }
        req.setAttribute("player1", player1);
        req.setAttribute("player2", player2);
        req.setAttribute("matchUuid", uuid);

        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }
}
