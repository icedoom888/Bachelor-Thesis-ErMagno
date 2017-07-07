package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
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
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static java.lang.System.exit;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class ClientInHandlerGUI extends GuiChangeHandler implements Runnable {

    private CommonOutSocket commonOutSocket;
    private ObjectInputStream socketIn;
    private Integer passes=0;
    private Boolean isRunning = true;
    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());


    public ClientInHandlerGUI(ObjectInputStream socketIn, CommonOutSocket commonOutSocket) {

        this.socketIn = socketIn;
        this.commonOutSocket = commonOutSocket;
   }

    @Override
    public void run() {

        System.out.println("Client In Running");


        while(isRunning){

            // handles input messages coming from the server
            try {
                String input = (String)socketIn.readObject();

               if (input.contentEquals("Change")) {

                   updateClientGUI();

                }

            } catch (ClassNotFoundException e) {

                LOGGER.info((Supplier<String>) e);
            } catch (IOException e) {

                LOGGER.info((Supplier<String>) e);

            }
        }
    }



    private void updateClientGUI() {

        Change c = null;
        try {
            c = (Change)socketIn.readObject();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
        //System.out.println(c);
        //System.out.println(commonView.getPlayerColor());



        if(c instanceof PlayerStateChange){

            inputChecker.setCurrentPlayerState(((PlayerStateChange)c).getNewPlayerState());
            
            try {

                handlePlayerState(inputChecker.getCurrentPlayerState());


            } catch (/*RemoteException*/ Exception e) {
                LOGGER.info((Supplier<String>) e);
            }

        }

        if(c instanceof GameChange){
            System.out.println(passes);
            passes++;
            System.out.println("GAME CHANGE ARRIVATA");
            handleGameState((GameChange)c);
        }

        if(c instanceof ReconnectionChange){

            handleReconnection((ReconnectionChange)c);
        }

        if(c instanceof PlayerDisconnectedChange){

            handlePlayerDisconnected((PlayerDisconnectedChange)c);
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

    private void handlePlayerDisconnected(PlayerDisconnectedChange c) {

        for (GuiChangeListener listener : listeners) {

            listener.showDisconnectedPlayer(c.getUsername());
        }
    }

    private void handleReconnection(ReconnectionChange c) {

        for (GuiChangeListener listener : listeners) {

            listener.showReconnectedPlayers(c.getReconnectedPlayerUsernames());
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
                LOGGER.info((Supplier<String>) e);
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

                    exit(0);
                }
            }, (long) 10000);

        }
    }

    private void handlePlayerState(PlayerState currentPlayerState) {

       try {

            commonOutSocket.handlePlayerState(currentPlayerState);

            firePlayerState(currentPlayerState);

        } catch (RemoteException e) {
            LOGGER.info((Supplier<String>) e);
        }

        switch (currentPlayerState){

            case DOACTION:

                try {
                    socketIn.readObject();
                    getFamilyPawnsAvailabilityGUI((Map<FamilyPawn, Boolean>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case BONUSACTION:
            case CHOOSEACTION:

                try {
                    socketIn.readObject();
                    validActionsGUI((Map<Integer, String>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case CHOOSEWORKERS:

                try {
                    socketIn.readObject();
                    getCardsForWorkersGUI((Map<Integer, ArrayList<String>>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case ACTIVATE_PAY_TO_OBTAIN_CARDS:

                try {
                    socketIn.readObject();
                    getPayToObtainCardsGUI((Map<String, HashMap<Integer, String>>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case CHOOSECOST:

                try {
                    socketIn.readObject();
                    getPossibleCostsGUI((Map<Integer, String>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case CHOOSE_COUNCIL_PRIVILEGE:

                try {
                    socketIn.readObject();
                    getCouncilPrivilegesGUI((List<Integer>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.info((Supplier<String>) e);
                }

                break;

            case CHOOSE_BONUS_TILE:

                try {
                    socketIn.readObject();
                    getBonusTilesGUI((Map<Integer, String>)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.info((Supplier<String>) e);
                }


                break;

            case PRAY:

                try {
                    socketIn.readObject();
                    getExcommunicationTileUrl((String)socketIn.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.info((Supplier<String>) e);
                }



                break;


        }
    }







    public void setCommonOutSocket(CommonOutSocket commonOutSocket) {
        this.commonOutSocket = commonOutSocket;

    }


    public CommonOutSocket getCommonOutSocket() {
        return commonOutSocket;
    }
}
