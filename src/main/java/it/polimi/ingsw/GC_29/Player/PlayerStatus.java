package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Controllers.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.Controllers.BonusAndMalusOnGoodsObtained;

import java.util.ArrayList;

/**
 * Created by Christian on 17/05/2017.
 */
public class PlayerStatus {
    private ArrayList<BonusAndMalusOnAction> bonusAndMalusOnActionList;
    private ArrayList<BonusAndMalusOnGoodsObtained> bonudAndMalusOnGoodsObtainedList;
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

    public ArrayList<BonusAndMalusOnGoodsObtained> getBonudAndMalusOnGoodsObtainedList() {
        return bonudAndMalusOnGoodsObtainedList;
    }

    public GoodSet getActualGoodSet() {
        return actualGoodSet;
    }

    public boolean isBlackPawnAvailability() {
        return blackPawnAvailability;
    }

    public boolean isWhitePawnAvailability() {
        return whitePawnAvailability;
    }

    public boolean isOrangePawnAvailability() {
        return orangePawnAvailability;
    }

    public boolean isNeutralPawnAvailability() {
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
