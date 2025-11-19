package dto;

public record MatchBoardDto(PlayerScoreDto playerScoreDto1,
                            PlayerScoreDto playerScoreDto2,
                            int tiebreakOnOff,
                            boolean isFinish) {
    @Override
    public PlayerScoreDto playerScoreDto1() {
        return playerScoreDto1;
    }

    @Override
    public PlayerScoreDto playerScoreDto2() {
        return playerScoreDto2;
    }

    @Override
    public int tiebreakOnOff() {
        return tiebreakOnOff;
    }

    @Override
    public boolean isFinish() {
        return isFinish;
    }

}
