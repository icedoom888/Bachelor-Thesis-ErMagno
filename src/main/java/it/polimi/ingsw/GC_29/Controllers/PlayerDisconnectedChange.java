package it.polimi.ingsw.GC_29.Controllers;

import java.util.List;

/**
 * Created by Christian on 05/07/2017.
 */
public class PlayerDisconnectedChange extends Change {

    private List<String> usernames;

    public PlayerDisconnectedChange(List<String> usernames){

        this.usernames = usernames;
    }

    public List<String> getUsername() {
        return usernames;
    }
}
