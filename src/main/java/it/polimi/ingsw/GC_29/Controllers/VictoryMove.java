package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.Track;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public class VictoryMove extends MovePawn {

    public VictoryMove(PlayerColor playerColor, int numberOfPoints) {
        super(playerColor, numberOfPoints);
    }

    @Override
    public void moveOnTrack(GameStatus model) {
        Track victoryPointsTrack = model.getGameBoard().getVictoryPointsTrack();
        victoryPointsTrack.movePawn(numberOfPoints, playerColor);
    }
}
