package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnGoods;

import java.util.ArrayList;

/**
 * Created by Christian on 17/05/2017.
 */
public class PlayerStatus {
    private ArrayList<BonusAndMalusOnAction> bonusAndMalusOnActionList;
    private ArrayList<BonusAndMalusOnGoods> bonusAndMalusOnGoodsObtainedList;
    private ArrayList<BonusAndMalusOnGoods> bonusAndMalusOnGoodsToPayList;
    private GoodSet actualGoodSet;
    private boolean blackPawnAvailability;
    private boolean whitePawnAvailability;
    private boolean orangePawnAvailability;
    private boolean neutralPawnAvailability;


    /*
    * I'm using this constructor just to make some tests
    *
     */
    public PlayerStatus(GoodSet actualGoodSet) {
        this.actualGoodSet = actualGoodSet;
    }

    public ArrayList<BonusAndMalusOnAction> getBonusAndMalusOnActionList() {
        return bonusAndMalusOnActionList;
    }

    public ArrayList<BonusAndMalusOnGoods> getBonusAndMalusOnGoodsObtainedList() {
        return bonusAndMalusOnGoodsObtainedList;
    }

    public ArrayList<BonusAndMalusOnGoods> getBonusAndMalusOnGoodsToPayList() {
        return bonusAndMalusOnGoodsToPayList;
    }

    public GoodSet getActualGoodSet() {
        return actualGoodSet;
    }

    public boolean isBlackPawnAvailable() {
        return blackPawnAvailability;
    }

    public boolean isWhitePawnAvailable() {
        return whitePawnAvailability;
    }

    public boolean isOrangePawnAvailable() {
        return orangePawnAvailability;
    }

    public boolean isNeutralPawnAvailable() {
        return neutralPawnAvailability;
    }

    public void setBlackPawnAvailability(boolean blackPawnAvailability) {
        this.blackPawnAvailability = blackPawnAvailability;
    }

    public void setWhitePawnAvailability(boolean whitePawnAvailability) {
        this.whitePawnAvailability = whitePawnAvailability;
    }

    public void setOrangePawnAvailability(boolean orangePawnAvailability) {
        this.orangePawnAvailability = orangePawnAvailability;
    }

    public void setNeutralPawnAvailability(boolean neutralPawnAvailability) {
        this.neutralPawnAvailability = neutralPawnAvailability;
    }
}
