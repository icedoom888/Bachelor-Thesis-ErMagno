package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.GUI.*;
import it.polimi.ingsw.GC_29.Client.GuiChangeHandler;
import it.polimi.ingsw.GC_29.Controllers.Change.*;
import it.polimi.ingsw.GC_29.Model.Era;
import it.polimi.ingsw.GC_29.Model.FamilyPawn;
import it.polimi.ingsw.GC_29.Controllers.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static java.lang.System.exit;

/**
 * Created by Lorenzotara on 23/06/17.
 *
 * ClientInHandlerGUI is similar to ClientInHandlerCLI. It can not receive query
 * that are made directly from the client because GUI is updated for every change
 * during the game. This class extends GuiChangeHandler, that handles the communication
 * with the GUI
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



    /**
     * updateClientGUI() has the goal to understand which kind of change has happened in the model:
     * PlayerStateChange, GameChange...
     * In case of PlayerState change, the new player state will be set into the inputChecker and then
     * the method handlePlayerState(currentPlayerState) will be called.
     */
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
            passes++;
            handleGameState((GameChange)c);
        }

        if(c instanceof ReconnectionChange){

            handleReconnection((ReconnectionChange)c);
        }

        if(c instanceof PlayerDisconnectedChange){

            handlePlayerDisconnected((PlayerDisconnectedChange)c);
        }

        if (c instanceof Excommunicated) {

            for (GuiChangeListener listener : listeners) {
                listener.excommunicate();
            }

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

    /**
     * When the game is set to ENDED, this method handles the end of the game,
     * sending through the commonOutSocket a String "end game" (see commonOutSocket endGame)
     * and after ten seconds it exits.
     * @param currentGameChange
     */
    private void handleGameState(GameChange currentGameChange) {

        GameState currentGameState = currentGameChange.getNewGameState();

        inputChecker.setcurrentGameState(currentGameState);

        if(currentGameState == GameState.ENDED){

            inputChecker.setCurrentPlayerState(PlayerState.ENDGAME);

            String winner = ((EndGame)currentGameChange).getWinner();

            //Lancia schermata
            System.out.println("\n\nLANCIO END GAME DA CLIENT IN HANDLER");
            endGame(winner);
            System.out.println("END GAME LANCIATO");

            isRunning = false;
            commonOutSocket.endGame();

            try {
                socketIn.close();
                System.out.println("SOCKET CLOSED");
            } catch (IOException e) {
                LOGGER.info((Supplier<String>) e);
            }


            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {

                    System.out.println("ESEGUO TASK");
                    System.out.println("I AM THE CLIENT VIEW AND I AM CLOSING THE GAME");


                    exit(0);
                }
            }, (long) 10000);

        }
    }

    /**
     * First the method calls the commonOutSocket to make the right query. Then it fires
     * the playerState change to the GUI in order to update it.
     * Finally it waits for the right answer from the server and then it calls the right method
     * of the super class.
     * @param currentPlayerState
     */
    private void handlePlayerState(PlayerState currentPlayerState) {


        commonOutSocket.handlePlayerState(currentPlayerState);
        firePlayerState(currentPlayerState);


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
