package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Controllers.Change;
import it.polimi.ingsw.GC_29.Controllers.Input;

/**
 * Created by Christian on 07/06/2017.
 */


public abstract class View extends Observable<Input> // osservabile rispetto al tipo azione
        implements Observer<Change> // osserva il tipo change
{
    protected LogoutInterface logoutInterface;

    public void registerLogout(LogoutInterface logoutInterface){

        this.logoutInterface = logoutInterface;
    }

}
