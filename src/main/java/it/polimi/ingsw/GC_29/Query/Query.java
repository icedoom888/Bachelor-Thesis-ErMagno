package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.Model;

import java.io.Serializable;

/**
 * Created by Christian on 07/06/2017.
 */
public abstract class Query<O> implements Serializable{

    /**
     *
     */
    //private static final long serialVersionUID = 6852387886693073989L;

    /*
     * TODO: devi fare un'interfaccia modelInterface con all'interno solo i metodi get del model, ovviamente implementata dal model
     * in queste  query non vi è bisogno di ritornare una copia poiché il ritorno viene serializzato dunque l'oggetto dal lato client non ha effetti
     * su quello a lato server
      */
    public abstract O perform(Model model);
}
