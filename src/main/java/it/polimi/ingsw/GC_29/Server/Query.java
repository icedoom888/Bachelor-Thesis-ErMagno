package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Controllers.GameStatus;

import java.io.Serializable;

/**
 * Created by Christian on 07/06/2017.
 */
public abstract class Query<O> implements Serializable{

    /**
     *
     */
    //private static final long serialVersionUID = 6852387886693073989L;

    public abstract O perform(GameStatus model);
}
