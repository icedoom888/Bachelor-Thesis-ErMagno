package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionType;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 19/05/2017.
 */
public class WorkActionBuilder implements ActionBuilder {

    private ActionType actionType;
    private PlayerStatus playerStatus;
    private boolean bonusAction;

    public WorkActionBuilder(ActionType actionType, PlayerStatus playerStatus, boolean bonusAction) {

        this.actionType = actionType;
        this.playerStatus = playerStatus;
        this.bonusAction = bonusAction;
    }

    @Override
    public Action build() {
        return null;
    }
}
