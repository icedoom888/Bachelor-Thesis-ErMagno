package it.polimi.ingsw.GC_29.Components;

import java.util.ArrayList;

/**
 * Created by Christian on 17/05/2017.
 */
public class PurpleCard extends DevelopmentCard{

    private GoodSet resourcesCost;
    private NecessaryGoodsCost necessaryGoodCost;
    boolean alternativeCost;

    public PurpleCard(String name, String description, Era era, ArrayList<Effect> immediateEffect, ArrayList<Effect> permanentEffect, GoodSet resourcesCost, NecessaryGoodsCost necessaryGoodCost, boolean alternativeCost) {
        super(name, description, era, CardColor.PURPLE, immediateEffect, permanentEffect);
        this.resourcesCost = resourcesCost;
        this.necessaryGoodCost = necessaryGoodCost;
        this.alternativeCost = alternativeCost;
    }

    public GoodSet getResourcesCost() {
        return resourcesCost;
    }

    public NecessaryGoodsCost getNecessaryGoodCost() {
        return necessaryGoodCost;
    }

    public boolean isAlternativeCost() {
        return alternativeCost;
    }
}


