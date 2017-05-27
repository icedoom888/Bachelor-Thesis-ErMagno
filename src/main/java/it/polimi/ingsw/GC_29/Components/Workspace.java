package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

/**
 * Created by Lorenzotara on 17/05/17.
 */

//TODO: rivedere costruttore fatto per togliere errori a caso
public class Workspace implements Cleanable {
    private ActionSpace mainField;
    private ActionSpace secondaryField;
    private ZoneType type;

    public Workspace (ZoneType type,
                     int numberOfPlayers) {

        if (type != ZoneType.HARVEST || type != ZoneType.PRODUCTION){
            throw new IllegalArgumentException("Illegal area type: " + type);
        }

        this.type = type;
        this.mainField = new ActionSpace(null,1, new PawnSlot(1,true),true,false);
        if(numberOfPlayers>=3) {
            this.secondaryField = new ActionSpace(
                    new BonusEffect(
                            new BonusAndMalusOnAction(
                                    type,
                                    -3)/*SOLO per questo turno*/,
                            new BonusAndMalusOnGoods(
                                    new GoodSet()),
                            new BonusAndMalusOnCost(type,
                                    new GoodSet(),
                                    new GoodSet(),
                                    true)),
                    1,
                    new PawnSlot(numberOfPlayers,
                            true),
                    false,
                    false);
        }
        else{ this.secondaryField=null;}
    }

    @Override
    public void clean() {

        mainField.clean();

        secondaryField.clean();

    }

    public ActionSpace getMainField() {
        return mainField;
    }

    public ActionSpace getSecondaryField() {
        return secondaryField;
    }

    public ZoneType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Workspace{" + "mainField=" + mainField + ", secondaryField=" + secondaryField + ", type=" + type + '}';
    }
}
