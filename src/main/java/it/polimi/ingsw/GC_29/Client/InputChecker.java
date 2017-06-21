package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.GameState;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilege;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.GUI.GameBoardController;
import it.polimi.ingsw.GC_29.GUI.GameBoardMain;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Christian on 14/06/2017.
 */
public class InputChecker {

    private InstructionSet instructionSet;

    private FamilyPawnType familyPawnChosen;

    private int actionIndex;

    private Map<Integer,String> validActionList;

    private PlayerColor playerColor;

    private PlayerState currentPlayerState;
    private GameState currentGameState;

    private Map<FamilyPawnType, Boolean> familyPawnAvailability;

    ///////WORKACTION VARIABLES////

    private int currentPayToObtainEffectIndex;
    private Boolean cardIsToActivate;
    private Map<Integer, ArrayList<String>> possibleCardsWorkActionMap;
    private List<String> payToObtainCardKeys;
    private Map<String, HashMap<Integer, String >> payToObtainCardsMap;
    private String currentPayToObtainCard;
    private Map<Integer, String> currentPayToObtainEffectsMap;
    private int workersChosen;
    private Map<String, Integer> activatedCardMap;

    private CardColor playerCardColor;
    private CardColor towerCardColor;

    ///////COUNCIL_PRIVILEGE VARIABLES////
    private List<CouncilPrivilegeEffect> councilPrivilegeEffectList;
    CouncilPrivilegeEffect currentCouncilPrivilegeEffect;
    List<Integer> councilPrivilegeEffectChosenList;
    CouncilPrivilege currentParchment;
    private List<CouncilPrivilege> currentParchmentList;

    ///////TOWERACTION VARIABLES////
    private Map<Integer, String> possibleCosts;
    private int costChosen;


    private Map<Integer, String> bonusTileMap;
    private int bonusTileChosen;

    public InputChecker(){

        instructionSet = new InstructionSet();

        validActionList = new HashMap<>();

        familyPawnAvailability = new EnumMap<>(FamilyPawnType.class);

        possibleCardsWorkActionMap = new HashMap<>();

        payToObtainCardsMap = new HashMap<>();

        payToObtainCardKeys = new ArrayList<>();

        currentPayToObtainEffectsMap = new HashMap<>();

        activatedCardMap = new HashMap<>();

        councilPrivilegeEffectList = new ArrayList<>();

        councilPrivilegeEffectChosenList = new ArrayList<>();

        currentParchmentList = new ArrayList<>();

        bonusTileMap = new HashMap<>();

        //TODO dalla view ad ogni update devo settare i valori giusti (pawnAvailability, playerState, gameState)
    }


    public String checkInput(String inputLine) {

        String checkedString = inputLine;

        List<Instruction> instructionList = instructionSet.getInstructions(currentPlayerState);

        for(Instruction instruction : instructionList){

            if(instruction.isRegex()){

                Pattern pattern = Pattern.compile(instruction.getRegex());
                Matcher matcher = pattern.matcher(inputLine);

                if(matcher.find()){

                    checkedString = handleRegex(checkedString, instruction);

                    return checkedString;
                }
            }

            else if(instruction.getInstruction().equals(checkedString)){

                return checkedString;

            }
        }

        checkedString = "invalid input";

        return checkedString;
    }



