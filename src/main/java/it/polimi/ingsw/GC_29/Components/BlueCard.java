package it.polimi.ingsw.GC_29.Components;

import java.util.ArrayList;

/**
 * Created by Christian on 17/05/2017.
 */
public class BlueCard extends DevelopmentCard{

    private GoodSet cost;

    public BlueCard(String name, String description, Era era, ArrayList<Effect> immediateEffect, ArrayList<Effect> permanentEffect, GoodSet cost) {
        super(name, description, era, CardColor.BLUE, immediateEffect, permanentEffect);
        this.cost = cost;
    }

    public GoodSet getCardCost() {
        return cost;
    }
}

