package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import it.polimi.ingsw.GC_29.Server.Observable;

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

    private ArrayList<ExcommunicationTile> excommunicationTiles;

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

    private final Object lock = new Object();
    private PlayerState lastState;


    public Player(String playerID, PlayerColor playerColor, PersonalBoard personalBoard) {

        this.playerState = PlayerState.WAITING;

        this.playerID = playerID;
        this.playerColor = playerColor;
        this.personalBoard = personalBoard;

        this.leaderCards = new ArrayList<>();

        this.councilPrivilegeEffectList = new ArrayList<>();


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

        excommunicationTiles = new ArrayList<>();

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

        if (this.playerState != PlayerState.SUSPENDED) {

            synchronized (lock) {
                this.playerState = playerState;

                try {
                    notifyObserver(new PlayerStateChange(this.playerState));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void setNotSuspended() {

        synchronized (lock) {
            this.playerState = PlayerState.WAITING;

            try {
                notifyObserver(new PlayerStateChange(this.playerState));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public PlayerState getPlayerState() {

        synchronized (lock) {
            return playerState;
        }
    }

    public String getPlayerID() {
        return playerID;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
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

        actualGoodSet.addGoodSet(newGoodSet);

        notifyObserver(new GoodSetChange(actualGoodSet));


        this.actualGoodSet.updateModelTracks(newGoodSet);
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

        updateLeaderGUI();
    }

    @Override
    public String toString() {
        return "Player{" + "playerID='" + playerID + '\'' + ", playerColor=" + playerColor + '}';
    }

    public List<CouncilPrivilegeEffect> getCouncilPrivilegeEffectList() {
        return councilPrivilegeEffectList;
    }

    public ArrayList<ExcommunicationTile> getExcommunicationTiles() {
        return excommunicationTiles;
    }

    public void setExcommunicationTiles(ArrayList<ExcommunicationTile> excommunicationTiles) {
        this.excommunicationTiles = excommunicationTiles;
    }

    public void updatePersonalBoardGUI(String special, CardColor cardColor) {

        try {
            notifyObserver(new PersonalCardChange(special, cardColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateLeaderGUI() {

        ArrayList<String> leaderUrls = new ArrayList<>();

        for (LeaderCard leaderCard : leaderCards) {
            leaderUrls.add(leaderCard.getUrl());
        }

        try {
            notifyObserver(new LeaderCardChange(leaderUrls));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void answerLeaderCard(boolean isPossible, boolean permanent) {

        try {
            notifyObserver(new ActivateLeader(isPossible, permanent));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLastState(PlayerState lastState) {
        this.lastState = lastState;
    }

    public PlayerState getLastState() {
        return lastState;
    }
}


