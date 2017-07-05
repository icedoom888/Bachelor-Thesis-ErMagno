package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 05/07/2017.
 */
public class PlayerDisconnectedChange extends Change {

    private String username;

    public PlayerDisconnectedChange(String username){

        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
