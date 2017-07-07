package it.polimi.ingsw.GC_29.Controllers.Change;

import it.polimi.ingsw.GC_29.Controllers.GameState;

/**
 * Created by Christian on 05/07/2017.
 */
public class EndGame extends GameChange {

    private String winner;

    public EndGame(String winner){
        super(GameState.ENDED);
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

}
