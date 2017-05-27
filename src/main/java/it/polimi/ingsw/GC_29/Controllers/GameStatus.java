package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.HashMap;

/**
 * Created by Icedoom on 19/05/2017.
 */
public class  GameStatus {

    private static GameStatus instance = null;

    // Mancano i controller da inserire in futuro
    private GameBoard gameBoard;
    private Player currentPlayer;
    private Era currentEra;
    private int currentTurn;
    //private final int numberOfPlayers = COSTANTEDAFILE; TODO: bisogna creare delle costanti da file da usare, come in questo caso
    private Player[] turnOrder;
    private Dice[] currentDices;
    private HashMap<FamilyPawn, ActionSpace> pawnsOnActionSpace;

    private GameStatus() {

    }

    public static GameStatus getInstance(){
        if(instance == null){
            instance = new GameStatus();
        }
        return instance;
    }

    public HashMap<FamilyPawn, ActionSpace> getPawnsOnActionSpace() {
        return pawnsOnActionSpace;
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

    /*public int getNumberOfPlayers() {
        return numberOfPlayers;
    }*/

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

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setCurrentDices(Dice[] currentDices) {
        this.currentDices = currentDices;
    }
}
