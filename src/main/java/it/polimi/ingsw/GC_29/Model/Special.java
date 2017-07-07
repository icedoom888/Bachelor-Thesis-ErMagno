package it.polimi.ingsw.GC_29.Model;

/**
 * Created by Lorenzotara on 12/06/17.
 */
public class Special implements Effect {

    private SpecialBonusAndMalus specialBonusAndMalus;

    public Special(SpecialBonusAndMalus specialBonusAndMalus) {
        this.specialBonusAndMalus = specialBonusAndMalus;
    }

    @Override
    public void execute(Player status) {
        status.getSpecialBonusAndMaluses().add(this.specialBonusAndMalus);
    }
}
