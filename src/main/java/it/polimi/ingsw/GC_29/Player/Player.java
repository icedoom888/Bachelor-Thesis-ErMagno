package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.*;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public class Player {
    private String playerID;
    private PlayerColor playerColor;
    private GameBoard gameboard;
    private PersonalBoard personalBoard;
    private PlayerStatus status;
    private LeaderCard[] leaderCards;
    private FamilyPawn[] familyPawns; // TODO: sbagliato, deve essere una hashmap
    private Pawn[] excommunicationPawns;
    private Pawn[] markerDiscs;

    public Player(String playerID, PlayerColor playerColor, GameBoard gameboard, PersonalBoard personalBoard, PlayerStatus status) {

        this.playerID = playerID;
        this.playerColor = playerColor;
        this.gameboard = gameboard;
        this.personalBoard = personalBoard;
        this.status = status;

        leaderCards = new LeaderCard[4]; // TODO: decidere se rendere parametrico numero leader card

        familyPawns = new FamilyPawn[] {new FamilyPawn(playerColor, FamilyPawnType.BLACK, 0),
                                        new FamilyPawn(playerColor, FamilyPawnType.ORANGE, 0),
                                        new FamilyPawn(playerColor, FamilyPawnType.WHITE, 0),
                                        new FamilyPawn(playerColor, FamilyPawnType.NEUTRAL, 0)};

        excommunicationPawns = new Pawn[] {new Pawn(playerColor), new Pawn(playerColor), new Pawn(playerColor)};

        markerDiscs = new Pawn[] {new Pawn(playerColor), new Pawn(playerColor), new Pawn(playerColor)};

    }

    public String getPlayerID() {
        return playerID;
    }
    
    public GameBoard getGameboard() {
        return gameboard;
    }

    public PersonalBoard getPersonalBoard() {
        return personalBoard;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public LeaderCard[] getLeaderCards() {
        return leaderCards;
    }

    public FamilyPawn[] getFamilyPawns() {
        return familyPawns;
    }

    public Pawn[] getExcommunicationPawns() {
        return excommunicationPawns;
    }

    public Pawn[] getMarkerDiscs() {
        return markerDiscs;
    }

    public void setStatus(PlayerStatus status){ // per il test su PlayerController
        this.status = status;
    }

    public void removeLeaderCard(LeaderCard leaderCard) {
        //TODO: scelta la carta leader da rimuovere, questo metodo la rimuove
    }
}
