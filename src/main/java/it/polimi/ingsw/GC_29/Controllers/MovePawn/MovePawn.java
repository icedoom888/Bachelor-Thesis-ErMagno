package it.polimi.ingsw.GC_29.Controllers.MovePawn;

import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Pawn;

import java.io.Serializable;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public abstract class MovePawn implements Serializable {

    protected int numberOfPoints;

    public MovePawn(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public abstract void moveOnTrack(Model model);

    public Pawn playerPawn(Model model) {
        return model.getCurrentPlayer().getMarkerDiscs();
    }
}
