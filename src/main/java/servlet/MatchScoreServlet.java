package servlet;

import dto.MatchBoardDto;
import dto.PlayerScoreDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchScoreService;
import util.PlayerScoreDtoManager;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    MatchScoreService matchScoreService = new MatchScoreService();
    PlayerScoreDtoManager playerScoreDtoManager = PlayerScoreDtoManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        MatchBoardDto matchBoardDto1 = playerScoreDtoManager.getMatchBoardDtoForId(UUID.fromString(uuid));
        PlayerScoreDto player1 = matchBoardDto1.getPlayerScoreDto1();
        PlayerScoreDto player2 = matchBoardDto1.getPlayerScoreDto2();
        req.setAttribute("player1", player1);
        req.setAttribute("player2", player2);
        req.setAttribute("matchUuid", uuid);
        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerButton = req.getParameter("player");
        MatchBoardDto matchBoardDto = matchScoreService.changeScore(req, resp);
        PlayerScoreDto player1 = matchBoardDto.getPlayerScoreDto1();
        PlayerScoreDto player2 = matchBoardDto.getPlayerScoreDto2();
        String uuid = req.getParameter("uuid");

        boolean finishMatchPoints = ((player1.getSets() == 2 ) ||
                (player2.getSets() == 2));
        if (finishMatchPoints) {
            boolean player1Win = player1.getSets() == 2;
            boolean player2Win = player2.getSets() == 2;
            if (player1Win) {
                matchScoreService.selectPlayerToChangeScore(playerButton, uuid);
                req.setAttribute("winnerName", player1.getName());
                req.setAttribute("loserName", player2.getName());
                req.getRequestDispatcher("/winner.jsp").forward(req, resp);
            } else if (player2Win) {
                matchScoreService.selectPlayerToChangeScore(playerButton, uuid);
                req.setAttribute("winnerName", player2.getName());
                req.setAttribute("loserName", player1.getName());
                req.getRequestDispatcher("/winner.jsp").forward(req, resp);
            }
        }

        req.setAttribute("player1", player1);
        req.setAttribute("player2", player2);
        req.setAttribute("matchUuid", uuid);

        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }
}
