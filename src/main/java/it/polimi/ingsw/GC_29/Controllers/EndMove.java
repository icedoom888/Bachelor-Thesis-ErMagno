package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 05/07/2017.
 */
public class EndMove extends GameChange {

    private String username;

    public EndMove(String username) {
        super(GameState.RUNNING);

        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
