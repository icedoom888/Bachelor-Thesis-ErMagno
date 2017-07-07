package it.polimi.ingsw.GC_29.Server;


import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Model.Model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by Christian on 07/06/2017.
 */

public abstract class Observable<C> {

    private List<Observer<C>> observers;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());


    public Observable(){
        this.observers= new CopyOnWriteArrayList<>();
    }

    public void registerObserver(Observer<C> o){
            this.observers.add(o);

    }
    public void unregisterObserver(Observer<C> o){

            this.observers.remove(o);


    }

    public void notifyObserver(C c) {


            for(Observer<C> o: this.observers){
                try {
                    o.update(c);
                } catch (ObserverException e) {
                    LOGGER.info((Supplier<String>) e);
                }
            }


    }

    protected void notifyObserver() {


            for(Observer<C> o: this.observers){
                o.update();
            }

    }
}