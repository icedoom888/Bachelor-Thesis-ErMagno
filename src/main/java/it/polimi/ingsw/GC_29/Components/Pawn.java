package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.io.Serializable;
import java.net.Proxy;

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
