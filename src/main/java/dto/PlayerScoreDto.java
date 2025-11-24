package dto;

import lombok.Data;

@Data
public class PlayerScoreDto {
    private String name;
    private int sets;
    private int games;
    private int points;
}
