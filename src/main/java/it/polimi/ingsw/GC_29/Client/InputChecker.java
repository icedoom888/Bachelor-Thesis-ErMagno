package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.GameState;
import it.polimi.ingsw.GC_29.Controllers.Input;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Server.RMIViewRemote;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Christian on 14/06/2017.
 */
public class InputChecker {

    private InstructionSet instructionSet;

    private FamilyPawnType familyPawnChosen;

    private int actionIndex;

    private HashMap<Integer,String> validActionList;

    private PlayerState currentPlayerState;

    private Map<FamilyPawnType, Boolean> familyPawnAvailability;

    private GameState currentGameState;

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

        }

        Integer.parseInt(lastWord);

        return null;
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

        lastWord = lastWord.toUpperCase();

        for(FamilyPawnType familyPawnType : FamilyPawnType.values()){

            if(FamilyPawnType.valueOf(lastWord) == familyPawnType && familyPawnAvailability.get(familyPawnType)){

                this.familyPawnChosen = familyPawnType;

                return "use family pawn";
            }
        }

        return "invalid input";
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

    public void setValidActionList(HashMap<Integer,String> validActionList) {
        this.validActionList = validActionList;
    }
}
