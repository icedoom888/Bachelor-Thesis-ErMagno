package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Created by Icedoom on 19/05/2017.
 */
public class  GameStatus {

    private static GameStatus instance = null;

    // Mancano i controller da inserire in futuro
    private GameBoard gameBoard;
    EnumMap<CardColor, ArrayDeque<DevelopmentCard>> orderedDecks;
    private Player currentPlayer;
    private Era currentEra;
    private int currentTurn;
    private Action currentAction;
    //private final int numberOfPlayers = COSTANTEDAFILE; TODO: bisogna creare delle costanti da file da usare, come in questo caso
    private ArrayList<Player> turnOrder;
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


    public EnumMap<CardColor, ArrayDeque<DevelopmentCard>> getOrderedDecks() {

        return orderedDecks;
    }


    public void setOrderedDecks(EnumMap<CardColor, ArrayDeque<DevelopmentCard>> orderedDecks) {

        this.orderedDecks = orderedDecks;
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


    public ArrayList<Player> getTurnOrder() {

        return turnOrder;
    }


    public void setTurnOrder(ArrayList<Player> turnOrder) {

        this.turnOrder = turnOrder;
    }


    public void setGameBoard(GameBoard gameBoard) {

        this.gameBoard = gameBoard;
    }

    public Action getCurrentAction() {
        return currentAction;
    }
}
