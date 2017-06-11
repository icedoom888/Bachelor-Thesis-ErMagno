package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Server.Observer;

/**
 * Created by Lorenzotara on 11/06/17.
 */
public class TrackController implements Observer<MovePawn> {

    private GameStatus model = GameStatus.getInstance();

    @Override
    public void update(MovePawn movePawn) throws Exception {
        movePawn.moveOnTrack(model);
    }

    @Override
    public void update() {

    }
}
