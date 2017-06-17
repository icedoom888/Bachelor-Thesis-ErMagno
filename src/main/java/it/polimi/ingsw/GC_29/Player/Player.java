package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Controllers.Change;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Controllers.PlayerStateChange;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import it.polimi.ingsw.GC_29.Server.Observable;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;

/**
 * Created by Christian on 17/05/2017.
 */
public class Player extends Observable<Change> {

    private PlayerState playerState;

    private String playerID;
    private PersonalBoard personalBoard;

    private ArrayList<LeaderCard> leaderCards;
    private HashMap<LeaderCard, Boolean> permanentLeaders;
    private HashMap<LeaderCard, Boolean> oncePerRoundLeaders;

    private List<FamilyPawn> familyPawns;
    private Pawn excommunicationPawns;
    private Pawn markerDiscs;
    private PlayerColor playerColor;
    private EnumMap<FamilyPawnType, Boolean> familyPawnAvailability;

    private ArrayList<BonusAndMalusOnAction> bonusAndMalusOnAction;
    private ArrayList<BonusAndMalusOnGoods> bonusAndMalusOnGoods;
    private ArrayList<BonusAndMalusOnCost> bonusAndMalusOnCost;
    private ArrayList<SpecialBonusAndMalus> specialBonusAndMaluses;

    private GoodSet actualGoodSet;
    private EnumMap<CardColor, Integer> cardsOwned;
    private Action currentAction;
    private LinkedList<ActionEffect> currentBonusActionList;
    private LinkedList<BonusAndMalusOnCost>currentBonusActionBonusMalusOnCostList;

    private ArrayList<Action> currentValidActionsList;
    private List<CouncilPrivilegeEffect> councilPrivilegeEffectList;


    public Player(String playerID, PlayerColor playerColor, PersonalBoard personalBoard) {

        this.playerState = PlayerState.WAITING;

        this.playerID = playerID;
        this.playerColor = playerColor;
        this.personalBoard = personalBoard;

         // TODO: decidere se rendere parametrico numero leader card
        this.oncePerRoundLeaders = new HashMap<>();
        this.permanentLeaders = new HashMap<>();

        this.councilPrivilegeEffectList = new ArrayList<>();

        //TODO: decommentare quando si creano leaderCards

        /*for (LeaderCard leaderCard : this.leaderCards) {
            if (leaderCard.isPermanent()) {
                this.permanentLeaders.put(leaderCard, true);
            }
            else this.oncePerRoundLeaders.put(leaderCard, true);
        }*/

        familyPawns = new ArrayList<>();
        familyPawns.add(new FamilyPawn(playerColor, FamilyPawnType.BLACK, 0));
        familyPawns.add(new FamilyPawn(playerColor, FamilyPawnType.ORANGE, 0));
        familyPawns.add(new FamilyPawn(playerColor, FamilyPawnType.WHITE, 0));
        familyPawns.add(new FamilyPawn(playerColor, FamilyPawnType.NEUTRAL, 0));

        excommunicationPawns = new Pawn(playerColor);

        markerDiscs = new Pawn(playerColor);

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

        currentValidActionsList = new ArrayList<>();

        specialBonusAndMaluses = new ArrayList<>();

    }

    public ArrayList<Action> getCurrentValidActionsList() {
        return currentValidActionsList;
    }

    public void setCurrentValidActionsList(ArrayList<Action> currentValidActionsList) {
        this.currentValidActionsList = currentValidActionsList;
    }

    public PlayerColor getPlayerColor() {

        return playerColor;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;

        try {
            notifyObserver(new PlayerStateChange(this.playerState));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getPlayerID() {
        return playerID;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public HashMap<LeaderCard, Boolean> getPermanentLeaders() {
        return permanentLeaders;
    }

    public HashMap<LeaderCard, Boolean> getOncePerRoundLeaders() {
        return oncePerRoundLeaders;
    }

    public List<FamilyPawn> getFamilyPawns() {
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


    public Pawn getExcommunicationPawns() {
        return excommunicationPawns;
    }

    public Pawn getMarkerDiscs() {
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

    public List<SpecialBonusAndMalus> getSpecialBonusAndMaluses() {
        return specialBonusAndMaluses;
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

    public void updateGoodSet(GoodSet newGoodSet) throws Exception {

        this.actualGoodSet.updateGoodSet(newGoodSet);
    }

    public Map<FamilyPawnType, Boolean> getFamilyPawnAvailability() {
        return familyPawnAvailability;
    }

    public List<BonusAndMalusOnCost> getBonusAndMalusOnCost() {
        return bonusAndMalusOnCost;
    }

    public void setFamilyPawnValue(FamilyPawnType familyPawnType, int familyPawnValue) {

        getFamilyPawn(familyPawnType).setActualValue(familyPawnValue);
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

    @Override
    public String toString() {
        return "Player{" + "playerID='" + playerID + '\'' + ", playerColor=" + playerColor + '}';
    }

    public List<CouncilPrivilegeEffect> getCouncilPrivilegeEffectList() {
        return councilPrivilegeEffectList;
    }
}