    private String handleRegex(String inputLine, Instruction instruction) {



        String[] parts = inputLine.split(" ");
        String lastWord = parts[parts.length - 1];

        switch (instruction.getInstruction()){

            case ("use family pawn (insert type)"):

                return handleUseFamilyPawn(lastWord);

            case ("activate leader card (insert index)"):

                return handleLeaderCard(lastWord, true);

            case ("discard leader card (insert index)"):

                return handleLeaderCard(lastWord, false);

            case ("execute action (insert index)"):

                return handleExecuteAction(lastWord);

            case ("see my development cards (insert type)"):

                return handleQueryCards(lastWord, true);

            case("see tower cards (tower type)"):

                return handleQueryCards(lastWord, false);

            case ("use workers (workers amount)"):

                return handleWorkersToActivateCards(lastWord);

            case ("use effect (effect index)"):

                return handleChooseEffect(lastWord);

            case ("activate (yes / no)"):

                return handleActivateCard(lastWord);

            case "privilege (effect index)" :

                return handlePrivilegeEffect(lastWord);

            case "cost (effect index)" :

                return handleCostChosen(lastWord);

            case "bonus tile (index)" :

                return handleBonusTile(lastWord);

        }

        Integer.parseInt(lastWord);

        return null;
    }

    private String handleBonusTile(String lastWord) {

        int index = Integer.parseInt(lastWord);


        if(bonusTileMap.keySet().contains(index)){

            bonusTileChosen = index;

            return "bonus tile chosen";
        }

        else {

            return "invalid input";
        }
    }

    private String handleCostChosen(String lastWord) {

        int index = Integer.parseInt(lastWord);


        if(possibleCosts.keySet().contains(index)){

            costChosen = index;

            return "cost chosen";
        }

        else {

            return "invalid input";
        }

    }

    /**
     * this method add the effect index chosen in the last ArrayList (which is created each time a new privilegeEffect is handled)
     * of councilPrivilegeEffectChosenList. The method removes the chosen ObtainEffect from the other Parchment in the currentParchmentList
     * @param lastWord
     * @return
     */
    private String handlePrivilegeEffect(String lastWord) {

        int index = Integer.parseInt(lastWord);


        if(index < currentParchment.getPossibleObtainEffect().size() && index >= 0){

            councilPrivilegeEffectChosenList.add(index);

            for (CouncilPrivilege councilPrivilege : currentParchmentList) {

                councilPrivilege.getPossibleObtainEffect().remove(index);

            }

            return "privilege";
        }

        else {

            return "invalid input";
        }
    }

    private String handleActivateCard(String lastWord) {

        if(lastWord.equals("yes")){

            cardIsToActivate = true;

            return "activate card";
        }
        if(lastWord.equals("no")){
            cardIsToActivate = false;
            return "activate card";
        }

        return "invalid input";
    }

    private String handleChooseEffect(String lastWord) {

        int index = Integer.parseInt(lastWord);


        if(currentPayToObtainEffectsMap.keySet().contains(index)){

            currentPayToObtainEffectIndex = index;

            return "effect chosen";
        }

        else {

            return "invalid input";
        }
    }

    private String handleWorkersToActivateCards(String lastWord) {

        int index = Integer.parseInt(lastWord);


        if(possibleCardsWorkActionMap.keySet().contains(index)){

            workersChosen = index;

            return "use workers";
        }

        else {

            return "invalid input";
        }

    }

    private String handleQueryCards(String lastWord, Boolean isPlayerCard) {

        String upperCaseWord = lastWord.toUpperCase();

        Class<CardColor> cardColorClass = CardColor.class;

        for (CardColor color : cardColorClass.getEnumConstants()) {
            if(color.name().equals(upperCaseWord)){

                if(isPlayerCard){

                    this.playerCardColor = color;
                    return "see development card";
                }
                else {
                    this.towerCardColor = color;
                    return "see tower card";
                }

            }
        }

        return "invalid input";

        
    }

    private String handleExecuteAction(String lastWord){

        int index = Integer.parseInt(lastWord);

        if(validActionList.keySet().contains(index)){
            actionIndex = index;

            return "execute action";
        }

        else {

            return "invalid input";
        }

    }

    private String handleLeaderCard(String lastWord, boolean b) {

        // TODO: implementa quando fai Leader Card

        return "";
    }

