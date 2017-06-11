package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 21/05/2017.
 */
public interface TurnState {
    void executeState(PlayerController wrapper) throws Exception;
}
