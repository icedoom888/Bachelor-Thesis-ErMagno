package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRemoteInterface;

/**
 * Created by Christian on 11/06/2017.
 */
public interface ConnectionInterface {

    boolean login(String username, String Password);

    void addClient(ClientRemoteInterface clientStub);
}
