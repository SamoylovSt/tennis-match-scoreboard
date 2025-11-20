package util;

import dao.MatchDao;
import dao.PlayerDao;
import service.MatchScoreService;
import service.MatchService;
import service.PlayerService;
import service.TiebreakService;

public class ServiceContainer {
    private final PlayerDao playerDao;
    private final MatchDao matchDao;

    private final MatchService matchService;
    private final MatchScoreService matchScoreService;
    private final PlayerService playerService;
    private final TiebreakService tiebreakService;
    private final PlayerScoreDtoManager manager;

    public ServiceContainer() {
        this.manager = PlayerScoreDtoManager.getInstance();
        this.playerDao =new PlayerDao();
        this.matchDao = new MatchDao();

        this.matchService = new MatchService(playerDao,matchDao);
        this.tiebreakService = new TiebreakService();
        this.matchScoreService = new MatchScoreService(matchService,manager,tiebreakService);
        this.playerService =new PlayerService(playerDao);

    }

    public TiebreakService getTiebreakService() {
        return tiebreakService;
    }

    public MatchScoreService getMatchScoreService() {
        return matchScoreService;
    }

    public MatchService getMatchService() {
        return matchService;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public PlayerScoreDtoManager getManager() {
        return manager;
    }

    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    public MatchDao getMatchDao() {
        return matchDao;
    }
}
