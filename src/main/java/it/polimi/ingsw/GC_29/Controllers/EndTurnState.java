package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 21/05/2017.
 */
public class EndTurnState implements TurnState {

    @Override
    public void executeState(PlayerController wrapper) {

        ActionChecker.getInstance().resetActionList();

        wrapper.setCurrentTurnState(new BeginTurnState());
    }
}
