package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 07/06/2017.
 */
public class GameChange extends Change {

    /**
     *
     */
   // private static final long serialVersionUID = 5551223529797237865L;

    private final GameState newGameState;

    public GameChange(GameState newGameState){
        this.newGameState = newGameState;
    }

    public GameState getNewGameState() {
        return newGameState;
    }


    @Override
    public String toString() {
        return "StateChange [newGameChange=" + newGameState + "]";
    }

    /**
     * questa classe quando viene ricevuta da view informa cosa è cambiato, di conseguenza la view in base al GameEvent fa una Query al controller
     * pr ricevere l'oggetto cambiato e trattarlo di conseguenza (vedi Query esempio esercitazione12)
     */
}
