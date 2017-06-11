package it.polimi.ingsw.GC_29.Controllers;


import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect;
import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Christian on 19/05/2017.
 */

public class PlayerController {

    private TurnState currentTurnState;
    private Player player;

    ///variabile per test
    private boolean commute = false;
    private int index = 0;

    public PlayerController() {

        this.player = GameStatus.getInstance().getCurrentPlayer();
        currentTurnState = new BeginTurnState();

    }

    public Player getPlayerStatus() {
        return player;
    }

    public void setCurrentTurnState(TurnState newTurnState){
        currentTurnState = newTurnState;
    }

    public void executeState() throws Exception {
        currentTurnState.executeState(this);
    }

    public void init() throws Exception {

        while(currentTurnState.getClass() != EndTurnState.class){ // finché non arrivo all'utlimo stato del turno continuo ad eseguire gli stati intermedi
            executeState();
        }

        executeState(); // endTurnState
    }


    /**
     *
     * @return check if the current player has a bonusAction to be executed
     */
    public boolean checkPresenceBonusActionEffect() {

       return !player.getCurrentBonusActionList().isEmpty();
    }

    public ActionEffect getBonusActionEffect(){

        return player.getCurrentBonusActionList().removeFirst();
    }


    /**
     * in this method, the player decides to place a family pawn or to skip the turn (the action)
     * It is a method that comunicates with player, so the Adapter pattern must be used to implement
     * the connection (socket and RMI)
     * @return
     */
    public boolean isPlaceFamilyMemberAction() {

        //Boolean answer = player.getAdapter().doAction();

         commute = !commute; //commute = commute==false

        return commute;
    }


    public boolean chooseAction() {

        // TODO: metodo di interfaccia, qui vi è distribuzione --> chiede quale azione vuole tra le disponibili o se il player vuole vedere le azioni possibili rispetto ad un altra pedina.

       // player.getAdapter().showValidActions();


        System.out.println("");

        Action actionChosen = ActionChecker.getInstance().getValidActionList().get(0);

        player.setCurrentAction(actionChosen);

        return  true;
    }
}
