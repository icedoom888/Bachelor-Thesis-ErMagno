package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public abstract class DevelopmentCard {
    private String name;
    private String description;
    private Era era;
    private CardColor color;
    private ArrayList<Effect> immediateEffect;
    private ArrayList<Effect> permanentEffect;

    public DevelopmentCard(String name, String description, Era era, CardColor color, ArrayList<Effect> immediateEffect, ArrayList<Effect> permanentEffect) {
        this.name = name;
        this.description = description;
        this.era = era;
        this.color = color;
        this.immediateEffect = immediateEffect;
        this.permanentEffect = permanentEffect;
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

    public CardColor getColor() {
        return color;
    }

    public ArrayList<Effect> getImmediateEffect() {
        return immediateEffect;
    }

    public ArrayList<Effect> getPermanentEffect() {
        return permanentEffect;
    }
}
