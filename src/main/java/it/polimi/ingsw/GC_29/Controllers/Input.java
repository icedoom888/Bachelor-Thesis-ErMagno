package it.polimi.ingsw.GC_29.Controllers;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public abstract class Input implements Serializable{


    public abstract void perform(GameStatus model, Controller controller) throws Exception;
}
