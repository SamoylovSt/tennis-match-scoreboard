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
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        System.out.println("get servlet MatchServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String playerOne = req.getParameter("playerOne");
        String playerTwo = req.getParameter("playerTwo");

        System.out.println("player one: " + playerOne + " player two: " + playerTwo);

        PlayerNameDto p1 = new PlayerNameDto();
        p1.setName(playerOne);


        if (!playerOne.equals(playerTwo) && playerOne.length() < 20 && playerTwo.length() < 20) {
            if (playerService.findPlayerByName(p1) == null) {
                playerService.createPlayer(p1);
                System.out.println("player1 save");
            } else {
                p1.setName(playerService.findPlayerByName(p1).getName());
                System.out.println("### player " + p1 + " already exists ###");
            }
            PlayerNameDto p2 = new PlayerNameDto();
            p2.setName(playerTwo);
            if (playerService.findPlayerByName(p2) == null) {
                playerService.createPlayer(p2);
                System.out.println("player2 save");
            } else {
                p2.setName(playerService.findPlayerByName(p2).getName());
                System.out.println("### player " + p2 + " already exists ###");
            }
        } else {
            if (playerOne.equals(playerTwo)) {

                System.out.println("odinakovoe imya");
                req.setAttribute("errorMessage", "odinakovoe imya");
                req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
            } else if (playerOne.length() > 20 || playerTwo.length() > 20 ) {
                System.out.println("name is too long");
                req.setAttribute("errorMessage", "name is too long");
                req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
            }
        }


        UUID uuid = UUID.randomUUID();

        manager.setPlayerScoreDto(uuid, playerOne, playerTwo);
        resp.sendRedirect("/match-score?uuid=" + uuid.toString());
    }
}
