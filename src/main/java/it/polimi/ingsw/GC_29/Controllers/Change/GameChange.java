package it.polimi.ingsw.GC_29.Controllers.Change;

import it.polimi.ingsw.GC_29.Controllers.Change.Change;
import it.polimi.ingsw.GC_29.Controllers.GameState;

/**
 * Created by Christian on 07/06/2017.
 */
public class GameChange extends Change {


    protected final GameState newGameState;

    public GameChange(GameState newGameState){
        this.newGameState = newGameState;
    }

    public GameState getNewGameState() {
        return newGameState;
    }


    @Override
    public String toString() {
        return "StateChange [newGameChange=" + newGameState + "]";
    }

}
