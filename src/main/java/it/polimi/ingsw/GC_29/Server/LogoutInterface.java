package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.Map;

/**
 * Created by Christian on 02/07/2017.
 */
public interface LogoutInterface {

    void clientDisconnected(String username);

    void setClientMatch(String username, ServerNewGame match);

    Map<String, ServerNewGame> getClientMatch();

    Map<String, ServerNewGame> getMatchMap();
}
