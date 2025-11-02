package servlet;

import dto.MatchBoardDto;
import dto.PlayerScoreDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PlayerService;
import util.PlayerScoreDtoManager;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    PlayerService playerService = new PlayerService();
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
        if (player1.getSets() == 2 || player2.getSets() == 2) {
            if (player1.getSets() == 2) {
                playerService.o4ko(player, uuid);
                req.setAttribute("winnerName", player1.getName());
                req.setAttribute("loserName", player2.getName());
                req.getRequestDispatcher("/winner.jsp").forward(req, resp);
            } else if (player2.getSets() == 2) {
                playerService.o4ko(player, uuid);
                req.setAttribute("winnerName", player2.getName());
                req.setAttribute("loserName", player1.getName());
                req.getRequestDispatcher("/winner.jsp").forward(req, resp);
            }

        } else {
            if (player.equals("player1")) {
                System.out.println("кнопка 1");
                player1 = playerService.o4ko(player, uuid);

            }
            if (player.equals("player2")) {
                System.out.println("кнопка 2");
                player2 = playerService.o4ko(player, uuid);

            }
        }

        req.setAttribute("player1", player1);
        req.setAttribute("player2", player2);
        req.setAttribute("matchUuid", uuid);

        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }
}
