package it.polimi.ingsw.GC_29.Components;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;

import java.util.ArrayList;

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

    @JsonCreator
    public DevelopmentCard(
            @JsonProperty("name") String name,
            @JsonProperty("era") Era era,
            @JsonProperty("cardCost") CardCost cardCost,
            @JsonProperty("color") CardColor color,
            @JsonProperty("immediateEffect") ArrayList<Effect> immediateEffect,
            @JsonProperty("permanentEffect") ArrayList<Effect> permanentEffect,
            @JsonProperty("withActionValue") boolean withActionValue,
            @JsonProperty("actionValue") int actionValue) {

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

    @Override
    public String toString() {
        return "DevelopmentCard{" + "name='" + name + ", era=" + era + ", cardCost=" + cardCost + ", color=" + color + ", immediateEffect=" + immediateEffect + ", permanentEffect=" + permanentEffect + ", withActionValue=" + withActionValue + ", actionValue=" + actionValue + '}';
    }
}
