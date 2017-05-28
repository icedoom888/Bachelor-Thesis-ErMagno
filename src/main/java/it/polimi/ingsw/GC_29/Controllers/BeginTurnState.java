package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 21/05/2017.
 */
public class BeginTurnState implements State {

    @Override
    public void executeState(PlayerController wrapper) {

        wrapper.setCurrentState(new ValidationActionState());
    }
}
