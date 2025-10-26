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
        String key = req.getParameter("uuid");
        MatchBoardDto matchBoardDto1 = manager.getMatchBoardDtoForId(UUID.fromString(key));
        PlayerScoreDto player1 = matchBoardDto1.getPlayerScoreDto1();
        PlayerScoreDto player2 = matchBoardDto1.getPlayerScoreDto2();
        req.setAttribute("player1", player1);
        req.setAttribute("player2", player2);
        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("uuid");
        System.out.println("UUID: " + key);
        MatchBoardDto matchBoardDto = manager.getMatchBoardDtoForId(UUID.fromString(key));
        PlayerScoreDto player1=matchBoardDto.getPlayerScoreDto1();
        PlayerScoreDto player2=matchBoardDto.getPlayerScoreDto2();
        System.out.println(player1);
        System.out.println(player2);
        req.setAttribute("player1", player1);
        req.setAttribute("player2", player2);
        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
        //ищу решение с кнопками фронта и с uuid нужен ли он мне вообще тут
    }
}
