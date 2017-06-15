package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.GameState;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
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

    private Map<FamilyPawnType, Boolean> familyPawnAvailability;

    private GameState currentGameState;

    private CardColor playerCardColor;
    private CardColor towerCardColor;

    public InputChecker(){

        instructionSet = new InstructionSet();

        validActionList = new HashMap<>();

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

        }

        Integer.parseInt(lastWord);

        return null;
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
}
