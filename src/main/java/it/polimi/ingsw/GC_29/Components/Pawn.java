package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.PlayerColor;

/**
 * Created by Icedoom on 17/05/17.
 */
public class Pawn {
    private PlayerColor playerColor;

    public Pawn(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }
}
