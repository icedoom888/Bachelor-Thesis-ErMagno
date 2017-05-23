package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.PersonalBoard;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnGoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Christian on 17/05/2017.
 */
public class PlayerStatus {

    private boolean blackPawnAvailability;
    private boolean whitePawnAvailability;
    private boolean orangePawnAvailability;
    private boolean neutralPawnAvailability;
    private ArrayList<BonusAndMalusOnAction> bonusAndMalusOnAction;
    private ArrayList<BonusAndMalusOnGoods> bonusAndMalusOnGoods;
    private PersonalBoard personalBoard; // TODO: aggiunto perchè dalle azioni
    private GoodSet actualGoodSet;
    private HashMap<CardColor, Integer> cardsOwned;
    private Action currentAction;
    private LinkedList<ActionEffect> currentBonusActionList;


    public PlayerStatus() {
        bonusAndMalusOnAction = new ArrayList<BonusAndMalusOnAction>();
        bonusAndMalusOnGoods = new ArrayList<BonusAndMalusOnGoods>();
        actualGoodSet = new GoodSet();
        cardsOwned = new HashMap<CardColor, Integer>();
        for(CardColor color : CardColor.values()){
            cardsOwned.put(color,0);
        }
        currentBonusActionList = new LinkedList<ActionEffect>();
    }

    public PlayerStatus(ArrayList<BonusAndMalusOnAction> bonusAndMalusOnAction, ArrayList<BonusAndMalusOnGoods> bonusAndMalusOnGoods, GoodSet actualGoodSet, HashMap<CardColor, Integer> cardsOwned, boolean blackPawnAvailability, boolean whitePawnAvailability, boolean orangePawnAvailability, boolean neutralPawnAvailability) {
        this.bonusAndMalusOnAction = bonusAndMalusOnAction;
        this.bonusAndMalusOnGoods = bonusAndMalusOnGoods;
        this.actualGoodSet = actualGoodSet;
        this.cardsOwned = cardsOwned;
        this.blackPawnAvailability = blackPawnAvailability;
        this.whitePawnAvailability = whitePawnAvailability;
        this.orangePawnAvailability = orangePawnAvailability;
        this.neutralPawnAvailability = neutralPawnAvailability;
    }
/*
    * I'm using this constructor just to make some tests
    *

    public PlayerStatus(GoodSet actualGoodSet) {
        this.actualGoodSet = actualGoodSet;
    }*/

    public ArrayList<BonusAndMalusOnAction> getBonusAndMalusOnAction() {
        return bonusAndMalusOnAction;
    }

    public ArrayList<BonusAndMalusOnGoods> getBonusAndMalusOnGoods() {

        return bonusAndMalusOnGoods;
    }

    public PersonalBoard getPersonalBoard() {
        return personalBoard;
    }

    public GoodSet getActualGoodSet() {

        return actualGoodSet;
    }

    public HashMap<CardColor, Integer> getCardsOwned() {
        return cardsOwned;
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

    public Action getCurrentAction() {

        return currentAction;
    }

    public LinkedList<ActionEffect> getCurrentBonusActionList() {

        return currentBonusActionList;
    }

    public void setCurrentAction(Action currentAction) {

        this.currentAction = currentAction;
    }

    public void updateCardsOwned(CardColor cardColor){
        /**durante una towerAction nel momento in cui la carta sarà
         *aggiunta alla PersonalBoard dovrà essere chiamato anche questo metodo
         */
        this.cardsOwned.put(cardColor,(this.getNumberOfCardsOwned(cardColor)+1));
    }

    public void updateGoodSet(GoodSet newGoodSet) {

        this.actualGoodSet.addGoodSet(newGoodSet);
    }


}

