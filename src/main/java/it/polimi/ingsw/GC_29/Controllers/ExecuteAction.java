package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect;
import it.polimi.ingsw.GC_29.Player.Player;

import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public class ExecuteAction extends Input {

    int actionIndex;

    Player currentPlayer;

    public ExecuteAction(int actionIndex){

        this.actionIndex = actionIndex;
    }

    /**
     * The ExecuteAction class extends the Input class. the "perform" method is a polimorphic method that in this Concrete Input class executes
     * the chosen action (actionIndex) and than change the current player "PlayerState" --> due to this change the view of the specific player
     * will be notified about this change ( because each view observes the model and the specific PLayer associated with the client
     *
     * @throws RemoteException
     */
    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        currentPlayer = model.getCurrentPlayer();

        //TODO: qui o lato client controllare che l'input sia valido (actionIndex)

        Action actionSelected = currentPlayer.getCurrentValidActionsList().get(actionIndex);

        actionSelected.execute();

        //TODO: ripetizione di codice --> REFACTOR

        if(!currentPlayer.getCurrentBonusActionList().isEmpty()){

            ActionEffect currentBonusAction = model.getCurrentPlayer().getCurrentBonusActionList().removeFirst();

            // temporary bonusMalusOn cost setted in the player
            if(currentBonusAction.getBonusAndMalusOnCost() != null){

                model.getCurrentPlayer().getCurrentBonusActionBonusMalusOnCostList().add(currentBonusAction.getBonusAndMalusOnCost());

            }

            ActionChecker.getInstance().resetActionListExceptPlayer();

            FamilyPawn familyPawn = new FamilyPawn(model.getCurrentPlayer().getPlayerColor(), FamilyPawnType.BONUS, currentBonusAction.getActionValue());

            ActionChecker.getInstance().setValidActionForFamilyPawn(familyPawn, currentBonusAction.getType());

            model.getCurrentPlayer().setPlayerState(PlayerState.BONUSACTION);

        }

        else {

            // TODO: qui ci va la logica (chiamando opportuni metodi di questa classe) del GameController sulla gestione di fine giro

            currentPlayer.setPlayerState(PlayerState.ENDTURN);

            int index = (model.getTurnOrder().indexOf(currentPlayer) + 1) % model.getTurnOrder().size() ;

            // controlli sull'index e eventuali chiamate

            model.setCurrentPlayer(model.getTurnOrder().get(index));

            currentPlayer = model.getCurrentPlayer();

            ActionChecker.getInstance().resetActionList();

            ActionChecker.getInstance().setCurrentPlayer();

            currentPlayer.setPlayerState(PlayerState.DOACTION);
        }

    }

}
