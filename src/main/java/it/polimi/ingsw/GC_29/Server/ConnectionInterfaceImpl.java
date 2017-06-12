package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRemoteInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christian on 12/06/2017.
 */
public class ConnectionInterfaceImpl extends UnicastRemoteObject implements ConnectionInterface{


    GameMatchHandler gameMatchHandler;

    protected ConnectionInterfaceImpl(GameMatchHandler gameMatchHandler) throws RemoteException {
        super();

        this.gameMatchHandler = gameMatchHandler;
    }

    @Override
    public Boolean login(String username, String password) throws RemoteException {
        String pw = gameMatchHandler.getUserPassword().get(username);

        if (pw == null) {
            gameMatchHandler.getUserPassword().put(username, password);
            //records.put(username, 0);
            return true;
        }

        return password.equals(pw);
    }

    @Override
    public void addClient(ClientRemoteInterface clientStub) throws RemoteException {

        System.out.println("CLIENT AGGIUNTO");

        gameMatchHandler.addClient(clientStub);




    }

}
