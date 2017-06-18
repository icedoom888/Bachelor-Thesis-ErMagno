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

    private final Instruction seeCardsDevelopment = new Instruction("see my development cards (insert type)",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)), "see my cards \\w+");

    private final Instruction seeCardsLeader = new Instruction("see my leader cards",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction seeCardsExcommunication = new Instruction("see my excommunication cards",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction seeGameBoard = new Instruction("see game board",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction seeTracks = new Instruction("see tracks",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction seePersonalBoard = new Instruction("see personal board",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction seeGameBoardDevelopmentCards = new Instruction("see tower cards (tower type)",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)), "see tower cards \\w+");

    private final Instruction seeGameBoardExcommunicationCards = new Instruction("see excommunication cards",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction seeMyGoodSet = new Instruction("see my goodset",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction seeMyAvailablePawns = new Instruction("see my family pawns",
            new ArrayList<>(Arrays.asList(PlayerState.DOACTION, PlayerState.CHOOSEACTION, PlayerState.BONUSACTION, PlayerState.ENDTURN, PlayerState.WAITING)));

    private final Instruction throwDices = new Instruction("throw dices",
            new ArrayList<>(Arrays.asList(PlayerState.THROWDICES)));

    private final Instruction chooseWorkers = new Instruction("use workers (workers amount)",
            new ArrayList<>(Arrays.asList(PlayerState.CHOOSEWORKERS)), "use workers \\d+");

    private final Instruction activateCard = new Instruction("activate (yes / no)",
            new ArrayList<>(Arrays.asList(PlayerState.ACTIVATE_PAY_TO_OBTAIN_CARDS)), "\\w+");

    private final Instruction chooseEffect = new Instruction("use effect (effect index)",
            new ArrayList<>(Arrays.asList(PlayerState.CHOOSE_EFFECT)), "use effect \\d+");

    private final Instruction choosePrivilege = new Instruction("privilege (effect index)",
            new ArrayList<>(Arrays.asList(PlayerState.CHOOSE_COUNCIL_PRIVILEGE)), "privilege \\d+");

    private final Instruction chooseCost = new Instruction("cost (effect index)",
            new ArrayList<>(Arrays.asList(PlayerState.CHOOSECOST)), "cost \\d+");


    private final List<Instruction> instructionList = new ArrayList<>(Arrays.asList(
            skipAction, endTurn, useFamilyPawn, activateLeaderCard,
            discardLeaderCard, seeValidActionList, doAction, seeCardsDevelopment, seeCardsExcommunication, seeCardsLeader, seeGameBoard,
            seeTracks, seePersonalBoard, seeGameBoardDevelopmentCards, seeGameBoardExcommunicationCards, seeMyGoodSet, seeMyAvailablePawns,
            throwDices, chooseWorkers, activateCard, chooseEffect, choosePrivilege, chooseCost));


    /////////////////////////////////////////////////////////////////////7


    public List<Instruction> getInstructions(PlayerState playerState){

        ArrayList<Instruction> returnList = new ArrayList<>();
        System.out.println(playerState);


        for (Instruction instruction : instructionList) {

            if(instruction.getStateList().contains(playerState)){
                returnList.add(instruction);
            }
        }

        return returnList;

    }

}

