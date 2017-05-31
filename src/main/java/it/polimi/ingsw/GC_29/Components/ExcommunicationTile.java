package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnCost;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnGoods;

/**
 * Created by Lorenzotara on 17/05/17.
 */

public class ExcommunicationTile {
    private Era era;
    private String name;
    private BonusAndMalusOnAction malusOnAction;
    private BonusAndMalusOnGoods malusOnGoods;
    private BonusAndMalusOnCost malusOnCost;
    private String description;

    public ExcommunicationTile(Era era,
                               String name,
                               BonusAndMalusOnAction malusOnAction,
                               BonusAndMalusOnGoods malusOnGoods,
                               BonusAndMalusOnCost malusOnCost,
                               String description) {
        this.era = era;
        this.name = name;
        this.malusOnAction = malusOnAction;
        this.malusOnGoods = malusOnGoods;
        this.malusOnCost = malusOnCost;
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

    public BonusAndMalusOnGoods getMalusOnGoods() {
        return malusOnGoods;
    }

    public BonusAndMalusOnCost getMalusOnCost() {
        return malusOnCost;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ExcommunicationTile{" + "era=" + era + ", name='" + name + '\'' + ", malusOnAction=" + malusOnAction + ", malusOnGoods=" + malusOnGoods + ", description='" + description + '\'' + '}';
    }

    /*
    @Override
    public String toString() {
        return "ExcommunicationTile{" + "era=" + era + ", name='" + name + '\'' + ", malusOnAction=" + malusOnAction + ", malusOnGoods=" + malusOnGoods + ", description='" + description + '\'' + '}';
    }*/
}
