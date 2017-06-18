package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Client.Instruction;
import it.polimi.ingsw.GC_29.Client.InstructionSet;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Query.*;

import java.io.*;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class ClientOutHandler implements Runnable {

    private final BufferedReader inKeyboard;
    private CommonView commonView;

    private ClientInHandler clientInHandler;

    private Boolean logged = false;

    //TODO: settare playerColor

    private ObjectOutputStream socketOut;


    public ClientOutHandler(ObjectOutputStream socketOut) {
        this.socketOut = socketOut;
        this.inKeyboard = new BufferedReader(new InputStreamReader(System.in));
    }



    @Override
    public void run() {

        // Handles output messages, from the client to the server
        System.out.println("CLIENT OUT HANDLER RUNNING");

        Boolean b = true;
        while (b) {

            String inputLine = null;
            try {
                inputLine = inKeyboard.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Input input;
            Query query;
            try {
                // Implements the communication protocol, creating the Actions corresponding to the input of the user
                inputLine = commonView.getInputChecker().checkInput(inputLine);

                switch (inputLine) {

                    case "throw dices":
                        System.out.println("STAI LANCIANDO I DADI");
                        socketOut.writeObject("throw dices");
                        socketOut.flush();
                        break;

                    case "skip action":
                        System.out.println("STAI SKIPPANDO L'AZIONE");
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
                        FamilyPawnType familyPawnChosen = commonView.getInputChecker().getFamilyPawnChosen();
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

                    case "execute action":
                        int actionIndex = commonView.getInputChecker().getActionIndex();
                        socketOut.writeObject("execute action");
                        socketOut.flush();
                        socketOut.writeObject(actionIndex);
                        socketOut.flush();
                        break;

                    case "use workers":
                        int workers = commonView.getInputChecker().getWorkersChosen();
                        socketOut.writeObject("number of workers");
                        socketOut.flush();
                        socketOut.writeObject(workers);
                        socketOut.flush();
                        break;

                    case "activate card":
                        if(commonView.getInputChecker().handleCardDecision()){
                            if(commonView.getInputChecker().nextCard()){
                                commonView.getInputChecker().askActivateCard();
                            }
                            else {
                                socketOut.writeObject("pay to obtain cards chosen");
                                socketOut.flush();
                                socketOut.writeObject(commonView.getInputChecker().getActivatedCardMap());
                                socketOut.flush();
                            }
                        }
                        break;

                    case "effect chosen":
                        commonView.getInputChecker().addCard();
                        if(commonView.getInputChecker().nextCard()){
                            commonView.getInputChecker().askActivateCard();

                        }
                        else {
                            socketOut.writeObject("pay to obtain cards chosen");
                            socketOut.flush();
                            socketOut.writeObject(commonView.getInputChecker().getActivatedCardMap());
                            socketOut.flush();
                        }
                        break;

                    case "cost chosen":
                        int index = commonView.getInputChecker().getCostChosen();
                        socketOut.writeObject("cost chosen");
                        socketOut.flush();
                        socketOut.writeObject(index);
                        socketOut.flush();
                        break;

                    case "privilege":
                        if(commonView.getInputChecker().nextParchment()){
                            commonView.getInputChecker().askWhichPrivilege();
                        }
                        else if(commonView.getInputChecker().nextPrivilegeEffect()){
                            commonView.getInputChecker().askWhichPrivilege();
                        }
                        else {
                            socketOut.writeObject("council privileges chosen");
                            socketOut.flush();
                            socketOut.writeObject(commonView.getInputChecker().getCouncilPrivilegeEffectChosenList());
                        }
                        break;

                    case "I want to pray":
                        PlayerColor playerColor = commonView.getPlayerColor();
                        socketOut.writeObject("i want to pray");
                        socketOut.flush();
                        socketOut.writeObject(playerColor);
                        socketOut.flush();
                        break;

                    case "I don't want to pray":
                        playerColor = commonView.getPlayerColor();
                        socketOut.writeObject("i don't want to pray");
                        socketOut.flush();
                        socketOut.writeObject(playerColor);
                        socketOut.flush();
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

    }






    public void handlePlayerState(PlayerState currentPlayerState) throws RemoteException {

        switch (currentPlayerState){

            case DOACTION:

                Query query = new GetFamilyPawnAvailability();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Fino a qui ho inviato la query
                // non devo più inviare nulla
                break;

            case CHOOSEACTION:

                query = new GetValidActions();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            //TODO: inserire gestione altri stati se necessario


            case CHOOSEWORKERS:

                query = new GetCardsForWorkers();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case ACTIVATE_PAY_TO_OBTAIN_CARDS:

                query = new GetPayToObtainCards();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case CHOOSECOST:

                query = new GetPossibleCosts();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case CHOOSE_COUNCIL_PRIVILEGE:

                query = new GetCouncilPrivileges();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }


    public void handleHelp(){

        List<Instruction> instructionList = commonView.getInputChecker().getInstructionSet().getInstructions(commonView.getCurrentPlayerState());

        System.out.println("your valid input in this current state are:");

        for (Instruction instruction : instructionList) {

            System.out.println("");
            System.out.println(instruction.getInstruction());

        }
    }




    public ObjectOutputStream getSocketOut() {
        return socketOut;
    }

    public void setClientInHandler(ClientInHandler clientInHandler) {
        this.clientInHandler = clientInHandler;
    }

    public void setCommonView(CommonView commonView) {
        this.commonView = commonView;
    }
}
