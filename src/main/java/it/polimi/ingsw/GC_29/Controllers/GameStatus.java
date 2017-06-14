package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import  it.polimi.ingsw.GC_29.Server.Observable;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Icedoom on 19/05/2017.
 */
public class GameStatus extends Observable<Change>{


    /*private GameEvent gameEvent;*/
    private GameState gameState;

    // Mancano i controller da inserire in futuro
    private GameBoard gameBoard;
    private EnumMap<CardColor, ArrayDeque<DevelopmentCard>> orderedDecks;
    private Player currentPlayer;
    private Era currentEra;
    private int currentTurn;
    private int currentRound;
    private Action currentAction;
    //private final int numberOfPlayers = COSTANTEDAFILE; TODO: bisogna creare delle costanti da file da usare, come in questo caso
    private ArrayList<Player> turnOrder;
    private ArrayList<Player> skippedTurnPlayers;
    private HashMap<FamilyPawn, ActionSpace> pawnsOnActionSpace;

    public GameStatus() {

        orderedDecks = new EnumMap<>(CardColor.class);
        pawnsOnActionSpace = new HashMap<>();
        gameState = GameState.RUNNING;
        skippedTurnPlayers = new ArrayList<>();
        turnOrder = new ArrayList<>();
    }


    public Map<FamilyPawn, ActionSpace> getPawnsOnActionSpace() {

        return pawnsOnActionSpace;
    }


    public GameBoard getGameBoard() {

        return gameBoard;
    }

    /**
     * this method notify all the player's view that a change in the game is happened, so the view can update
     * @param
     */
    /*public void setGameEvent(GameEvent gameEvent) throws RemoteException {
        this.gameEvent = gameEvent;
        notifyObserver(new GameChange(this.gameEvent));
    }*/

    public Map<CardColor, ArrayDeque<DevelopmentCard>> getOrderedDecks() {

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

    public Player getPlayer(PlayerColor playerColor){

        for (Player player : turnOrder) {
            if(player.getPlayerColor() == playerColor){
                return player;
            }
        }
        throw new IllegalArgumentException("Illegal color type: " + playerColor);
    }


    public void setTurnOrder(ArrayList<Player> turnOrder) {

        this.turnOrder = turnOrder;
    }


    public void setGameBoard(GameBoard gameBoard) {

        this.gameBoard = gameBoard;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) throws Exception {
        this.gameState = gameState;
        notifyObserver(new GameChange(this.gameState));
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public List<Player> getSkippedTurnPlayers() {
        return skippedTurnPlayers;
    }

    public void setSkippedTurnPlayers(ArrayList<Player> skippedTurnPlayers) {
        this.skippedTurnPlayers = skippedTurnPlayers;
    }


}

