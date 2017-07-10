package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.Model;

import java.io.Serializable;

/**
 * Created by Christian on 07/06/2017.
 */
public abstract class Query<O> implements Serializable{
    /*
     * in queste  query non vi è bisogno di ritornare una copia poiché il ritorno viene serializzato dunque l'oggetto dal lato client non ha effetti
     * su quello a lato server
      */
    public abstract O perform(Model model);
}
