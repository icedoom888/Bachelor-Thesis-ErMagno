package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Controllers.Change;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public interface ClientViewRemote // serve solo per rmi poiché in socket l'update viene fatto attraverso l'ObjectOutputStream!
        extends Remote {
    // Interface to receive information from the server

    public void updateClient(Change c)
            throws RemoteException;
}
