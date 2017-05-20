package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.Dice;
import it.polimi.ingsw.GC_29.Components.Era;
import it.polimi.ingsw.GC_29.Components.GameBoard;
import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Icedoom on 19/05/2017.
 */
public class  GameStatus {

    // Mancano i controller da inserire in futuro
    private GameBoard gameBoard;
    private Player currentPlayer;
    private Era currentEra;
    private int currentTurn;
    private final int numberOfPlayers;
    private Player[] turnOrder;
    private Dice[] currentDices;

    public GameStatus(GameBoard gameBoard, int numberOfPlayers) {
        this.gameBoard = gameBoard;
        this.currentPlayer = null;
        this.currentEra = Era.FIRSTERA;
        this.currentTurn = 0;
        this.numberOfPlayers = numberOfPlayers;
        this.turnOrder = new Player[numberOfPlayers];
        this.currentDices = new Dice[3];

    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Era getCurrentEra() {
        return currentEra;
    }

    public void setCurrentEra(Era currentEra) {
        this.currentEra = currentEra;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Player[] getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(Player[] turnOrder) {
        this.turnOrder = turnOrder;
    }

    public Dice[] getCurrentDices() {
        return currentDices;
    }

    public void setcurrentDices(){
        currentDices=getGameBoard().getDiceLane();
    }

}
