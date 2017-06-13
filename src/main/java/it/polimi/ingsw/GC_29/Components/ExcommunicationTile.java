package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnCost;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnGoods;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.Player.Player;


/**
 * Created by Lorenzotara on 17/05/17.
 */

public class ExcommunicationTile {

    //TODO: malus a fine partita

    private Era era;
    private SpecialBonusAndMalus name;
    private BonusAndMalusOnAction malusOnAction;
    private BonusAndMalusOnGoods malusOnGoods;
    private BonusAndMalusOnCost malusOnCost;
    private Effect effect;

    public ExcommunicationTile(Era era,
                               SpecialBonusAndMalus name,
                               BonusAndMalusOnAction malusOnAction,
                               BonusAndMalusOnGoods malusOnGoods,
                               BonusAndMalusOnCost malusOnCost,
                               Effect effect) {
        this.era = era;
        this.name = name;
        this.malusOnAction = malusOnAction;
        this.malusOnGoods = malusOnGoods;
        this.malusOnCost = malusOnCost;
        this.effect = effect;
    }

    public ExcommunicationTile(Era first, String prova1, Object malusOnAction, BonusAndMalusOnGoods malusOnGoods, Object malusOnCost, String s) {
        //TODO: c'è in test
    }

    public Era getEra() {
        return era;
    }

    public SpecialBonusAndMalus getName() {
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

    public Effect getEffect() {
        return effect;
    }

    public void execute(Player player) throws Exception {
        if (malusOnAction != null) player.getBonusAndMalusOnAction().add(this.getMalusOnAction());
        if (malusOnGoods != null) player.getBonusAndMalusOnGoods().add(this.getMalusOnGoods());
        if (malusOnCost != null) player.getBonusAndMalusOnCost().add(this.getMalusOnCost());
        if (effect != null) effect.execute(player);

    }

    @Override
    public String toString() {
        return "ExcommunicationTile{" + "era=" + era + ", name='" + name + '\'' + ", malusOnAction=" + malusOnAction + ", malusOnGoods=" + malusOnGoods + ", malusOnCost=" + malusOnCost +  '\'' + '}';
    }
}
