package it.polimi.ingsw.GC_29.Components;

import java.util.ArrayList;

/**
 * Created by Christian on 17/05/2017.
 */
public class GreenCard extends DevelopmentCard{

    private int actionCost;

    public GreenCard(String name, String description, Era era, ArrayList<Effect> immediateEffect, ArrayList<Effect> permanentEffect, int actionCost) {
        super(name, description, era, CardColor.BLUE, immediateEffect, permanentEffect);
        this.actionCost = actionCost;
    }

    public int getActionCost() {
        return actionCost;
    }
}
