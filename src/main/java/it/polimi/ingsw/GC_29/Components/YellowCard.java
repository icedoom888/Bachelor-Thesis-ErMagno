package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;

import java.util.ArrayList;

/**
 * Created by Christian on 17/05/2017.
 */
public class YellowCard extends DevelopmentCard{

    private GoodSet cost;
    private int activateValue;

    public YellowCard(String name, String description, Era era, ArrayList<Effect> immediateEffect, ArrayList<Effect> permanentEffect, GoodSet cost, int activateValue) {
        super(name, description, era, CardColor.YELLOW, immediateEffect, permanentEffect);
        this.cost = cost;
        this.activateValue = activateValue;
    }

    public GoodSet getCost() {
        return cost;
    }

    public int getActivateValue() {
        return activateValue;
    }
}
