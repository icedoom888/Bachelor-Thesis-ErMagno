package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 21/05/2017.
 */
public class ExecuteActionState implements State {
    @Override
    public void executeState(PlayerController wrapper) {
        State nextState = wrapper.getCurrentAction().execute(); // TODO: la execute delle azioni ritorna sempre un nuovo stato del turno, vedi appunti quaderno
        wrapper.setCurrentState(nextState);
    }
}
