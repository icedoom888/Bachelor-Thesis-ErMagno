package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 07/06/2017.
 */
public class PlayerStateChange extends Change{

    /**
     *
     */
    private static final long serialVersionUID = 5551223529797237865L;

    private final PlayerState newPlayerState;

    public PlayerStateChange(PlayerState newPlayerState){
        this.newPlayerState = newPlayerState;
    }

    public PlayerState getNewPlayerState() {
        return newPlayerState;
    }

    /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
    @Override
    public String toString() {
        return "StateChange [newPlayerState=" + newPlayerState + "]";
    }
}
