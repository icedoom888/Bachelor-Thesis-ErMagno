package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.Pawn;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.io.Serializable;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public abstract class MovePawn implements Serializable {

    protected int numberOfPoints;

    public MovePawn(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public abstract void moveOnTrack(GameStatus model);

    public Pawn playerPawn(GameStatus model) {
        return model.getCurrentPlayer().getMarkerDiscs();
    }
}
