package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.PersonalBoard;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Christian on 17/05/2017.
 */
public class PlayerStatus {

    //TODO: tra player e player status ci sono due ripetizioni: la personalBoard e il playerColor. Bisogna pensare se passare direttamente il player, dal momento che le azioni accedono al suo colore

    private PlayerColor playerColor;
    private HashMap<FamilyPawnType, Boolean> familyPawnAvailability;
    private ArrayList<BonusAndMalusOnAction> bonusAndMalusOnAction;
    private ArrayList<BonusAndMalusOnGoods> bonusAndMalusOnGoods;
    private ArrayList<BonusAndMalusOnCost> bonusAndMalusOnCost;
    private PersonalBoard personalBoard;
    private GoodSet actualGoodSet;
    private HashMap<CardColor, Integer> cardsOwned;
    private Action currentAction;
    private LinkedList<ActionEffect> currentBonusActionList;
    private LinkedList<BonusAndMalusOnCost>currentBonusActionBonusMalusOnCostList;


    public PlayerStatus(PlayerColor playerColor, PersonalBoard personalBoard) {
        this.playerColor = playerColor;
        this.personalBoard = personalBoard;
        bonusAndMalusOnAction = new ArrayList<BonusAndMalusOnAction>();
        bonusAndMalusOnGoods = new ArrayList<BonusAndMalusOnGoods>();
        bonusAndMalusOnCost = new ArrayList<BonusAndMalusOnCost>();
        actualGoodSet = new GoodSet();
        cardsOwned = new HashMap<CardColor, Integer>();

        for(CardColor color : CardColor.values()){
            cardsOwned.put(color,0);
        }

        currentBonusActionList = new LinkedList<ActionEffect>();
        currentBonusActionBonusMalusOnCostList = new LinkedList<BonusAndMalusOnCost>();
        this.familyPawnAvailability = new HashMap<FamilyPawnType, Boolean>();
        familyPawnAvailability.put(FamilyPawnType.BLACK, true);
        familyPawnAvailability.put(FamilyPawnType.ORANGE, true);
        familyPawnAvailability.put(FamilyPawnType.WHITE, true);
        familyPawnAvailability.put(FamilyPawnType.NEUTRAL, true);
    }

    public PlayerStatus(PlayerColor playerColor,
                        ArrayList<BonusAndMalusOnAction> bonusAndMalusOnAction,
                        ArrayList<BonusAndMalusOnGoods> bonusAndMalusOnGoods,
                        ArrayList<BonusAndMalusOnCost> bonusAndMalusOnCost,
                        GoodSet actualGoodSet, HashMap<CardColor, Integer> cardsOwned,
                        boolean blackPawnAvailability,
                        boolean whitePawnAvailability,
                        boolean orangePawnAvailability,
                        boolean neutralPawnAvailability) {

        this.playerColor = playerColor;
        this.bonusAndMalusOnAction = bonusAndMalusOnAction;
        this.bonusAndMalusOnGoods = bonusAndMalusOnGoods;
        this.bonusAndMalusOnCost = bonusAndMalusOnCost;
        this.actualGoodSet = actualGoodSet;
        this.cardsOwned = cardsOwned;
        this.familyPawnAvailability = new HashMap<FamilyPawnType, Boolean>();
        familyPawnAvailability.put(FamilyPawnType.BLACK, true);
        familyPawnAvailability.put(FamilyPawnType.ORANGE, true);
        familyPawnAvailability.put(FamilyPawnType.WHITE, true);
        familyPawnAvailability.put(FamilyPawnType.NEUTRAL, true);

    }

    public PlayerColor getPlayerColor() {

        return playerColor;
    }

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

    public Action getCurrentAction() {

        return currentAction;
    }

    public LinkedList<ActionEffect> getCurrentBonusActionList() {

        return currentBonusActionList;
    }

    public LinkedList<BonusAndMalusOnCost> getCurrentBonusActionBonusMalusOnCostList() {
        return currentBonusActionBonusMalusOnCostList;
    }

    public void setPersonalBoard(PersonalBoard personalBoard) {
        this.personalBoard = personalBoard;
    }

    public void setCurrentAction(Action currentAction) {

        this.currentAction = currentAction;
    }

    public void updateCardsOwned(CardColor cardColor){
        /* durante una towerAction nel momento in cui la carta sarà
          aggiunta alla PersonalBoard dovrà essere chiamato anche questo metodo
         */
        this.cardsOwned.put(cardColor,(this.getNumberOfCardsOwned(cardColor)+1));
    }

    public void updateGoodSet(GoodSet newGoodSet) {

        this.actualGoodSet.addGoodSet(newGoodSet);
    }

    public HashMap<FamilyPawnType, Boolean> getFamilyPawnAvailability() {
        return familyPawnAvailability;
    }

    public ArrayList<BonusAndMalusOnCost> getBonusAndMalusOnCost() {
        return bonusAndMalusOnCost;
    }
}

