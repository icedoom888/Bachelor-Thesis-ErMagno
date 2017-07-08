package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import it.polimi.ingsw.GC_29.Client.Instruction;
import it.polimi.ingsw.GC_29.Model.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Query.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class CommonOutSocket implements InputInterfaceGUI{

    private ObjectOutputStream socketOut;
    private Map<String, Integer> activatedCardMap;
    private List<Integer> councilPrivilegeEffectChosenList;
    private InputChecker inputChecker;
    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());

    public CommonOutSocket(ObjectOutputStream socketOut) {
        this.socketOut = socketOut;
    }

    public void sendInput(String inputLine) {

        Query query;

        try {
            // Implements the communication protocol, creating the Actions corresponding to the input of the user

            System.out.println("\n\nINPUT RECEIVED FROM BUTTONS: " + inputLine + "\n\n");

            if (!inputLine.contentEquals("activated cards GUI")
                    && !inputLine.contentEquals("council privileges chosen GUI")
                    && !inputLine.contentEquals("council privileges chosen leader GUI")
                    && !inputLine.contentEquals("use leader cards GUI")) {

                inputLine = inputChecker.checkInput(inputLine);

                System.out.println("\n\n" + inputLine + "\n\n");
            }

            switch (inputLine) {

                case "bonus tile chosen":
                    int bonusTile = inputChecker.getBonusTileChosen();
                    socketOut.writeObject("bonus tile");
                    socketOut.flush();
                    socketOut.writeObject(bonusTile);
                    socketOut.flush();

                    System.out.println("HO INVIATO LA TILE AL SERVER");
                    break;

                case "throw dices":
                    System.out.println("STAI LANCIANDO I DADI");
                    socketOut.writeObject("throw dices");
                    socketOut.flush();
                    break;

                case "skip action":
                    socketOut.writeObject("skip action");
                    socketOut.flush();
                    break;

                case "end turn":
                    System.out.println("STAI CONCLUDENDO IL TURNO");
                    socketOut.writeObject("end turn");
                    socketOut.flush();
                    break;

                case "use family pawn":
                    System.out.println("STAI SCEGLIENDO LA FAMILY PAWN");
                    FamilyPawnType familyPawnChosen = inputChecker.getFamilyPawnChosen();
                    socketOut.writeObject("family pawn chosen");
                    socketOut.flush();
                    socketOut.writeObject(familyPawnChosen);
                    socketOut.flush();
                    break;

                case "see valid action list":
                    query = new GetValidActions();
                    socketOut.writeObject(query);
                    socketOut.flush();
                    break;

                case "see my family pawns":
                    query = new GetFamilyPawnAvailability();
                    socketOut.writeObject(query);
                    socketOut.flush();
                    break;

                case "execute action":
                    int actionIndex = inputChecker.getActionIndex();
                    socketOut.writeObject("execute action");
                    socketOut.flush();
                    socketOut.writeObject(actionIndex);
                    socketOut.flush();
                    break;

                case "use workers":
                    int workers = inputChecker.getWorkersChosen();
                    socketOut.writeObject("number of workers");
                    socketOut.flush();
                    socketOut.writeObject(workers);
                    socketOut.flush();
                    break;

                case "activate card":
                    if(inputChecker.handleCardDecision()){
                        handleNextCard();
                    }
                    break;

                case "effect chosen":
                    inputChecker.addCard();
                    handleNextCard();
                    break;

                case "activated cards GUI":

                    socketOut.writeObject("pay to obtain cards chosen");
                    socketOut.flush();
                    socketOut.writeObject((HashMap<String, Integer>)activatedCardMap);
                    socketOut.flush();
                    break;

                case "cost chosen":

                    int index = inputChecker.getCostChosen();
                    socketOut.writeObject("cost chosen");
                    socketOut.flush();
                    socketOut.writeObject(index);
                    socketOut.flush();
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
                            socketOut.writeObject("council privileges chosen");
                            socketOut.flush();
                            ArrayList<Integer> privilegeChosenList = (ArrayList<Integer>)inputChecker.getCouncilPrivilegeEffectChosenList();
                            socketOut.writeObject(privilegeChosenList);
                            socketOut.flush();
                        }

                        /*else if (inputChecker.getCurrentPlayerState() == PlayerState.DISCARDINGLEADER) {

                            socketOut.writeObject("council privileges chosen leader");
                            socketOut.flush();
                            socketOut.writeObject(inputChecker.getCouncilPrivilegeEffectChosenList());
                            socketOut.flush();
                            socketOut.writeObject(playerColor);
                        }*/
                    }
                    break;

                case "council privileges chosen GUI":

                    socketOut.writeObject("council privileges chosen");
                    socketOut.flush();
                    socketOut.reset();
                    socketOut.writeObject((ArrayList<Integer>)councilPrivilegeEffectChosenList);
                    socketOut.flush();
                    break;

                case "council privileges chosen leader GUI":

                    socketOut.writeObject("council privileges chosen leader");
                    socketOut.flush();
                    socketOut.reset();
                    socketOut.writeObject((ArrayList<Integer>)councilPrivilegeEffectChosenList);
                    socketOut.flush();
                    socketOut.writeObject(inputChecker.getPlayerColor());
                    break;


                case "pray":
                    socketOut.writeObject("pray");
                    socketOut.flush();
                    socketOut.writeObject(inputChecker.getPlayerColor());
                    socketOut.flush();
                    break;

                case "do not pray":
                    socketOut.writeObject("do not pray");
                    socketOut.flush();
                    socketOut.writeObject(inputChecker.getPlayerColor());
                    socketOut.flush();
                    break;

                case "use leader cards":
                    query = new GetAvailableLeaderCards(inputChecker.getPlayerColor());
                    socketOut.writeObject(query);
                    socketOut.flush();
                    query = new LeaderCardsQuery(inputChecker.getPlayerColor());
                    socketOut.writeObject(query);
                    socketOut.flush();
                    break;

                case "use leader cards GUI":
                   socketOut.writeObject("use leader cards GUI");
                    socketOut.flush();
                    socketOut.writeObject(inputChecker.getPlayerColor());
                    socketOut.flush();
                    break;

                case "not use leader card":
                    inputChecker.resetPlayerState();
                    break;

                case "activate leader card":
                    inputChecker.resetPlayerState();
                    index = inputChecker.getLeaderChosenIndex();
                    socketOut.writeObject("activate leader card");
                    socketOut.flush();
                    socketOut.writeObject(index);
                    socketOut.flush();
                    socketOut.writeObject(inputChecker.getPlayerColor());
                    break;


                case "discard leader card":
                    inputChecker.resetPlayerState();
                    index = inputChecker.getLeaderChosenIndex();
                    socketOut.writeObject("discard leader card");
                    socketOut.flush();
                    socketOut.writeObject(index);
                    socketOut.flush();
                    socketOut.writeObject(inputChecker.getPlayerColor());
                    socketOut.flush();
                    break;

                case "see my goodset":
                    query = new GetGoodSet();
                    socketOut.writeObject(query);
                    socketOut.flush();
                    break;

                case "see development card":
                    query = new GetDevelopmentCard(inputChecker.getPlayerCardColor());
                    socketOut.writeObject(query);
                    socketOut.flush();
                    break;

                case "see tower card":
                    query = new GetTowerCard(inputChecker.getTowerCardColor());
                    socketOut.writeObject(query);
                    socketOut.flush();
                    break;

                case "join game":
                    socketOut.writeObject("join game");
                    socketOut.flush();
                    socketOut.writeObject(inputChecker.getPlayerColor());
                    socketOut.flush();
                    System.out.println("SOCKET JOIN GAME INVIATO AL SERVER");
                    break;

                case "help":
                default:
                    handleHelp();
                    break;

            }
        } catch (IOException e1) {
            LOGGER.info((Supplier<String>) e1);
        }


    }

    private void handleNextCard() throws IOException {

        if(inputChecker.nextCard()){
            inputChecker.askActivateCard();
        }
        else {
            socketOut.writeObject("pay to obtain cards chosen");
            socketOut.flush();

            HashMap<String, Integer> getActivateCardMap = ((HashMap<String, Integer>)inputChecker.getActivatedCardMap());
            socketOut.writeObject(getActivateCardMap);
            //socketOut.writeObject(inputChecker.getActivatedCardMap());
            socketOut.flush();
        }

    }

    @Override
    public void sendInput(Map<String, Integer> activatedCardMap) {
        setActivatedCardMap(activatedCardMap);
        sendInput("activated cards GUI");
    }

    @Override
    public void sendInput(List<Integer> councilPrivilegeEffectChosenList, boolean councilPrivilege) {
        setCouncilPrivilegeEffectChosenList(councilPrivilegeEffectChosenList);
        if (councilPrivilege) {
            sendInput("council privileges chosen GUI");
        }
        else sendInput("council privileges chosen leader GUI");
    }


    public void handlePlayerState(PlayerState currentPlayerState) {

        switch (currentPlayerState){

            case DOACTION:

                Query query = new GetFamilyPawnAvailability();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                // Fino a qui ho inviato la query
                // non devo più inviare nulla
                break;

            case BONUSACTION:
            case CHOOSEACTION:

                query = new GetValidActions();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;


            case CHOOSEWORKERS:

                query = new GetCardsForWorkers();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case ACTIVATE_PAY_TO_OBTAIN_CARDS:

                query = new GetPayToObtainCards();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case CHOOSECOST:

                query = new GetPossibleCosts();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case CHOOSE_COUNCIL_PRIVILEGE:

                System.out.println("\n\nASKING FOR THE PRIVILEGES DA COMMONOUT");

                query = new GetCouncilPrivileges();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case CHOOSE_BONUS_TILE:

                query = new GetBonusTile();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case PRAY:

                query = new GetExcommunication();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            default:
                break;

        }
    }


    public void handleHelp(){

        List<Instruction> instructionList = inputChecker.getInstructionSet().getInstructions(inputChecker.getCurrentPlayerState());

        System.out.println("your valid input in this current state are:");

        for (Instruction instruction : instructionList) {

            System.out.println("");
            System.out.println(instruction.getInstruction());

        }
    }




    public ObjectOutputStream getSocketOut() {
        return socketOut;
    }

    public void setSocketOut(ObjectOutputStream socketOut) {
        this.socketOut = socketOut;
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

    public void endGame() {

        try {
            socketOut.writeObject("end game");
            socketOut.flush();
            socketOut.close();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }
}
