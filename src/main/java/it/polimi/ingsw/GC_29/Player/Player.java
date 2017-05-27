package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.*;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public class Player {
    private String playerID;
    private GameBoard gameboard;
    private PersonalBoard personalBoard;
    private PlayerStatus status;
    private LeaderCard[] leaderCards;
    private FamilyPawn[] relativePawns;
    private Pawn[] excommunicationPawns;
    private Pawn[] markerDiscs;

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

    public FamilyPawn[] getRelativePawns() {
        return relativePawns;
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
