package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Model.Model;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public abstract class Input implements Serializable{


    public abstract void perform(Model model, Controller controller) throws RemoteException;
}
