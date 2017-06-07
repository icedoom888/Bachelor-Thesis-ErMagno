package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 07/06/2017.
 */
public class GameChange extends Change {

    /**
     *
     */
   // private static final long serialVersionUID = 5551223529797237865L;

    private final GameEvent newGameEvent;

    public GameChange(GameEvent newPlayerState){
        this.newGameEvent = newPlayerState;
    }

    public GameEvent getNewGameEvent() {
        return newGameEvent;
    }

    /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
    @Override
    public String toString() {
        return "StateChange [newGameChange=" + newGameEvent + "]";
    }

    /**
     * questa classe quando viene ricevuta da view informa cosa è cambiato, di conseguenza la view in base al GameEvent fa una Query al controller
     * pr ricevere l'oggetto cambiato e trattarlo di conseguenza (vedi Query esempio esercitazione12)
     */
}
