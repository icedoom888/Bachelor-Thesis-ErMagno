package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 19/05/2017.
 */
public class CouncilPalaceActionBuilder implements ActionBuilder {

    private PlayerStatus playerStatus;
    private boolean bonusAction;

    public CouncilPalaceActionBuilder(PlayerStatus playerStatus, boolean bonusAction) {
        this.playerStatus = playerStatus;
        this.bonusAction = bonusAction;
    }

    @Override
    public Action build() {
        return null;
    }
}
