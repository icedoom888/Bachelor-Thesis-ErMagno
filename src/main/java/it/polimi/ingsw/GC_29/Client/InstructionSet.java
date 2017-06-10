package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Controllers.PlayerState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Christian on 10/06/2017.
 */
public class InstructionSet {

    private final Instruction skipAction = new Instruction("skip action",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION)));

    private final Instruction endTurn = new Instruction("end turn",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN)));

    private final Instruction useFamilyPawn = new Instruction("use family pawn (insert type)",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION)), "use family pawn \\w+");

    private final Instruction activateLeaderCard = new Instruction("activate leader card (insert index)",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN)), "activate leader card \\d+");

    private final Instruction discardLeaderCard = new Instruction("discard leader card (insert index)",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN)), "discard leader card \\d+");

    private final Instruction seeValidActionList = new Instruction("see valid action list",
            new ArrayList<>(Arrays.asList(PlayerState.CHOOSEACTION, PlayerState.BONUSACTION)));

    private final Instruction doAction = new Instruction("execute action (insert index)",
            new ArrayList<>(Arrays.asList(PlayerState.CHOOSEACTION, PlayerState.BONUSACTION)), "execute action \\d+");

    private final Instruction seeCards = new Instruction("see my cards (insert type: development, leader, excommunication)",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITINGTURN)), "see my cards \\w+");

    private final Instruction seeGameBoard = new Instruction("see game board",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITINGTURN)));

    private final Instruction seeTracks = new Instruction("see tracks",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITINGTURN)));

    private final Instruction seePersonalBoard = new Instruction("see personal board",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITINGTURN)));

    private final Instruction seeGameBoardCards = new Instruction("see game board cards (insert type: development, leader, excommunication)",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITINGTURN)), "see game board cards \\w+");

    private final Instruction seeMyGoodSet = new Instruction("see my goodset",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITINGTURN)));


    private final List<Instruction> instructionList = new ArrayList<>(Arrays.asList(
            skipAction, endTurn, useFamilyPawn, activateLeaderCard,
            discardLeaderCard, seeValidActionList, doAction, seeCards, seeGameBoard,
            seeTracks, seePersonalBoard, seeGameBoardCards, seeMyGoodSet));


    /////////////////////////////////////////////////////////////////////7


    public List<Instruction> getInstructions(PlayerState playerState){

        ArrayList<Instruction> returnList = new ArrayList<>();

        for (Instruction instruction : instructionList) {

            if(instruction.getStateList().contains(playerState)){

                returnList.add(instruction);
            }


            
        }

        return returnList;

    }

}

class Instruction {

    private String instruction;
    private String regex;
    private boolean isRegex = false;
    private List<PlayerState> stateList;

    public Instruction(String instruction, List<PlayerState> stateList){

        this.instruction = instruction;
        this.stateList = new ArrayList<>(stateList);
    }

    public Instruction(String instruction, List<PlayerState> stateList, String regex){

        this(instruction, stateList);

        this.regex = regex;

        this.isRegex = true;
    }

    public List<PlayerState> getStateList() {
        return stateList;
    }

    public String getInstruction() {
        return instruction;
    }

    public boolean isRegex() {
        return isRegex;
    }

    public String getRegex() {
        return regex;
    }
}
