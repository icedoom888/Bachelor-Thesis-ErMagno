package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Lorenzotara on 31/05/17.
 */
public class BeginTurnState implements TurnState {

    @Override
    public void executeState(PlayerController wrapper) {

        wrapper.setCurrentTurnState(new ValidationActionTurnState());
    }
}
