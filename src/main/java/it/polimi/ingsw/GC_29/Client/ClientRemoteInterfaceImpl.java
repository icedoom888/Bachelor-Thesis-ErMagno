package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Christian on 12/06/2017.
 */
public class ClientRemoteInterfaceImpl extends UnicastRemoteObject implements ClientRemoteInterface {

    private Boolean gameBegun = false;

    private PlayerColor playerColor;

    private String userName;


    protected ClientRemoteInterfaceImpl() throws RemoteException {
        super();
    }

    @Override
    public void initializeNewGame() {

        gameBegun = true;
        System.out.println("GAME BEGUN TRUE");
    }


    @Override
    public void setPlayerColor(PlayerColor playerColor) throws RemoteException {

        this.playerColor = playerColor;

    }


    @Override
    public PlayerColor getPlayerColor() throws RemoteException {

        return this.playerColor;
    }

    @Override
    public String getUserName() throws RemoteException {

        return this.userName;
    }

    public void setUsername(String userName){

        this.userName = userName;
    }

    public Boolean getGameBegun() {
        return gameBegun;
    }
}
