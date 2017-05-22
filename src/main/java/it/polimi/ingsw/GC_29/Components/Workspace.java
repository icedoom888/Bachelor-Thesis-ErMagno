package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionType;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusEffect;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Workspace implements Cleanable {
    private ActionSpace mainField;
    private ActionSpace secondaryField;
    private ActionType type;

    public Workspace(ActionType type,int numberOfPlayers){
        this.type = type;
        this.mainField = new ActionSpace(null,1, new PawnSlot(1,true),true,false);
        if(numberOfPlayers>=3) {
            this.secondaryField = new ActionSpace(new BonusEffect(new BonusAndMalusOnAction(type,-3)/*SOLO per questo turno*/), 1, new PawnSlot(numberOfPlayers, true), false, false);
        }
        else{ this.secondaryField=null;}
    }

    @Override
    public void clean() {

    }

    public ActionSpace getMainField() {
        return mainField;
    }

    public ActionSpace getSecondaryField() {
        return secondaryField;
    }

    public ActionType getType() {
        return type;
    }
}
