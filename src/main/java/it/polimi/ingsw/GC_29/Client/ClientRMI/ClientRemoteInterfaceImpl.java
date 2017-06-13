package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMIViewRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Christian on 12/06/2017.
 */
public class ClientRemoteInterfaceImpl extends UnicastRemoteObject implements ClientRemoteInterface {

    private Boolean gameBegun = false;

    private PlayerColor playerColor;

    private String userName;

    private RMIViewRemote serverViewStub;

    private Distribution distribution;

    public ClientRemoteInterfaceImpl(Distribution distribution) throws RemoteException {
        super();
        this.distribution = distribution;
    }

    @Override
    public void initializeNewGame(RMIViewRemote serverViewStub) {

        gameBegun = true;
        System.out.println("GAME BEGUN TRUE");
        this.serverViewStub = serverViewStub;

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

    public RMIViewRemote getServerViewStub() {
        return serverViewStub;
    }

    @Override
    public Distribution getDistribution() throws RemoteException {
        return distribution;
    }


}
