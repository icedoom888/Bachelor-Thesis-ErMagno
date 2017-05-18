package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Controllers.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.Controllers.BonusAndMalusOnGoodsObtained;

import java.util.ArrayList;

/**
 * Created by Christian on 17/05/2017.
 */
public class PlayerStatus {

    private ArrayList<BonusAndMalusOnGoodsObtained> bonusAndMalusOnGoodsObtainedList;
    private ArrayList<BonusAndMalusOnAction> bonusAndMalusOnActionsList;
    private GoodSet actualGoodSet;

    public ArrayList<BonusAndMalusOnGoodsObtained> getBonusAndMalusOnGoodsObtainedList() {
        return bonusAndMalusOnGoodsObtainedList;
    }

    public ArrayList<BonusAndMalusOnAction> getBonusAndMalusOnActionsList() {
        return bonusAndMalusOnActionsList;
    }

    public GoodSet getActualGoodSet() {
        return actualGoodSet;
    }
}
