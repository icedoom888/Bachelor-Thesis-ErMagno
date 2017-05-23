package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionType;

/**
 * Created by Christian on 21/05/2017.
 */
public class ValidationActionState implements State {

    @Override
    public void executeState(PlayerController wrapper) {

        boolean validAction = false;

        while (!validAction){

            ActionType typeSelected = askForAction();

            wrapper.setActionBuilder(typeSelected);

            wrapper.setCurrentAction(wrapper.getActionBuilder().build());

            validAction = wrapper.getCurrentAction().isPossible();
        }
        wrapper.setCurrentState(new ExecuteActionState());
    }

    private ActionType askForAction() {
        return ActionType.BLUETOWER; // prova
    }
}
