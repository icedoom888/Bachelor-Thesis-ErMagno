package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import it.polimi.ingsw.GC_29.Client.Instruction;
import it.polimi.ingsw.GC_29.Model.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Model.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by Christian on 01/07/2017.
 *
 * CommonOutRMI is a class that is used by clients playing in CLI and
 * clients playing in GUI. It handles all the messages between the client
 * and the server.
 * It implements InputInterfaceGUI
 */
public class CommonOutRMI implements InputInterfaceGUI{

    private InputChecker inputChecker;
    protected PlayerColor playerColor;
    protected RMIViewRemote serverViewStub;
    private Map<String, Integer> activatedCardMap;
    private List<Integer> councilPrivilegeEffectChosenList;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());

    public CommonOutRMI(){

        inputChecker = new InputChecker();
    }


    public void connectWithServerView(PlayerColor playerColor, RMIViewRemote serverViewStub){
        this.serverViewStub = serverViewStub;
        this.playerColor = playerColor;
        this.inputChecker.setPlayerColor(playerColor);
    }

    public void sendInput(String inputLine) {

        try {
            // Implements the communication protocol, creating the Actions corresponding to the input of the user

            if (!inputLine.contentEquals("activated cards GUI")
                    && !inputLine.contentEquals("council privileges chosen GUI")
                    && !inputLine.contentEquals("use leader cards GUI")) {

                inputLine = inputChecker.checkInput(inputLine);
            }

            switch (inputLine) {

                case "bonus tile chosen":
                    int bonusTile = inputChecker.getBonusTileChosen();
                    serverViewStub.bonusTileChosen(bonusTile);
                    break;

                case "throw dices":
                    serverViewStub.throwDices();
                    break;

                case "skip action":
                    serverViewStub.skipAction();
                    break;

                case "end turn":
                    serverViewStub.endTurn();
                    break;

                case "use family pawn":
                    FamilyPawnType familyPawnChosen = inputChecker.getFamilyPawnChosen();
                    serverViewStub.usePawnChosen(familyPawnChosen);
                    break;

                case "see valid action list":
                    inputChecker.printValidActionList();
                    break;

                case "see my family pawns":
                    System.out.println(serverViewStub.getPlayerPawns());
                    break;

                case "execute action":
                    int actionIndex = inputChecker.getActionIndex();
                    serverViewStub.doAction(actionIndex);
                    break;

                case "use workers":
                    int workers = inputChecker.getWorkersChosen();
                    serverViewStub.activateCards(workers);
                    break;

                case "activate card":
                    if(inputChecker.handleCardDecision()){

                        if(inputChecker.nextCard()){

                            inputChecker.askActivateCard();

                        }

                        else {

                            serverViewStub.payToObtainCardChosen(inputChecker.getActivatedCardMap());

                            }
                    }
                    break;

                case "effect chosen":

                    inputChecker.addCard();

                    if(inputChecker.nextCard()){

                        inputChecker.askActivateCard();

                    }
                    else {

                        serverViewStub.payToObtainCardChosen(inputChecker.getActivatedCardMap());

                    }
                    break;

                case "activated cards GUI":

                    serverViewStub.payToObtainCardChosen(activatedCardMap);
                    break;

                case "cost chosen":
                    serverViewStub.chooseCost(inputChecker.getCostChosen());
                    break;

                case "privilege":

                    if(inputChecker.nextParchment()){

                        inputChecker.askWhichPrivilege();

                    }

                    else if(inputChecker.nextPrivilegeEffect()){

                        inputChecker.askWhichPrivilege();
                    }

                    else {
                        if (inputChecker.getCurrentPlayerState() == PlayerState.CHOOSE_COUNCIL_PRIVILEGE) {
                            serverViewStub.privilegesChosen(inputChecker.getCouncilPrivilegeEffectChosenList());
                        }
                    }
                    break;

                case "council privileges chosen GUI":

                    serverViewStub.privilegesChosen(councilPrivilegeEffectChosenList);
                    break;

                case "pray":
                    serverViewStub.pray(true, playerColor);
                    break;

                case "do not pray":
                    serverViewStub.pray(false, playerColor);
                    break;

                case "use leader cards":
                    inputChecker.setLeaderCards(serverViewStub.getLeaderCards(playerColor));
                    inputChecker.setLeaderCardMap(serverViewStub.getLeaderCardsMap(playerColor));
                    inputChecker.showAvailableLeaderCards();
                    break;

                case "use leader cards GUI":
                    serverViewStub.useLeaderCardGui(playerColor);
                    break;

                case "activate leader card":
                    int index = inputChecker.getLeaderChosenIndex();
                    serverViewStub.leaderAction(true, index, playerColor);
                    break;

                case "discard leader card":
                    index = inputChecker.getLeaderChosenIndex();
                    serverViewStub.leaderAction(false, index, playerColor);
                    break;

                case "not use leader card":
                    inputChecker.resetPlayerState();
                    break;

                case "see my goodset":
                    System.out.println(serverViewStub.getPlayerGoodset());
                    break;

                case "see development card":
                    for (String s : serverViewStub.getDevelopmentCard(inputChecker.getPlayerCardColor())) {
                        System.out.println(s);
                    }
                    
                    break;

                case "see tower card":
                    for (String s : serverViewStub.getTowerCards(inputChecker.getTowerCardColor())) {
                        System.out.println(s);
                    }
                    break;

                case "join game":
                    serverViewStub.joinGame(inputChecker.getPlayerColor());
                    System.out.println("SERVER AVVISATO JOIN GAME");
                    break;

                case "see game board":
                    System.out.println(serverViewStub.seeGameBoard());
                    break;

                case "see personal board":
                    System.out.println(serverViewStub.seePersonalBoard(playerColor));

                case "help":
                default:
                    handleHelp();
                    break;

            }
        } catch (RemoteException e) {
            LOGGER.info((Supplier<String>) e);
        }


    }

    @Override
    public void sendInput(Map<String, Integer> activatedCardMap) {
        setActivatedCardMap(activatedCardMap);
        sendInput("activated cards GUI");
    }

    @Override
    public void sendInput(List<Integer> councilPrivilegeEffectChosenList, boolean b) {
        setCouncilPrivilegeEffectChosenList(councilPrivilegeEffectChosenList);
        sendInput("council privileges chosen GUI");
        System.out.println("SEND INPUT CHIAMATO");
    }


    public void handleHelp(){

        List<Instruction> instructionList = inputChecker.getInstructionSet().getInstructions(inputChecker.getCurrentPlayerState());

        System.out.println("your valid input in this current state are:");

        for (Instruction instruction : instructionList) {

            System.out.println("");
            System.out.println(instruction.getInstruction());

        }
    }

    public void setActivatedCardMap(Map<String, Integer> activatedCardMap) {
        this.activatedCardMap = activatedCardMap;
    }

    public void setCouncilPrivilegeEffectChosenList(List<Integer> councilPrivilegeEffectChosenList) {
        this.councilPrivilegeEffectChosenList = councilPrivilegeEffectChosenList;
    }

    public void setInputChecker(InputChecker inputChecker) {
        this.inputChecker = inputChecker;
    }

    public InputChecker getInputChecker() {
        return inputChecker;
    }
}
