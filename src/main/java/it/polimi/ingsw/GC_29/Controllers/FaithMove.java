package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Model.GoodType;
import it.polimi.ingsw.GC_29.Model.Track;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public class FaithMove extends MovePawn {

    public FaithMove(int numberOfPoints) {
        super(numberOfPoints);
    }

    @Override
    public void moveOnTrack(Model model) {
        Track faithPointsTrack = model.getGameBoard().getFaithPointsTrack();
        faithPointsTrack.movePawn(numberOfPoints, super.playerPawn(model));
        model.updateTrackGUI(model.getCurrentPlayer().getPlayerColor(), GoodType.FAITHPOINTS, numberOfPoints);

    }
}
