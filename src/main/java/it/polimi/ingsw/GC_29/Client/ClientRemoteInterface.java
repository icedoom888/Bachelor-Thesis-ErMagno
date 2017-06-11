package it.polimi.ingsw.GC_29.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Christian on 11/06/2017.
 */
public interface ClientRemoteInterface extends Remote{

    void initializeNewGame() throws RemoteException;
}
