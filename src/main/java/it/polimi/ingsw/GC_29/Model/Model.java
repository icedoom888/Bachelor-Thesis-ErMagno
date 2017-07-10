package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Controllers.Change.*;
import  it.polimi.ingsw.GC_29.Server.Observable;

import java.util.*;

/**
 * Created by Icedoom on 19/05/2017.
 */
public class Model extends Observable<Change>{

    private GameState gameState;

    private GameBoard gameBoard;
    private EnumMap<CardColor, ArrayDeque<DevelopmentCard>> orderedDecks;
    private Player currentPlayer;
    private Era currentEra;
    private int currentTurn;
    private int currentRound;
    private Action currentAction;
    private ArrayList<Player> turnOrder;
    private ArrayList<Player> skippedTurnPlayers;
    private HashMap<FamilyPawn, ActionSpace> pawnsOnActionSpace;
    private Map<Integer, BonusTile> bonusTileMap;

    public Model() {

        orderedDecks = new EnumMap<>(CardColor.class);
        pawnsOnActionSpace = new HashMap<>();
        gameState = GameState.RUNNING;
        skippedTurnPlayers = new ArrayList<>();
        turnOrder = new ArrayList<>();
        bonusTileMap = new HashMap<>();
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

    public void setGameState(GameState gameState) {
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


    public void setPawns(ArrayList<Player> players) {
        ArrayList<Pawn> pawns = new ArrayList<>();
        for (Player player : players) {
            pawns.add(player.getMarkerDiscs());
        }
        gameBoard.getFaithPointsTrack().startTrack(pawns);
        gameBoard.getVenturesPointsTrack().startTrack(pawns);
        gameBoard.getVictoryPointsTrack().startTrack(pawns);
    }

    public void setBonusTileMap(Map<Integer, BonusTile> bonusTileMap) {
        this.bonusTileMap = bonusTileMap;
    }

    public Map<Integer, BonusTile> getBonusTileMap() {
        return bonusTileMap;
    }

    public void updateTowerGUI(CardColor cardColor) {

        ArrayList<String> cards = new ArrayList<>();

        for (Floor floor : gameBoard.getTower(cardColor).getFloors()) {

            DevelopmentCard card = floor.getDevelopmentCard();

            if (card != null) {
                cards.add(card.getSpecial());
            }

            else {
                cards.add("null");
            }
        }


        notifyObserver(new TowerCardsChange(cards, cardColor));


    }

    public void updateTrackGUI(PlayerColor playerColor, GoodType goodType, int numberOfPoints) {

        notifyObserver(new TrackChange(playerColor, goodType, numberOfPoints));

    }

    public void updateDisconnectedTrackGUI(PlayerColor playerColor, GoodType goodType, int numberOfPoints) {


        notifyObserver(new TrackReset(playerColor, goodType, numberOfPoints));

    }

    public void updatePawnsGUI(FamilyPawn temporaryPawn) {

        int actionIndex = currentPlayer.getCurrentValidActionsList().indexOf(currentPlayer.getCurrentAction());


        notifyObserver(new AddPawnChange(temporaryPawn, actionIndex));


    }

    public void clearPawns() {


        notifyObserver(new ClearPawns());


    }


    public void setEndGame(Player winner) {

        notifyObserver(new EndGame(winner.getPlayerID()));

    }

    public void notifyPlayerReconnected(List<String> usernamePLayerReconnectedList) {

        notifyObserver(new ReconnectionChange(usernamePLayerReconnectedList));

    }

    public void notifyPlayerDisconnected(List<String> playerNames) {

        notifyObserver(new PlayerDisconnectedChange(playerNames));


    }

    public void notifyEndMove() {

        notifyObserver(new EndMove(currentPlayer.getPlayerID()));

    }

    public void sendIdGui(Map<PlayerColor, String> playerNames) {

        notifyObserver(new PlayerNames(playerNames));
    }
}

