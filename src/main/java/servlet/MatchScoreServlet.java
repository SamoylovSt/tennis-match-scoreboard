package servlet;

import dto.MatchBoardDto;
import dto.PlayerScoreDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  Map<UUID, MatchBoardDto> currentMatch = new HashMap<>();

        PlayerScoreDto player1 = new PlayerScoreDto();
        player1.setName("player");
        player1.setSets(111);
        player1.setGames(1);
        player1.setPoints(1);
        req.setAttribute("player1", player1);

        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }
}
