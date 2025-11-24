package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PlayerService;
import util.PlayerScoreDtoManager;
import util.ServiceContainer;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class MatchServlet extends HttpServlet {
    private ServiceContainer container;
    private PlayerService playerService;
    private PlayerScoreDtoManager manager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.container = (ServiceContainer) getServletContext().getAttribute("serviceContainer");
        this.playerService = container.getPlayerService();
        this.manager = PlayerScoreDtoManager.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String playerOne = req.getParameter("playerOne");
        String playerTwo = req.getParameter("playerTwo");

        if (!playerOne.equals(playerTwo) && playerOne.length() < 20 && playerTwo.length() < 20) {
            if (playerService.findPlayerByName(playerOne) == null) {
                playerService.createPlayer(playerOne);
            } else {
                playerOne = (playerService.findPlayerByName(playerOne).getName());
            }
            if (playerService.findPlayerByName(playerTwo) == null) {
                playerService.createPlayer(playerTwo);
            } else {
                playerOne = (playerService.findPlayerByName(playerTwo).getName());
            }
        } else {
            if (playerOne.equals(playerTwo)) {
                req.setAttribute("errorMessage", "the same name");
                req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
            } else if (playerOne.length() > 20 || playerTwo.length() > 20) {
                req.setAttribute("errorMessage", "name is too long");
                req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
            }
            return;
        }
        UUID uuid = UUID.randomUUID();
        manager.setPlayerScoreDto(uuid, playerOne, playerTwo);
        resp.sendRedirect("/match-score?uuid=" + uuid.toString());
    }
}
