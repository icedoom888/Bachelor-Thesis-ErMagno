package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;

/**
 * Created by Lorenzotara on 31/05/17.
 */
public class BeginTurnState implements TurnState {

    @Override
    public void executeState(PlayerController wrapper) {

        ActionChecker.getInstance().setCurrentPlayer();

        wrapper.setCurrentTurnState(new ValidationActionTurnState());
    }
}
