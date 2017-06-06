package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Distribution.Common.DistributionAdapter;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

import java.util.*;

/**
 * Created by Christian on 17/05/2017.
 */
public class Player {

    //TODO: tra player e player status ci sono due ripetizioni: la personalBoard e il playerColor. Bisogna pensare se passare direttamente il player, dal momento che le azioni accedono al suo colore

    private String playerID;
    private PersonalBoard personalBoard;
    private LeaderCard[] leaderCards;
    private FamilyPawn[] familyPawns;
    private Pawn[] excommunicationPawns;
    private Pawn[] markerDiscs;
    private PlayerColor playerColor;
    private EnumMap<FamilyPawnType, Boolean> familyPawnAvailability;

    private ArrayList<BonusAndMalusOnAction> bonusAndMalusOnAction;
    private ArrayList<BonusAndMalusOnGoods> bonusAndMalusOnGoods;
    private ArrayList<BonusAndMalusOnCost> bonusAndMalusOnCost;

    private GoodSet actualGoodSet;
    private EnumMap<CardColor, Integer> cardsOwned;
    private Action currentAction;
    private LinkedList<ActionEffect> currentBonusActionList;
    private LinkedList<BonusAndMalusOnCost>currentBonusActionBonusMalusOnCostList;
    private DistributionAdapter adapter;


    public Player(String playerID, PlayerColor playerColor, PersonalBoard personalBoard) {

        this.playerID = playerID;
        this.playerColor = playerColor;
        this.personalBoard = personalBoard;

        leaderCards = new LeaderCard[4]; // TODO: decidere se rendere parametrico numero leader card

        familyPawns = new FamilyPawn[] {new FamilyPawn(playerColor, FamilyPawnType.BLACK, 0),
                new FamilyPawn(playerColor, FamilyPawnType.ORANGE, 0),
                new FamilyPawn(playerColor, FamilyPawnType.WHITE, 0),
                new FamilyPawn(playerColor, FamilyPawnType.NEUTRAL, 0)};

        excommunicationPawns = new Pawn[] {new Pawn(playerColor), new Pawn(playerColor), new Pawn(playerColor)};

        markerDiscs = new Pawn[] {new Pawn(playerColor), new Pawn(playerColor), new Pawn(playerColor)};

        bonusAndMalusOnAction = new ArrayList<>();
        bonusAndMalusOnGoods = new ArrayList<>();
        bonusAndMalusOnCost = new ArrayList<>();
        actualGoodSet = new GoodSet();
        cardsOwned = new EnumMap<>(CardColor.class);

        for(CardColor color : CardColor.values()){
            cardsOwned.put(color,0);
        }

        currentBonusActionList = new LinkedList<>();
        currentBonusActionBonusMalusOnCostList = new LinkedList<>();
        this.familyPawnAvailability = new EnumMap<>(FamilyPawnType.class);
        familyPawnAvailability.put(FamilyPawnType.BLACK, true);
        familyPawnAvailability.put(FamilyPawnType.ORANGE, true);
        familyPawnAvailability.put(FamilyPawnType.WHITE, true);
        familyPawnAvailability.put(FamilyPawnType.NEUTRAL, true);

    }

    public PlayerColor getPlayerColor() {

        return playerColor;
    }

    public String getPlayerID() {
        return playerID;
    }

    public LeaderCard[] getLeaderCards() {
        return leaderCards;
    }

    public FamilyPawn[] getFamilyPawns() {
        return familyPawns;
    }

    public FamilyPawn getFamilyPawn(FamilyPawnType familyPawnType){

        for(FamilyPawn familyPawn : familyPawns){

            if(familyPawn.getType() == familyPawnType){
                return familyPawn;
            }
        }

        throw new IllegalArgumentException("wrong type" + familyPawnType);
    }

    public Pawn[] getExcommunicationPawns() {
        return excommunicationPawns;
    }

    public Pawn[] getMarkerDiscs() {
        return markerDiscs;
    }

    public void removeLeaderCard(LeaderCard leaderCard) {
        //TODO: scelta la carta leader da rimuovere, questo metodo la rimuove
    }


    public List<BonusAndMalusOnAction> getBonusAndMalusOnAction() {
        return bonusAndMalusOnAction;
    }

    public List<BonusAndMalusOnGoods> getBonusAndMalusOnGoods() {

        return bonusAndMalusOnGoods;
    }

    public PersonalBoard getPersonalBoard() {
        return personalBoard;
    }

    public GoodSet getActualGoodSet() {

        return actualGoodSet;
    }

    public Map<CardColor, Integer> getCardsOwned() {
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

    public Map<FamilyPawnType, Boolean> getFamilyPawnAvailability() {
        return familyPawnAvailability;
    }

    public List<BonusAndMalusOnCost> getBonusAndMalusOnCost() {
        return bonusAndMalusOnCost;
    }

    public void setFamilyPawnValue(FamilyPawnType familyPawnType, int familyPawnValue) {

        for (FamilyPawn familyPawn : familyPawns) {

            if (familyPawn.getType() == familyPawnType) {
                familyPawn.setActualValue(familyPawnValue);
            }
        }
    }

    @Override
    public String toString() {
        return "Player{" + "playerID='" + playerID + '\'' + ", playerColor=" + playerColor + '}';
    }

    public DistributionAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(DistributionAdapter adapter) {
        this.adapter = adapter;
    }

}


