package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.TowerAction;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.WorkAction;
import it.polimi.ingsw.GC_29.Player.Player;

import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public class ExecuteAction extends Input {

    private int actionIndex;

    private transient Player currentPlayer;

    public ExecuteAction(int actionIndex){

        this.actionIndex = actionIndex;
    }

    /**
     * The ExecuteAction class extends the Input class. the "perform" method is a polimorphic method that in this Concrete Input class executes
     * the chosen action (actionIndex) and than change the current player "PlayerState" --> due to this change the view of the specific player
     * will be notified about this change ( because each view observes the model and the specific PLayer associated with the client
     *
     *
     */
    @Override
    public void perform(Model model, Controller controller) {

        currentPlayer = model.getCurrentPlayer();

        Action actionSelected = currentPlayer.getCurrentValidActionsList().get(actionIndex);

        if(actionSelected instanceof WorkAction) {

            handleWorkAction(actionSelected, controller);

        }


        else if (actionSelected instanceof TowerAction) {

            handleTowerAction(actionSelected, controller);

        }

        /////////////NORMAL ACTIONS WITHOUT DISTRIBUTION////////////////////

        else {

            currentPlayer.setCurrentAction(actionSelected);

            actionSelected.execute();

            controller.handleEndAction();

        }

    }

    /**
     * this method checks if the card of the tower action selected has a price and if the card alternative prices that
     * the player can choose (the isPossible() method of the tower action creates a map of possible cost available for
     * the player). If the condition is true the method sets the player state in CHOOSECOST in order to enable the
     * comunication about the price choice.
     * @param actionSelected
     * @param controller
     */
    private void handleTowerAction(Action actionSelected, Controller controller) {


        TowerAction towerAction = (TowerAction)actionSelected;
        currentPlayer.setCurrentAction(towerAction);
        if (towerAction.getCardCost().isWithPrice() && towerAction.getPossibleCardCosts().keySet().size() > 1) {
            currentPlayer.setPlayerState(PlayerState.CHOOSECOST);
        }

        else {
            towerAction.setCostChosen(0);
            towerAction.execute();
            controller.handleEndAction();
        }

    }

    /**
     * this method checks if the player has different choiches about which card to activate in the workAction
     * towards the workers available. If the player cannot activate any card, the action is concluded after
     * giving the bonus from the bonus tile to the player
     * @param actionSelected
     * @param controller
     */
    private void handleWorkAction(Action actionSelected, Controller controller) {

        WorkAction workAction = (WorkAction)actionSelected;
        currentPlayer.setCurrentAction(workAction);

        workAction.buildDifferentChoices();


        if (workAction.getCardsForWorkers().keySet().contains(0) && workAction.getCardsForWorkers().size() == 1) {

            controller.handlePayToObtainCards(workAction, currentPlayer, 0);
        }

        else if(!(workAction.getCardsForWorkers().isEmpty())){

            currentPlayer.setPlayerState(PlayerState.CHOOSEWORKERS);
        }

        else{

            currentPlayer.setCurrentAction(workAction);

            workAction.execute();

            controller.handleEndAction();

        }


    }

}
