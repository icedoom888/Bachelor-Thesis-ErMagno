package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class DevelopmentCard {
    private String name;
    private String description;
    private Era era;
    private CardCost cardCost;
    private CardColor color;
    private ArrayList<Effect> immediateEffect;
    private ArrayList<Effect> permanentEffect;
    private boolean withActionValue;
    private int actionValue;

    public DevelopmentCard(String name, String description, Era era, CardCost cardCost, CardColor color,
                           ArrayList<Effect> immediateEffect, ArrayList<Effect> permanentEffect, boolean withActionValue, int actionValue) {
        this.name = name;
        this.description = description;
        this.era = era;
        this.cardCost = cardCost;
        this.color = color;
        this.immediateEffect = immediateEffect;
        this.permanentEffect = permanentEffect;
        this.withActionValue = withActionValue;
        this.actionValue = actionValue;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Era getEra() {
        return era;
    }

    public CardCost getCardCost() { // immutable object
        return new CardCost(cardCost);
    }

    public CardColor getColor() {
        return color;
    }

    public ArrayList<Effect> getImmediateEffect() {
        return immediateEffect;
    }

    public ArrayList<Effect> getPermanentEffect() {
        return permanentEffect;
    }

    public boolean isWithActionValue() {
        return withActionValue;
    }

    public int getActionValue() {
        return actionValue;
    }
}
