package servlet;

import dto.MatchListDto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchService;
import util.ServiceContainer;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private ServiceContainer container;
    private MatchService matchService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.container = (ServiceContainer) getServletContext().getAttribute("serviceContainer");
        this.matchService = container.getMatchService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int page = 1;
        int pageSize = 5;

        try {
            String pageParam = req.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                page = Integer.parseInt(pageParam);
            }
        } catch (NumberFormatException e) {
            page = 1;
        }

        long totalMatches = matchService.getTotalMatchCount();
        int totalPages = (int) Math.ceil((double) totalMatches / pageSize);


        try {
            String name = req.getParameter("name");
            if (name == null) {

                List<MatchListDto> matchesList = matchService.getAllMatchPagination(page, pageSize);

                req.setAttribute("matches", matchesList);
                req.setAttribute("currentPage", page);
                req.setAttribute("pageSize", pageSize);
                req.setAttribute("totalPages", totalPages);
                req.setAttribute("totalMatches", totalMatches);

                req.getRequestDispatcher("/matches.jsp").forward(req, resp);
            } else {
                List<MatchListDto> matchesList = matchService.getMatchForName(name);
                req.setAttribute("matches", matchesList);
                System.out.println("get servlet MatchesServlet");
                req.getRequestDispatcher("/matches.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new PaginationException(e);
        }

    }
}
