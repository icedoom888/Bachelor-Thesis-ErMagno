package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Player.Player;



/**
 * Created by Lorenzotara on 13/06/17.
 */
public class ChangePawnValue implements Effect {

    private int newActionValue;

    public ChangePawnValue(int newActionValue) {
        this.newActionValue = newActionValue;
    }

    @Override
    public void execute(Player status) throws Exception {
        //TODO: comunicazione
        FamilyPawn familyPawnChosen = status.getFamilyPawn(FamilyPawnType.BLACK); //TODO: a caso, serve comunicazione
        familyPawnChosen.setActualValue(newActionValue);
    }
}
