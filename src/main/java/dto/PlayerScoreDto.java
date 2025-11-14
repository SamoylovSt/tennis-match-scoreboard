package dto;

import lombok.Data;

@Data
public class PlayerScoreDto {
    private String name;
    private int sets;
    private int games;
    private int points;

    public PlayerNameDto convertToPlayerNameDto(PlayerScoreDto player) {

        PlayerNameDto playerNameDto = new PlayerNameDto();

        playerNameDto.setName(player.getName());
        return playerNameDto;

    }
}
