package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Components.Track;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public class MilitaryMove extends MovePawn {

    public MilitaryMove(int numberOfPoints) {
        super(numberOfPoints);
    }

    @Override
    public void moveOnTrack(GameStatus model) {
        Track militaryPointsTrack = model.getGameBoard().getVenturesPointsTrack();
        militaryPointsTrack.movePawn(numberOfPoints, super.playerPawn(model));
        model.updateTrackGUI(model.getCurrentPlayer().getPlayerColor(), GoodType.MILITARYPOINTS, numberOfPoints);

    }
}
