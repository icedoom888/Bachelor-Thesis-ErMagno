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

    private final Instruction seeCardsDevelopment = new Instruction("see my development cards",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)), "see my cards \\w+");

    private final Instruction seeCardsLeader = new Instruction("see my leader cards",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)), "see my cards \\w+");

    private final Instruction seeCardsExcommunication = new Instruction("see my excommunication cards",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)), "see my cards \\w+");

    private final Instruction seeGameBoard = new Instruction("see game board",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction seeTracks = new Instruction("see tracks",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction seePersonalBoard = new Instruction("see personal board",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction seeGameBoardDevelopmentCards = new Instruction("see development cards",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)), "see game board cards \\w+");

    private final Instruction seeGameBoardExcommunicationCards = new Instruction("see excommunication cards",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)), "see game board cards \\w+");

    private final Instruction seeMyGoodSet = new Instruction("see my goodset",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));


    private final List<Instruction> instructionList = new ArrayList<>(Arrays.asList(
            skipAction, endTurn, useFamilyPawn, activateLeaderCard,
            discardLeaderCard, seeValidActionList, doAction, seeCardsDevelopment, seeCardsExcommunication, seeCardsLeader, seeGameBoard,
            seeTracks, seePersonalBoard, seeGameBoardDevelopmentCards, seeGameBoardExcommunicationCards, seeMyGoodSet));


    /////////////////////////////////////////////////////////////////////7


    public List<Instruction> getInstructions(PlayerState playerState){

        ArrayList<Instruction> returnList = new ArrayList<>();
        System.out.println(playerState);

        if(playerState == null){

            System.out.println(" to start the game insert: start");
        }

        for (Instruction instruction : instructionList) {

            if(instruction.getStateList().contains(playerState)){
                System.out.println("istruzione aggiunta");
                returnList.add(instruction);
            }

            else{

                System.out.println("istruzione non disponibile");
            }


            
        }

        return returnList;

    }

}

