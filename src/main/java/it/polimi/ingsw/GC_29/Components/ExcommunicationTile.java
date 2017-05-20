package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnGoods;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class ExcommunicationTile {
    private Era era;
    private String name;
    private BonusAndMalusOnAction malusOnAction;
    private BonusAndMalusOnGoods malusOnEffect;
    private String description;

    public ExcommunicationTile(Era era, String name, BonusAndMalusOnAction malusOnAction, BonusAndMalusOnGoods malusOnEffect, String description) {
        this.era = era;
        this.name = name;
        this.malusOnAction = malusOnAction;
        this.malusOnEffect = malusOnEffect;
        this.description = description;
    }

    public Era getEra() {
        return era;
    }

    public String getName() {
        return name;
    }

    public BonusAndMalusOnAction getMalusOnAction() {
        return malusOnAction;
    }

    public BonusAndMalusOnGoods getMalusOnEffect() {
        return malusOnEffect;
    }

    public String getDescription() {
        return description;
    }
}
