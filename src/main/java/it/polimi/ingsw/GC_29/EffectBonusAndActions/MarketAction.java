package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class MarketAction extends Action {

    private int houseSelected;

    public MarketAction(FamilyPawn pawnSelected, PlayerStatus playerStatus, int houseSelected) {
        super(pawnSelected, ZoneType.MARKET, playerStatus);

        this.houseSelected = houseSelected;
    }

    @Override
    public void execute() {

    }

    @Override
    protected void update() {

    }


}
