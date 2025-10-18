package servlet;

import entity.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PlayerService;

import java.io.IOException;

@WebServlet("/new-match")
public class MatchServlet extends HttpServlet {

    PlayerService playerService = new PlayerService();

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

        System.out.println(playerOne + playerTwo);
        Player player=new Player();
        player.setName(playerOne);
        playerService.createPlayer(player);
       // resp.sendRedirect("/match-score?uuid=$match_id");

         //  resp.sendRedirect("new-match.html");
    }
}
