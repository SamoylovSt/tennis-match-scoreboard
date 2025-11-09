package dto;

import lombok.Data;

@Data
public class MatchBoardDto {

    private PlayerScoreDto playerScoreDto1;
    private PlayerScoreDto playerScoreDto2;

    private int tiebreakOnOff;

}
