package it.polimi.ingsw.GC_29.Client;

import com.sun.media.jfxmedia.events.PlayerStateEvent;
import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.GameState;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilege;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

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
    private PlayerState playerStateBeforeLeaderCard;
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
    Map<Integer, ObtainEffect> currentParchment;
    private List<Map<Integer, ObtainEffect>> currentParchmentList;

    ///////TOWERACTION VARIABLES////
    private Map<Integer, String> possibleCosts;
    private int costChosen;


    private Map<Integer, String> bonusTileMap;
    private int bonusTileChosen;

    //////LEADERCARD VARIABLES//////
    private Map<Integer, Boolean> leaderCardMap;
    private int leaderChosenIndex;
    private List<String> leaderCards;

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

        leaderCardMap = new HashMap<>();

        leaderCards = new ArrayList<>();

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

                return activateLeaderCard(lastWord);

            case ("discard leader card (insert index)"):

                return discardLeaderCard(lastWord);

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


        if(currentParchment.containsKey(index)){

            councilPrivilegeEffectChosenList.add(index);

            for (Map<Integer, ObtainEffect> councilEffectsMap : currentParchmentList) {

                councilEffectsMap.remove(index);

            }

            return "privilege";
        }

        else {

            return "invalid input";
        }
    }

    private String handleActivateCard(String lastWord) {

        if("yes".equals(lastWord)){

            cardIsToActivate = true;

            return "activate card";
        }
        if("no".equals(lastWord)){
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

    private String activateLeaderCard(String lastWord) {

        int index = Integer.parseInt(lastWord);

        if(leaderCardMap.keySet().contains(index)){

            if(leaderCardMap.get(index)){

                leaderChosenIndex = index;

                return "activate leader card";
            }

            else {

                return "the card cannot be activated";
            }


        }

        else {

            return "invalid input";
        }

    }


    private String discardLeaderCard(String lastWord) {

        int index = Integer.parseInt(lastWord);

        System.out.println("\n\nInput checker leadermap: " + leaderCardMap);

        if(leaderCardMap.keySet().contains(index)){

            leaderChosenIndex = index;

            return "discard leader card";

        }

        else {

            return "invalid input";
        }

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

            for (CouncilPrivilege councilPrivilege : currentCouncilPrivilegeEffect.getParchmentList()) {

                Map<Integer, ObtainEffect> temporaryMap = new HashMap<>();

                for(int index = 0; index < councilPrivilege.getPossibleObtainEffect().size(); index++){

                    ObtainEffect temporaryObtainEffect = councilPrivilege.getPossibleObtainEffect().get(index);

                    temporaryMap.put(index, temporaryObtainEffect);

                }

                currentParchmentList.add(temporaryMap);
            }

            return true;
        }

        else {
            return false;
        }
    }

    public Boolean nextParchment(){

        return !currentParchmentList.isEmpty();
    }

    public void askWhichPrivilege(){

        currentParchment = currentParchmentList.remove(0);

        System.out.println("choose between these privilege effects: ");

        for (Map.Entry<Integer, ObtainEffect> entry : currentParchment.entrySet()) {

            ObtainEffect obtainEffect = entry.getValue();

            System.out.println("privilege " + entry.getKey() + ") " + obtainEffect);

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

        for (Map.Entry<Integer, String> entry : possibleCosts.entrySet()) {

            System.out.println(entry.getKey() + ")   " + entry.getValue());

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

        for (Map.Entry<Integer, String> entry : bonusTileMap.entrySet()) {

            System.out.println("bonus tile index: " + entry.getKey() + ")  " + entry.getValue());


        }

        System.out.println("the valid input is: bonus tile (index)");
    }



    public int getBonusTileChosen() {
        return bonusTileChosen;
    }

    public void setcurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    public String getCurrentPayToObtainCard() {
        return currentPayToObtainCard;
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

    public Map<String, Integer> getActivatedCardMap() {
        return activatedCardMap;
    }

    public int getCurrentPayToObtainEffectIndex() {
        return currentPayToObtainEffectIndex;
    }

    public void setCurrentPayToObtainEffectIndex(int currentPayToObtainEffectIndex) {
        this.currentPayToObtainEffectIndex = currentPayToObtainEffectIndex;
    }

    public void setPayToObtainCardsMap(Map<String, HashMap<Integer, String>> payToObtainCardsMap) {

        this.payToObtainCardsMap = payToObtainCardsMap;

        Set<String> keySet = payToObtainCardsMap.keySet();

        String keyArray[] = keySet.toArray(new String [keySet.size()]);

        payToObtainCardKeys = new ArrayList<>(Arrays.asList(keyArray));

        currentPayToObtainCard = payToObtainCardKeys.remove(0);

    }


    public void setFamilyPawnAvailability(Map<FamilyPawnType,Boolean> familyPawnAvailability) {
        this.familyPawnAvailability = familyPawnAvailability;
    }

    public void setCurrentPlayerState(PlayerState currentPlayerState) {

        playerStateBeforeLeaderCard = this.currentPlayerState;
        this.currentPlayerState = currentPlayerState;

    }

    public PlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public Map<FamilyPawnType,Boolean> getFamilyPawnAvailability() {
        return familyPawnAvailability;
    }

    public int getLeaderChosenIndex() {
        return leaderChosenIndex;
    }

    public void setLeaderCardMap(Map<Integer, Boolean> leaderCardMap){

        System.out.println("SETTING LEADER CARD MAP IN INPUT CHECKER");

        this.leaderCardMap = leaderCardMap;

        System.out.println("\n\nleader map dell'input checker: " + leaderCardMap);
    }

    /*public void showLeaderCardMap(){

        //setCurrentPlayerState(PlayerState.LEADER_CARDS);

        System.out.println("these are your lea");

        for(int index = 0; index < leaderCards.size(); index++){

            System.out.println();
        }
    }*/

    public PlayerState getPlayerStateBeforeLeaderCard() {
        return playerStateBeforeLeaderCard;
    }

    public void setLeaderCards(List<String> leaderCards) {
        this.leaderCards = leaderCards;
    }

    public void showAvailableLeaderCards() {

        playerStateBeforeLeaderCard = currentPlayerState;

        setCurrentPlayerState(PlayerState.LEADER);

        System.out.println("these are your available leader cards");

        for(int index = 0; index < leaderCards.size(); index++){

            System.out.println("index " + index + ") " + leaderCards.get(index));

        }
    }

    public void resetPlayerState() {

        currentPlayerState = playerStateBeforeLeaderCard;
        System.out.println(currentPlayerState);
        handleHelp();
    }
}