    private String handleUseFamilyPawn(String lastWord){

        String upperCaseWord = lastWord.toUpperCase();

        Class<FamilyPawnType> familyPawnTypeClass = FamilyPawnType.class;

        for (FamilyPawnType familyPawnType : familyPawnTypeClass.getEnumConstants()) {

            if(familyPawnType.name().equals(upperCaseWord)){

                this.familyPawnChosen = familyPawnType;

                return "use family pawn";

            }
        }

        return "invalid input";
    }


    public void handleHelp(){

        List<Instruction> instructionList = instructionSet.getInstructions(currentPlayerState);

        System.out.println("your valid input in this current state are:");

        for (Instruction instruction : instructionList) {

            System.out.println("");
            System.out.println(instruction.getInstruction());

        }
    }


    public void printValidActionList() {

        if(!validActionList.isEmpty()){

            Set<Integer> keys = validActionList.keySet();

            for (Integer key : keys) {

                System.out.println("action index: " + key + ") " + validActionList.get(key));

            }

        }

        else System.out.println("nessuna azione valida");

    }



    public void setFamilyPawnAvailability(Map<FamilyPawnType,Boolean> familyPawnAvailability) {
        this.familyPawnAvailability = familyPawnAvailability;
    }

    public void setCurrentPlayerState(PlayerState currentPlayerState) {
        this.currentPlayerState = currentPlayerState;

    }

    public InstructionSet getInstructionSet() {
        return instructionSet;
    }

    public FamilyPawnType getFamilyPawnChosen() {
        return familyPawnChosen;
    }

    public int getActionIndex() {
        return actionIndex;
    }

