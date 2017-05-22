package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionType;
import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.Tower;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.HashMap;

/**
 * Created by Christian on 19/05/2017.
 */
public class PlayerController {

    private State currentState;
    private Action currentAction;
    private GameStatus gameStatus;
    private PlayerStatus playerStatus;
    private ActionBuilder actionBuilder;

    public PlayerController() {
        this.gameStatus = GameStatus.getInstance();
        this.playerStatus = gameStatus.getCurrentPlayer().getStatus();
        currentState = new BeginTurnState();

    }

    public void setCurrentState(State newState){
        currentState = newState;
    }

    public void executeState(){
        currentState.executeState(this);
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }

    public ActionBuilder getActionBuilder() {
        return actionBuilder;
    }

    public void init(){

        while(currentState.getClass() != EndTurnState.class){ // finché non arrivo all'utlimo stato del turno continuo ad eseguire gli stati intermedi
            currentState.executeState(this);
        }

        currentState.executeState(this);

        /**
         * boolean valid = false;

        while(!valid){ // per il test su PlayerController
            ActionType typeSelected = askForAction();
            setActionBuilder(typeSelected);
            Action actionCreated = actionBuilder.build();
            valid = actionCreated.isPossible(); // dovrebbe prendere il metodo del tipo dinamico
        }


         * ciclo while(ActionExecutionSuccess)
         * chiamo askForAction e ciò che mi arriva lo sparo a setActionBuilde
         * actionBuilder chiama build(), in questo metodo viene creata e lanciata l'azione, il metodo build ritorna true se l'azione
         */

    }

    private ActionType askForAction(){

        return ActionType.BLUETOWER;
    }

    public void setActionBuilder(ActionType type) {

        if(type == ActionType.SKIPTURN){
            // TODO: gestione fine turno
        }
        else {
            actionBuilder = FactoryActionBuilder.getActionBuilder(type, false, playerStatus);
        }
        // TODO: continuazione processo di gestione turno
    }
}
