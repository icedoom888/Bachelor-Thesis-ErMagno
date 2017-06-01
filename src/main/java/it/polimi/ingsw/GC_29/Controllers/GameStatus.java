package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.*;

/**
 * Created by Icedoom on 19/05/2017.
 */
public class  GameStatus {

    private static GameStatus instance = null;

    // Mancano i controller da inserire in futuro
    private PlayerController playerController;
    private GameBoard gameBoard;
    EnumMap<CardColor, ArrayDeque<DevelopmentCard>> orderedDecks;
    private Player currentPlayer;
    private Era currentEra;
    private int currentTurn;
    private int currentRound;
    private Action currentAction;
    //private final int numberOfPlayers = COSTANTEDAFILE; TODO: bisogna creare delle costanti da file da usare, come in questo caso
    private ArrayList<Player> turnOrder;
    private ArrayList<Player> skippedTurnPlayers;
    private HashMap<FamilyPawn, ActionSpace> pawnsOnActionSpace;

    private GameStatus() {

        orderedDecks = new EnumMap<>(CardColor.class);
        pawnsOnActionSpace = new HashMap<>();
    }


    public static GameStatus getInstance(){
        if(instance == null){
            instance = new GameStatus();
        }
        return instance;
    }

    public Map<FamilyPawn, ActionSpace> getPawnsOnActionSpace() {

        return pawnsOnActionSpace;
    }


    public GameBoard getGameBoard() {

        return gameBoard;
    }


    public Map<CardColor, ArrayDeque<DevelopmentCard>> getOrderedDecks() {

        return orderedDecks;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
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

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
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


    public List<Player> getTurnOrder() {

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

    public ArrayList<Player> getSkippedTurnPlayers() {
        return skippedTurnPlayers;
    }

    public void setSkippedTurnPlayers(ArrayList<Player> skippedTurnPlayers) {
        this.skippedTurnPlayers = skippedTurnPlayers;
    }
}

