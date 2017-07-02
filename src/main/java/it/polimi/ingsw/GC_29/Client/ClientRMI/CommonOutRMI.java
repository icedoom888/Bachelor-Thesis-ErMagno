package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.ClientSocket.CommonView;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import it.polimi.ingsw.GC_29.Client.Instruction;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Query.*;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by Christian on 01/07/2017.
 */
public class CommonOutRMI implements InputInterfaceGUI{

    private InputChecker inputChecker;
    protected PlayerColor playerColor;
    protected RMIViewRemote serverViewStub;
    private Map<String, Integer> activatedCardMap;
    private List<Integer> councilPrivilegeEffectChosenList;

    public CommonOutRMI(){

        inputChecker = new InputChecker();
    }


    public void connectWithServerView(PlayerColor playerColor, RMIViewRemote serverViewStub){
        this.serverViewStub = serverViewStub;
        this.playerColor = playerColor;
        this.inputChecker.setPlayerColor(playerColor);
    }

    public void sendInput(String inputLine) {

        Query query;

        try {
            // Implements the communication protocol, creating the Actions corresponding to the input of the user

            if (!inputLine.contentEquals("activated cards GUI")
                    || !inputLine.contentEquals("council privileges chosen GUI")) {

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
                        serverViewStub.privilegesChosen(inputChecker.getCouncilPrivilegeEffectChosenList());
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

                case "help":
                    handleHelp();
                    break;

                default:
                    handleHelp();
                    break;

            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


    }

    @Override
    public void sendInput(Map<String, Integer> activatedCardMap) {
        setActivatedCardMap(activatedCardMap);
    }

    @Override
    public void sendInput(List<Integer> councilPrivilegeEffectChosenList) {
        setCouncilPrivilegeEffectChosenList(councilPrivilegeEffectChosenList);
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
