package it.polimi.ingsw.GC_29.Model;

import java.io.Serializable;

/**
 * Created by Icedoom on 17/05/17.
 */
public class Pawn implements Serializable{
    private PlayerColor playerColor;

    public Pawn(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "playerColor=" + playerColor +
                '}';
    }
}
