package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 19/05/2017.

public class MarketActionBuilder implements ActionBuilder{

    private FamilyPawn familyPawnSelected;
    private PlayerStatus playerStatus;

    public MarketActionBuilder( FamilyPawn familyPawnSelected, PlayerStatus playerStatus) {

        this.familyPawnSelected = familyPawnSelected;
        this.playerStatus = playerStatus;
    }

    @Override
    public Action build() {
        return null;
    }
}
 */