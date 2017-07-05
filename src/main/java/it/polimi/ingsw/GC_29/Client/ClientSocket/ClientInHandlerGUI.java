package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.GUI.*;
import it.polimi.ingsw.GC_29.Client.GuiChangeHandler;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Controllers.*;
import org.testng.collections.Lists;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class ClientInHandlerGUI extends GuiChangeHandler implements Runnable {

    private CommonOutSocket commonOutSocket;
    private ObjectInputStream socketIn;
    private Integer passes=0;
    Boolean isRunning = true;
    //private ChangeViewGUI changeViewGUI;
    //private List<GuiChangeListener> listeners = Lists.newArrayList();

    public ClientInHandlerGUI(ObjectInputStream socketIn) {

        this.socketIn = socketIn;
       // this.changeViewGUI = new ChangeViewGUI(socketIn, commonView);
    }

    @Override
    public void run() {
        System.out.println("Client In Running");




        while(isRunning){

            // handles input messages coming from the server
            try {
                String input = (String)socketIn.readObject();

                //System.out.println("Update da Server View arrivato a Client In GUI");

                switch (input) {

                    case "Change":
                        updateClientGUI();
                        break;

                    /*case "Leader":

                        try {

                            System.out.println("Waiting for leaders\n");


                            Map<Integer, Boolean> availableLeaders = (Map<Integer, Boolean>)socketIn.readObject();

                            System.out.println("Leaders received\n");

                            inputChecker.setLeaderCardMap(availableLeaders);

                            //getAvailableLeaders(availableLeaders);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                        break;
                        */

                    /*
                    case "Valid Actions":
                        validActionsGUI();
                        break;
                    case "Get GoodSet":
                        getGoodSetGUI();
                        break;
                    case "Get Development Cards":
                        getCardsGUI("development");
                        break;
                    case "Get Tower Cards":
                        getCardsGUI("tower");
                        break;
                    case "Get Family Pawns Availability":
                        getFamilyPawnsAvailabilityGUI();
                        break;

                        */
                }

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();


                //break;
            }
        }
    }



    private void updateClientGUI() {

        Change c = null;
        try {
            c = (Change)socketIn.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(c);
        //System.out.println(commonView.getPlayerColor());



        if(c instanceof PlayerStateChange){

            inputChecker.setCurrentPlayerState(((PlayerStateChange)c).getNewPlayerState());
            
            try {

                handlePlayerState(inputChecker.getCurrentPlayerState());


            } catch (/*RemoteException*/ Exception e) {
                e.printStackTrace();
            }

        }

        if(c instanceof GameChange){
            System.out.println(passes);
            passes++;
            System.out.println("GAME CHANGE ARRIVATA");
            handleGameState((GameChange)c);
        }

        if (c instanceof GUIChange) {

            GUIChange guiChange = (GUIChange)c;

            if (guiChange instanceof LeadersAvailableGUI) {

                LeadersAvailableGUI leadersAvailableGUI = (LeadersAvailableGUI)guiChange;

                Map<Integer, Boolean> leadersAvailable = leadersAvailableGUI.getLeadersAvailable();

                if (!leadersAvailable.isEmpty()) {
                    inputChecker.setLeaderCardMap(leadersAvailable);
                    inputChecker.setCurrentPlayerState(PlayerState.LEADER);
                    leadersAvailableGUI.perform(listeners);
                }
            }

            else guiChange.perform(listeners);

        }

    }

    private void handleGameState(GameChange currentGameChange) {

        GameState currentGameState = currentGameChange.getNewGameState();

        inputChecker.setcurrentGameState(currentGameState);

        if(currentGameState == GameState.ENDED){

            inputChecker.setCurrentPlayerState(PlayerState.ENDGAME);

            String winner = ((EndGame)currentGameChange).getWinner();

            //Lancia schermata
            System.out.println("\n\nLANCIO END GAME DA CLIENT IN HANDLER");
            endGame(winner);

            isRunning = false;
            commonOutSocket.endGame();

            try {
                socketIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {

                    System.out.println("ESEGUO TASK");
                    System.out.println("I AM THE CLIENT VIEW AND I AM CLOSING THE GAME");
                    /*try {
                        socketIn.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                    System.exit(0);
                }
            }, (long) 10000);

        }
    }

    private void handlePlayerState(PlayerState currentPlayerState) {

        //inputChecker.setCurrentPlayerState(currentPlayerState);

        try {

            commonOutSocket.handlePlayerState(currentPlayerState);

            firePlayerState(currentPlayerState);

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        switch (currentPlayerState){

            case DOACTION:

                try {
                    socketIn.readObject();
                    getFamilyPawnsAvailabilityGUI((Map<FamilyPawn, Boolean>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                /*try {
                    String input = (String)socketIn.readObject();

                    System.out.println("DO action: " + input);


                    if (input.contentEquals("Get Family Pawns Availability")) {
                        getFamilyPawnsAvailabilityGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }*/

                break;

            case BONUSACTION:
            case CHOOSEACTION:

                try {
                    socketIn.readObject();
                    validActionsGUI((Map<Integer, String>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                /*try {
                    String input = (String)socketIn.readObject();

                    System.out.println("VALID ACTIONS: " + input);

                    if (input.contentEquals("Valid Actions")) {
                        validActionsGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }*/

                break;

            case CHOOSEWORKERS:

                try {
                    socketIn.readObject();
                    getCardsForWorkersGUI((Map<Integer, ArrayList<String>>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                /*try {
                    String input = (String)socketIn.readObject();

                    System.out.println("Choose workers: " + input);

                    if (input.contentEquals("Get Cards For Workers")) {
                        getCardsForWorkersGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }*/

                break;

            case ACTIVATE_PAY_TO_OBTAIN_CARDS:

                try {
                    socketIn.readObject();
                    getPayToObtainCardsGUI((Map<String, HashMap<Integer, String>>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                /*try {
                    String input = (String)socketIn.readObject();

                    System.out.println("Pay to obtain: " + input);

                    if (input.contentEquals("Get Pay To Obtain Cards")) {
                        getPayToObtainCardsGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }*/

                break;

            case CHOOSECOST:

                try {
                    socketIn.readObject();
                    getPossibleCostsGUI((Map<Integer, String>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                /*try {
                    String input = (String)socketIn.readObject();

                    System.out.println("choose cost: " + input);

                    if (input.contentEquals("Get Possible Costs")) {
                        getPossibleCostsGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }*/

                break;

            case CHOOSE_COUNCIL_PRIVILEGE:

                try {
                    socketIn.readObject();
                    getCouncilPrivilegesGUI((List<Integer>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                /*try {
                    String input = (String)socketIn.readObject();

                    System.out.println("council privilege: " + input);

                    if (input.contentEquals("Get Council Privileges")) {
                        getCouncilPrivilegesGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }*/

                break;

            case CHOOSE_BONUS_TILE:

                try {
                    socketIn.readObject();
                    getBonusTilesGUI((Map<Integer, String>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                /*try {

                    String input = (String)socketIn.readObject();

                    System.out.println("Bonus Tiles : " + input);

                    if (input.contentEquals("Get Bonus Tile")) {
                        getBonusTilesGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }*/

                break;

            case PRAY:

                try {
                    socketIn.readObject();
                    getExcommunicationTileUrl((String)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                /*try {

                    String input = (String)socketIn.readObject();

                    System.out.println("PRAY : " + input);

                    if (input.contentEquals("Get Excommunication")) {
                        getExcommunicationTileUrl();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }*/

                break;


        }
    }




   /* private void firePray(String excommunicationUrl) {
        for (GuiChangeListener listener : listeners) {
            listener.pray(excommunicationUrl);
        }
    }


    private void firePlayerState(PlayerState currentPlayerState) {

        for (GuiChangeListener listener : listeners) {
            listener.changeState(currentPlayerState);
        }

    }


    private void fireValidActions() {

        for (GuiChangeListener listener : listeners) {

            listener.validActions(commonView.getInputChecker().getValidActionList());

        }
    }


    private void fireFamilyPawns(Map<FamilyPawn, Boolean> familyPawns) {

        for (GuiChangeListener listener : listeners) {
            listener.updatePawns(familyPawns);
        }
    }

    private void fireCardsForWorkers(Map<Integer, ArrayList<String>> cardsForWorkers) {

        for (GuiChangeListener listener : listeners) {
            listener.cardsForWorkers(cardsForWorkers);
        }

    }

    private void firePayToObtainCards(Map<String, HashMap<Integer, String>> payToObtainCard) {

        for (GuiChangeListener listener : listeners) {
            listener.payToObtainCard(payToObtainCard);
        }
    }

    private void firePossibleCosts(Map<Integer, String> possibleCosts) {

        for (GuiChangeListener listener : listeners) {
            listener.possibleCosts(possibleCosts);
        }

    }

    private void fireCouncilPrivileges(List<Integer> councilPrivileges) {

        for (GuiChangeListener listener : listeners) {
            listener.councilPrivilege(councilPrivileges);
        }

    }

    private void fireBonusTiles(Map<Integer, String> bonusTiles) {

        for (GuiChangeListener listener : listeners) {
            listener.bonusTile(bonusTiles);
        }

    }












    public void addListener(GuiChangeListener listener) {
        listeners.add(listener);
    }












    private void validActionsGUI(Map<Integer, String> validActions) {

        //Map<Integer, String> validActions = (Map<Integer, String>)socketIn.readObject();
        commonView.getInputChecker().setValidActionList(validActions);
        fireValidActions();


    }



    private void getFamilyPawnsAvailabilityGUI(Map<FamilyPawn, Boolean> familyPawns) {

        //Map<FamilyPawn, Boolean> familyPawns = (Map<FamilyPawn, Boolean>)socketIn.readObject();

        HashMap<FamilyPawnType, Boolean> familyPawnsAvailability = new HashMap<>();

        for (FamilyPawn familyPawn : familyPawns.keySet()) {

            familyPawnsAvailability.put(familyPawn.getType(), familyPawns.get(familyPawn));
        }

        commonView.getInputChecker().setFamilyPawnAvailability(familyPawnsAvailability);

        fireFamilyPawns(familyPawns);

    }



    private void getCardsForWorkersGUI(Map<Integer, ArrayList<String>> cardsForWorkers) {

        //Map<Integer, ArrayList<String>> cardsForWorkers = (Map<Integer, ArrayList<String>>)socketIn.readObject();
        commonView.getInputChecker().setPossibleCardsWorkActionMap(cardsForWorkers);

        fireCardsForWorkers(cardsForWorkers);

    }


    private void getPayToObtainCardsGUI(Map<String, HashMap<Integer, String>> payToObtainCards) {

        //Map<String, HashMap<Integer, String>> payToObtainCards = (Map<String, HashMap<Integer, String>>)socketIn.readObject();
        commonView.getInputChecker().setPayToObtainCardsMap(payToObtainCards);

        firePayToObtainCards(payToObtainCards);

    }



    private void getPossibleCostsGUI(Map<Integer, String> possibleCosts) {

        //Map<Integer, String> possibleCosts = (Map<Integer, String>)socketIn.readObject();
        commonView.getInputChecker().setPossibleCosts(possibleCosts);

        firePossibleCosts(possibleCosts);

    }



    private void getCouncilPrivilegesGUI(List<Integer> councilPrivileges) {

        //List<Integer> councilPrivileges = (List<Integer>)socketIn.readObject();
        commonView.getInputChecker().setCouncilPrivilegeEffectList(councilPrivileges);
        //commonView.getInputChecker().nextPrivilegeEffect();
        //commonView.getInputChecker().askWhichPrivilege();

        fireCouncilPrivileges(councilPrivileges);

    }



    private void getBonusTilesGUI(Map<Integer, String> bonusTiles) {

        //Map<Integer, String> bonusTiles = (Map<Integer, String>)socketIn.readObject();
        commonView.getInputChecker().setBonusTileMap(bonusTiles);

        fireBonusTiles(bonusTiles);

    }


    private void getExcommunicationTileUrl(String excommunicationUrl) {

        //String excommunicationUrl = (String)socketIn.readObject();

        firePray(excommunicationUrl);

    }*/


    public void setCommonOutSocket(CommonOutSocket commonOutSocket) {
        this.commonOutSocket = commonOutSocket;

    }



}
