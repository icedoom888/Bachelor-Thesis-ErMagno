package it.polimi.ingsw.GC_29.Components;


import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class DevelopmentCard {

    private String name;
    private Era era;
    private CardCost cardCost;
    private CardColor color;
    private ArrayList<Effect> immediateEffect;
    private ArrayList<Effect> permanentEffect;
    private boolean withActionValue;
    private int actionValue;


    public DevelopmentCard(
            String name,
            Era era,
            CardCost cardCost,
            CardColor color,
            ArrayList<Effect> immediateEffect,
            ArrayList<Effect> permanentEffect,
            boolean withActionValue,
            int actionValue) {

        this.name = name;
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

    public Era getEra() {
        return era;
    }

    public CardCost getCardCost() { // immutable object
        return new CardCost(cardCost);
    }

    public CardColor getColor() {
        return color;
    }

    public List<Effect> getImmediateEffect() {
        return immediateEffect;
    }

    public List<Effect> getPermanentEffect() {
        return permanentEffect;
    }

    public boolean isWithActionValue() {
        return withActionValue;
    }

    public int getActionValue() {
        return actionValue;
    }

    @Override
    public String toString() {
        return "DevelopmentCard{"
                + "name='" + name
                + ", era=" + era
                + ", cardCost=" + cardCost
                + ", color=" + color
                + ", immediateEffect=" + immediateEffect
                + ", permanentEffect=" + permanentEffect
                + ", withActionValue=" + withActionValue
                + ", actionValue=" + actionValue
                + '}';
    }
}
