package it.polimi.ingsw.GC_29.Controllers.MovePawn;

import it.polimi.ingsw.GC_29.Controllers.MovePawn.MovePawn;
import it.polimi.ingsw.GC_29.Model.GoodType;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Track;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public class MilitaryMove extends MovePawn {

    public MilitaryMove(int numberOfPoints) {
        super(numberOfPoints);
    }

    @Override
    public void moveOnTrack(Model model) {

        Track militaryPointsTrack = model.getGameBoard().getVenturesPointsTrack();
        militaryPointsTrack.movePawn(numberOfPoints, super.playerPawn(model));
        model.updateTrackGUI(model.getCurrentPlayer().getPlayerColor(), GoodType.MILITARYPOINTS, numberOfPoints);

    }
}
