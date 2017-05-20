package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class CouncilPalaceAction extends Action {

    public CouncilPalaceAction(FamilyPawn pawnSelected, ActionType actionSelected, int workersSelected, boolean realAction, PlayerStatus playerStatus) {
        super(pawnSelected, actionSelected, workersSelected, realAction, playerStatus);
    }
    /*@Override
    public void execute(PlayerStatus playerStatus) {

    }*/

    @Override
    public void execute() {

    }

    @Override
    protected void update() {

    }
}
