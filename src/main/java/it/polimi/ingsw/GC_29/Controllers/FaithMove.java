package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.Track;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public class FaithMove extends MovePawn {

    public FaithMove(PlayerColor playerColor, int numberOfPoints) {
        super(playerColor, numberOfPoints);
    }

    @Override
    public void moveOnTrack(GameStatus model) {
        Track faithPointsTrack = model.getGameBoard().getFaithPointsTrack();
        faithPointsTrack.movePawn(numberOfPoints, playerColor);
    }
}
