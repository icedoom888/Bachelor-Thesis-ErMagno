package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.SpecialBonusAndMalus;
import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Lorenzotara on 12/06/17.
 */
public class Special implements Effect {

    private SpecialBonusAndMalus specialBonusAndMalus;

    public Special(SpecialBonusAndMalus specialBonusAndMalus) {
        this.specialBonusAndMalus = specialBonusAndMalus;
    }

    @Override
    public void execute(Player status) throws Exception {
        status.getSpecialBonusAndMaluses().add(this.specialBonusAndMalus);
    }
}
