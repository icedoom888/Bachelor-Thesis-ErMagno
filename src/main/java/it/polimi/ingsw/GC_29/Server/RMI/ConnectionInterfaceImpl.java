package it.polimi.ingsw.GC_29.Server.RMI;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRemoteInterface;
import it.polimi.ingsw.GC_29.Server.GameMatchHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Christian on 12/06/2017.
 */
public class ConnectionInterfaceImpl extends UnicastRemoteObject implements ConnectionInterface{


    GameMatchHandler gameMatchHandler;

    public ConnectionInterfaceImpl(GameMatchHandler gameMatchHandler) throws RemoteException {
        super();

        this.gameMatchHandler = gameMatchHandler;
    }

    @Override
    public Boolean login(String username, String password) throws RemoteException {

        if(gameMatchHandler.verifyLoggedClient(username)){

            return false;
        }

        else {

            String pw = gameMatchHandler.getUserPassword().get(username);

            if (pw == null) {
                gameMatchHandler.getUserPassword().put(username, password);
                return true;
            }

            return password.equals(pw);

        }


    }

    @Override
    public void addClient(ClientRemoteInterface clientStub) throws RemoteException {

        System.out.println("CLIENT AGGIUNTO");

        gameMatchHandler.addClient(clientStub);




    }

}
