package it.polimi.ingsw.GC_29.Controllers;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public abstract class Input implements Serializable{

    //private static final long serialVersionUID = -4334191184999875154L;

    public abstract void perform(GameStatus model) throws RemoteException;
}
