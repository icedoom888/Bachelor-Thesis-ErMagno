package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ZoneType;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 19/05/2017.
 */
public class PlayerController {

    private State currentState;
    private PlayerStatus playerStatus;

    public PlayerController() {

        this.playerStatus = GameStatus.getInstance().getCurrentPlayer().getStatus();
        currentState = new BeginTurnState();

    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setCurrentState(State newState){
        currentState = newState;
    }

    public void executeState(){
        currentState.executeState(this);
    }

    public void init(){

        while(currentState.getClass() != EndTurnState.class){ // finché non arrivo all'utlimo stato del turno continuo ad eseguire gli stati intermedi
            executeState();
        }

        executeState();

        /**
         * boolean valid = false;

        while(!valid){ // per il test su PlayerController
            ZoneType typeSelected = askForAction();
            setActionBuilder(typeSelected);
            Action actionCreated = actionBuilder.build();
            valid = actionCreated.isPossible(); // dovrebbe prendere il metodo del tipo dinamico
        }


         * ciclo while(ActionExecutionSuccess)
         * chiamo askForAction e ciò che mi arriva lo sparo a setActionBuilde
         * actionBuilder chiama build(), in questo metodo viene creata e lanciata l'azione, il metodo build ritorna true se l'azione
         */

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

    public int askForWorkers() {

        return 0;
    }

    public boolean isPlaceFamilyMemberAction() {

        return true;
    }
}
