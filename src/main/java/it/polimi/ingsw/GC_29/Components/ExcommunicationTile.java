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


    private String url;
    private Era era;
    private SpecialBonusAndMalus special;
    private BonusAndMalusOnAction malusOnAction;
    private BonusAndMalusOnGoods malusOnGoods;
    private BonusAndMalusOnCost malusOnCost;
    private Effect effect;

    public ExcommunicationTile(String url,
                               Era era,
                               SpecialBonusAndMalus special,
                               BonusAndMalusOnAction malusOnAction,
                               BonusAndMalusOnGoods malusOnGoods,
                               BonusAndMalusOnCost malusOnCost,
                               Effect effect) {
        this.url = url;
        this.era = era;
        this.special = special;
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

    public SpecialBonusAndMalus getSpecial() {
        return special;
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

    public String getUrl() {
        return url;
    }

    public void execute(Player player) throws Exception {
        if (malusOnAction != null) player.getBonusAndMalusOnAction().add(this.getMalusOnAction());
        if (malusOnGoods != null) player.getBonusAndMalusOnGoods().add(this.getMalusOnGoods());
        if (malusOnCost != null) player.getBonusAndMalusOnCost().add(this.getMalusOnCost());
        //if (effect != null) effect.execute(player);
        player.getExcommunicationTiles().add(this);

    }

    @Override
    public String toString() {
        return "ExcommunicationTile{" + "era=" + era + ", special='" + special + '\'' + ", malusOnAction=" + malusOnAction + ", malusOnGoods=" + malusOnGoods + ", malusOnCost=" + malusOnCost +  '\'' + '}';
    }
}
