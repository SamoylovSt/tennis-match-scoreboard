package dao;

import dto.PlayerNameDto;
import entity.Player;

public interface InterfaceDao {
    public Player findPlayerByName(PlayerNameDto player1);
    //понять нахуй мне интерфейс
}
