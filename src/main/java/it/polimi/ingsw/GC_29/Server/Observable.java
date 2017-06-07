package it.polimi.ingsw.GC_29.Server;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 07/06/2017.
 */

public abstract class Observable<C> {

    private List<Observer<C>> observers;

    public Observable(){
        this.observers=new ArrayList<Observer<C>>();
    }

    public void registerObserver(Observer<C> o){
        this.observers.add(o);
    }
    public void unregisterObserver(Observer<C> o){
        this.observers.remove(o);
    }

    public void notifyObserver(C c) throws RemoteException {
        System.out.println("I am the "+this.getClass().getSimpleName());
        for(Observer<C> o: this.observers){
            o.update(c);
        }
    }

    protected void notifyObserver() {
        for(Observer<C> o: this.observers){
            o.update();
        }
    }
}