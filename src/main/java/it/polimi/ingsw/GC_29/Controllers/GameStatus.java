package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.GameBoard;
import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Christian on 19/05/2017.
 */
public class GameStatus {

    private GameBoard gameBoard;
    private Player currentPlayer;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
