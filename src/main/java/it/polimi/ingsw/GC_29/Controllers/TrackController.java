package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Controllers.MovePawn.MovePawn;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Server.Observer;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public class TrackController implements Observer<MovePawn> {

    private Model model;

    public TrackController(Model model) {
        this.model = model;
    }

    @Override
    public void update(MovePawn movePawn) {
        movePawn.moveOnTrack(model);

    }

    @Override
    public void update() {

    }
}
