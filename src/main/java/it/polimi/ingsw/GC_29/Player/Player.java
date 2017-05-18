package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.*;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public class Player {
    private String playerID;
    private PlayerColor playerColor;
    private it.polimi.ingsw.GC_29.Components.GameBoard gameboard;
    private it.polimi.ingsw.GC_29.Components.PersonalBoard personalBoard;
    private PlayerStatus status;
    private it.polimi.ingsw.GC_29.Components.LeaderCard[] leaderCards;
    private it.polimi.ingsw.GC_29.Components.FamilyPawn[] relativePawns;
    private it.polimi.ingsw.GC_29.Components.Pawn[] excommunicationPawns;
    private it.polimi.ingsw.GC_29.Components.Pawn[] markerDiscs;

    public String getPlayerID() {
        return playerID;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
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

    public void removeLeaderCard(LeaderCard leaderCard) {
        //TODO: scelta la carta leader da rimuovere, questo metodo la rimuove
    }
}
