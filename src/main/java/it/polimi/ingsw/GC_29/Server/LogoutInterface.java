package it.polimi.ingsw.GC_29.Server;

import java.util.Map;

/**
 * Created by Christian on 02/07/2017.
 */

/**
 * interface used by the client view to notify the gameMatchHandler that a player is disconnected
 */
public interface LogoutInterface {

    void clientDisconnected(String username);

    void setClientMatch(String username, ServerNewGame match);

    Map<String, ServerNewGame> getClientMatch();

    Map<String, ServerNewGame> getMatchMap();
}
