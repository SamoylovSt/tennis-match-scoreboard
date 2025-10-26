package servlet;

import dto.PlayerNameDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PlayerService;
import util.PlayerScoreDtoManager;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class MatchServlet extends HttpServlet {

    PlayerService playerService = new PlayerService();
    PlayerScoreDtoManager manager = PlayerScoreDtoManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.html").forward(req, resp);
        System.out.println("get servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String playerOne = req.getParameter("playerOne");
        String playerTwo = req.getParameter("playerTwo");


        System.out.println("player one: " + playerOne + " player two: " + playerTwo);
        PlayerNameDto p1 = new PlayerNameDto();
        p1.setName(playerOne);
        PlayerNameDto p2 = new PlayerNameDto();
        p2.setName(playerTwo);
        playerService.createPlayer(p1);
        playerService.createPlayer(p2);


        UUID key = UUID.randomUUID();


        manager.setPlayerScoreDto(key, playerOne, playerTwo);
        resp.sendRedirect("/match-score?uuid=" + key.toString());
    }
}
