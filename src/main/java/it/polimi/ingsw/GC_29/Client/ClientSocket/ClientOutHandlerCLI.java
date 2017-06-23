package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.Instruction;
import it.polimi.ingsw.GC_29.Client.InstructionSet;
import it.polimi.ingsw.GC_29.Components.BonusTile;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Query.*;

import java.io.*;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class ClientOutHandlerCLI implements Runnable {

    private final BufferedReader inKeyboard;
    //private CommonView commonView;

    private ClientInHandlerCLI clientInHandlerCLI;
    private CommonOut commonOut;

    //private Boolean logged = false;

    //TODO: settare playerColor

    //private ObjectOutputStream socketOut;


    public ClientOutHandlerCLI(ObjectOutputStream socketOut) {
        this.commonOut = new CommonOut(socketOut);
        this.inKeyboard = new BufferedReader(new InputStreamReader(System.in));

    }



    @Override
    public void run() {

        // Handles output messages, from the client to the server
        System.out.println("CLIENT OUT HANDLER CLI RUNNING");

        Boolean b = true;
        while (b) {

            String inputLine = null;
            try {
                inputLine = inKeyboard.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            commonOut.sendInput(inputLine);

            /*Input input;
            Query query;
            try {
                // Implements the communication protocol, creating the Actions corresponding to the input of the user
                inputLine = commonView.getInputChecker().checkInput(inputLine);

                switch (inputLine) {

                    case "bonus tile chosen":
                        int bonusTile = commonView.getInputChecker().getBonusTileChosen();
                        socketOut.writeObject("bonus tile");
                        socketOut.flush();
                        socketOut.writeObject(bonusTile);
                        socketOut.flush();
                        break;

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

                    case "see my family pawns":
                        query = new GetFamilyPawns();
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

                    case "pray":
                        PlayerColor playerColor = commonView.getPlayerColor();
                        socketOut.writeObject("pray");
                        socketOut.flush();
                        socketOut.writeObject(playerColor);
                        socketOut.flush();
                        break;

                    case "do not pray":
                        playerColor = commonView.getPlayerColor();
                        socketOut.writeObject("do not pray");
                        socketOut.flush();
                        socketOut.writeObject(playerColor);
                        socketOut.flush();
                        break;

                    case "see my goodset":
                        query = new GetGoodSet();
                        socketOut.writeObject(query);
                        socketOut.flush();
                        break;

                    case "see development card":
                        query = new GetDevelopmentCard(commonView.getInputChecker().getPlayerCardColor());
                        socketOut.writeObject(query);
                        socketOut.flush();
                        break;

                    case "see tower card":
                        query = new GetTowerCard(commonView.getInputChecker().getTowerCardColor());
                        socketOut.writeObject(query);
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
            }*/

        }

    }






    /*public void handlePlayerState(PlayerState currentPlayerState) throws RemoteException {

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

            case BONUSACTION:
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

                break;

            case CHOOSE_BONUS_TILE:

                query = new GetBonusTile();
                try {
                    socketOut.writeObject(query);
                    socketOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

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

    public void setClientInHandlerCLI(ClientInHandlerCLI clientInHandlerCLI) {
        this.clientInHandlerCLI = clientInHandlerCLI;
    }*/

    public void setCommonView(CommonView commonView) {
        this.commonOut.setCommonView(commonView);
    }

    public CommonOut getCommonOut() {
        return commonOut;
    }
}
