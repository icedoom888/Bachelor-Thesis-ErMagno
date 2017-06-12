package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRemoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Christian on 11/06/2017.
 */
public interface ConnectionInterface extends Remote{

    Boolean login(String username, String Password) throws RemoteException;

    void addClient(ClientRemoteInterface clientStub) throws RemoteException;
}
