package servlet;

import dto.MatchListDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchService;

import java.io.IOException;

import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    MatchService matchService = new MatchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String name = req.getParameter("name");
            if (name == null) {
                List<MatchListDto> matchesList = matchService.getAllMatch();
                req.setAttribute("matches", matchesList);
                System.out.println("get servlet MatchesServlet with null parameter");
                req.getRequestDispatcher("/matches.jsp").forward(req, resp);
            } else {
                List<MatchListDto> matchesList = matchService.getMatchForName(name);
                req.setAttribute("matches", matchesList);
                System.out.println("get servlet MatchesServlet");
                req.getRequestDispatcher("/matches.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
