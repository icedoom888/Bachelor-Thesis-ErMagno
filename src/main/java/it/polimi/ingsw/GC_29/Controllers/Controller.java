package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Server.Observer;

import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public class Controller implements Observer<Input>{

    private final GameStatus gioco;

    public Controller(GameStatus gioco){
        this.gioco=gioco;
    }

    public void update(Input input) throws RemoteException {
        System.out.println("I AM THE CONTROLLER UPDATING THE MODEL");
        Observer.super.update(input);
        input.esegui(gioco);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}
