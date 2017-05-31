package it.polimi.ingsw.GC_29.Controllers;


import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 19/05/2017.
 */

public class PlayerController {

    private TurnState currentTurnState;
    private PlayerStatus playerStatus;

    ///variabile per test
    private boolean commute = false;

    public PlayerController() {

        this.playerStatus = GameStatus.getInstance().getCurrentPlayer().getStatus();
        currentTurnState = new BeginTurnTurnState();

    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setCurrentTurnState(TurnState newTurnState){
        currentTurnState = newTurnState;
    }

    public void executeState(){
        currentTurnState.executeState(this);
    }

    public void init(){

        while(currentTurnState.getClass() != EndTurnTurnState.class){ // finché non arrivo all'utlimo stato del turno continuo ad eseguire gli stati intermedi
            executeState();
        }

        executeState(); // endTurnState
    }


    /**
     *
     * @return check if the current player has a bonusAction to be executed
     */
    public boolean checkPresenceBonusActionEffect() {

       return !playerStatus.getCurrentBonusActionList().isEmpty();
    }

    public ActionEffect getBonusActionEffect(){

        return playerStatus.getCurrentBonusActionList().removeFirst();
    }


    /**
     * in this method, the player decides to place a family pawn or to skip the turn (the action)
     * It is a method that comunicates with player, so the Adapter pattern must be used to implement
     * the connection (socket and RMI)
     * @return
     */
    public boolean isPlaceFamilyMemberAction() {

        commute = commute==false;

        return commute;
    }
}
