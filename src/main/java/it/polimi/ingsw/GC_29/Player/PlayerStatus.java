package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnGoods;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Christian on 17/05/2017.
 */
public class PlayerStatus {
    private ArrayList<BonusAndMalusOnAction> bonusAndMalusOnActionList;
    private ArrayList<BonusAndMalusOnGoods> bonusAndMalusOnGoods;
    private GoodSet actualGoodSet;
    private HashMap<CardColor, Integer> cardsOwned;
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

    public ArrayList<BonusAndMalusOnGoods> getBonusAndMalusOnGoods(boolean whenObtained) {
        ArrayList<BonusAndMalusOnGoods> newBonusAndMalusOnGoods = new ArrayList<BonusAndMalusOnGoods>();
        for(BonusAndMalusOnGoods singleBonusAndMalusOnGoods: bonusAndMalusOnGoods){
            if(singleBonusAndMalusOnGoods.isWhenObtained()==whenObtained){
                newBonusAndMalusOnGoods.add(singleBonusAndMalusOnGoods);
            }
        }
        return newBonusAndMalusOnGoods;
    }

    public GoodSet getActualGoodSet() {
        return actualGoodSet;
    }

    public void updateGoodSet(GoodSet newGoodSet) {
        this.actualGoodSet.addGoodSet(newGoodSet);
    }

    public HashMap<CardColor, Integer> getCardsOwned() {
        return cardsOwned;
    }

    public void updateCardsOwned(CardColor cardColor){
        /**durante una towerAction nel momento in cui la carta sarà
        *aggiunta alla PersonalBoard dovrà essere chiamato anche questo metodo
         */
        this.cardsOwned.put(cardColor,this.cardsOwned.get(cardColor)+1);
    }

    public int getNumberOfCardsOwned(CardColor cardColor){
        return cardsOwned.get(cardColor);
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

