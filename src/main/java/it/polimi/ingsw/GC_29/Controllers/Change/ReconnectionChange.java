package it.polimi.ingsw.GC_29.Controllers.Change;

import it.polimi.ingsw.GC_29.Controllers.Change.Change;

import java.util.List;

/**
 * Created by Christian on 05/07/2017.
 */
public class ReconnectionChange extends Change {

    private List<String> reconnectedPlayerUsernames;

    public ReconnectionChange(List<String> reconnectedPlayerUsernames){

        this.reconnectedPlayerUsernames = reconnectedPlayerUsernames;
    }

    public List<String> getReconnectedPlayerUsernames() {
        return reconnectedPlayerUsernames;
    }
}
