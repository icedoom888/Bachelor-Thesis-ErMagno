package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Model.GoodType;
import it.polimi.ingsw.GC_29.Model.Track;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public class VictoryMove extends MovePawn {

    public VictoryMove(int numberOfPoints) {
        super(numberOfPoints);
    }

    @Override
    public void moveOnTrack(Model model) {

        Track victoryPointsTrack = model.getGameBoard().getVictoryPointsTrack();
        victoryPointsTrack.movePawn(numberOfPoints, super.playerPawn(model));
        model.updateTrackGUI(model.getCurrentPlayer().getPlayerColor(), GoodType.VICTORYPOINTS, numberOfPoints);
    }
}
