package dto;

public class PlayerScoreDto {
    private String name;
    private int sets;
    private int games;
    private int points;

    public PlayerScoreDto(String name, int sets, int games, int points, String name2, int sets2, int games2, int points2) {
        this.name = name;
        this.sets = sets;
        this.games = games;
        this.points = points;
    }

    public PlayerScoreDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
