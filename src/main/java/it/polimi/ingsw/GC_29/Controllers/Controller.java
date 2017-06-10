package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Server.Observer;

import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */

/**
 * the controller class is an Observer of Input Objects, it observes the views of server side and when the views call
 * a notifyObserver(Input input) the update(Input input) of the controller is called and it performs the "perform" method
 * of the Input Object
 */
public class Controller implements Observer<Input>{

    private final GameStatus gioco;

    public Controller(GameStatus gioco){
        this.gioco=gioco;
    }

    public void update(Input input) throws RemoteException {
        System.out.println("I AM THE CONTROLLER UPDATING THE MODEL");
        Observer.super.update(input);
        input.perform(gioco);
    }

    @Override
    public void update() {
        // Auto-generated method stub

    }

}