    public void setValidActionList(Map<Integer,String> validActionList) {
        this.validActionList = validActionList;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public Map<Integer, String> getValidActionList() {
        return validActionList;
    }

    public CardColor getPlayerCardColor() {
        return playerCardColor;
    }

    public CardColor getTowerCardColor() {
        return towerCardColor;
    }

    public Map<Integer, ArrayList<String>> getPossibleCardsWorkActionMap() {
        return possibleCardsWorkActionMap;
    }

    public void setPossibleCardsWorkActionMap(Map<Integer, ArrayList<String>> possibleCardsWorkActionMap) {
        this.possibleCardsWorkActionMap = possibleCardsWorkActionMap;
    }

    public int getWorkersChosen() {
        return workersChosen;
    }


    public void setPayToObtainCardsMap(Map<String, HashMap<Integer, String>> payToObtainCardsMap) {

        this.payToObtainCardsMap = payToObtainCardsMap;

        Set<String> keySet = payToObtainCardsMap.keySet();

        String keyArray[] = keySet.toArray(new String [keySet.size()]);

        payToObtainCardKeys = new ArrayList<>(Arrays.asList(keyArray));

        currentPayToObtainCard = payToObtainCardKeys.remove(0);

    }

    public void printPossibleCardsWorkAction() {

        Set<Integer> workerskeys = possibleCardsWorkActionMap.keySet();

        for(Integer workersAmount : workerskeys){

            ArrayList<String> tempString = possibleCardsWorkActionMap.get(workersAmount);

            System.out.println(" if you use this workers amount:" + workersAmount + ") " + "you can activate these cards: \n");

            for (String s : tempString) {
                System.out.println(s);
            }
        }
    }

    public void askActivateCard() {

        System.out.println("Do you want to activate this card? \n" + currentPayToObtainCard);

        System.out.println("insert valid input: yes / no");
    }

    public Boolean nextCard(){

        if(!payToObtainCardKeys.isEmpty()) {

            currentPayToObtainCard = payToObtainCardKeys.remove(0);

            setCurrentPlayerState(PlayerState.ACTIVATE_PAY_TO_OBTAIN_CARDS);

            return true;
        }

        else {

            return false;
        }

    }

    public Map<String, Integer> getActivatedCardMap() {
        return activatedCardMap;
    }

    public int getCurrentPayToObtainEffectIndex() {
        return currentPayToObtainEffectIndex;
    }

    public void setCurrentPayToObtainEffectIndex(int currentPayToObtainEffectIndex) {
        this.currentPayToObtainEffectIndex = currentPayToObtainEffectIndex;
    }

    public boolean handleCardDecision() {

        if(cardIsToActivate){

            currentPayToObtainEffectsMap = payToObtainCardsMap.get(currentPayToObtainCard);

            if(currentPayToObtainEffectsMap.size() > 1){

                askWichEffect();

                return false;
            }

            else{

                Set<Integer> keySet = payToObtainCardsMap.get(currentPayToObtainCard).keySet();

                Integer keyArray[] = keySet.toArray(new Integer[keySet.size()]);

                currentPayToObtainEffectIndex = keyArray[0];

                addCard();

                return true;
            }

        }

        return true;
    }

    public void addCard() {

        activatedCardMap.put(currentPayToObtainCard, currentPayToObtainEffectIndex);
    }

    private void askWichEffect() {

        if(!currentPayToObtainEffectsMap.isEmpty()){

            setCurrentPlayerState(PlayerState.CHOOSE_EFFECT);

            Set<Integer> keys = currentPayToObtainEffectsMap.keySet();

            System.out.println("which Pay To Obtain do you want to activate?");

            for (Integer key : keys) {

                System.out.println("effect index: " + key + ") " + currentPayToObtainEffectsMap.get(key));

            }

            System.out.println("insert the effect index:");

        }
    }

    public void setCouncilPrivilegeEffectList(List<Integer> councilPrivilegeEffectList) {

        for (Integer integer : councilPrivilegeEffectList) {

            this.councilPrivilegeEffectList.add(new CouncilPrivilegeEffect(integer));
        }

        councilPrivilegeEffectChosenList.clear();
    }


    public Boolean nextPrivilegeEffect() {

        if (!councilPrivilegeEffectList.isEmpty()) {

            currentCouncilPrivilegeEffect = councilPrivilegeEffectList.remove(0);
            currentParchmentList = currentCouncilPrivilegeEffect.getParchmentList();

            return true;
        } else {
            return false;
        }
    }

    public Boolean nextParchment(){

        return !currentParchmentList.isEmpty();
    }

    public void askWhichPrivilege(){

        currentParchment = currentCouncilPrivilegeEffect.getParchmentList().remove(0);

        System.out.println("choose between these privilege effects: ");

        for (ObtainEffect obtainEffect : currentParchment.getPossibleObtainEffect()) {

            int index = currentParchment.getPossibleObtainEffect().indexOf(obtainEffect);

            System.out.println("privilege " + index + ") " + obtainEffect);
        }

    }

    public List<Integer> getCouncilPrivilegeEffectChosenList() {
        return councilPrivilegeEffectChosenList;
    }

    public void setPossibleCosts(Map<Integer, String> possibleCosts) {
        this.possibleCosts = possibleCosts;
    }

    public void askWhichCost() {
        System.out.println("Choose how to pay between the following costs:\n");
        for (Integer integer : possibleCosts.keySet()) {
            String cost = possibleCosts.get(integer);
            System.out.println(integer + ")   " + cost);
        }
    }

    public int getCostChosen() {
        return costChosen;
    }

    public void setCostChosen(int costChosen) {
        this.costChosen = costChosen;
    }

    public void setBonusTileMap(Map<Integer, String> bonusTileMap) {
        this.bonusTileMap = bonusTileMap;
    }

    public void askWhichBonusTile() {

        System.out.println("Select one of the next bonus tiles");

        for (Integer bonusTileIndex : bonusTileMap.keySet()) {

            System.out.println("bonus tile index: " + bonusTileIndex + ")  " + bonusTileMap.get(bonusTileIndex));

        }

        System.out.println("the valid input is: bonus tile (index)");
    }



    public int getBonusTileChosen() {
        return bonusTileChosen;
    }

    public void setcurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }
}
