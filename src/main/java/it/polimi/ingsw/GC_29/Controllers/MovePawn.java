package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.io.Serializable;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public abstract class MovePawn implements Serializable {

    protected PlayerColor playerColor;
    protected int numberOfPoints;

    public MovePawn(PlayerColor playerColor, int numberOfPoints) {
        this.playerColor = playerColor;
        this.numberOfPoints = numberOfPoints;
    }

    public abstract void moveOnTrack(GameStatus model);

}
