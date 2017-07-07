package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Model.FamilyPawn;
import it.polimi.ingsw.GC_29.Model.FamilyPawnType;
import it.polimi.ingsw.GC_29.Model.GoodSet;
import it.polimi.ingsw.GC_29.Controllers.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static java.lang.System.exit;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class ClientInHandlerCLI implements Runnable {

    private ObjectInputStream socketIn;
    private CommonOutSocket commonOutSocket;
    private Boolean isRunning = true;
    private InputChecker inputChecker;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());


    public ClientInHandlerCLI(ObjectInputStream socketIn) {
        this.socketIn = socketIn;
        //this.commonView = new CommonView();
    }


    @Override
    public void run() {

        System.out.println("Client In Running");

        while(isRunning){

            // handles input messages coming from the server, just showing them to the user
            try {
                String input = (String)socketIn.readObject();

                System.out.println("Update da Server View arrivato a Client In");

                System.out.println("\n\nINPUT: " + input);

                switch (input) {

                    case "Change":
                        updateClient();
                        break;
                    case "Valid Actions":
                        validActions();
                        break;
                    case "Get GoodSet":
                        getGoodSet();
                        break;
                    case "Get Development Cards":
                    case "Get Tower Cards":
                        getCards();
                        break;
                    case "Get Family Pawns Availability":
                        getFamilyPawnsAvailability();
                        break;
                    case "Get Bonus Tile":
                        getBonusTiles();
                        break;
                    case "Leader":
                        getAvailableLeaderCards();
                        break;

                    default:
                        break;




                }

            } catch (ClassNotFoundException e) {

                LOGGER.info((Supplier<String>) e);
            } catch (IOException e) {

                LOGGER.info((Supplier<String>) e);
            }
        }
    }




    private void updateClient() {

        Change c = null;
        try {
            c = (Change)socketIn.readObject();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
        System.out.println(c);
        System.out.println(inputChecker.getPlayerColor());

        if(c instanceof PlayerStateChange){

            inputChecker.setCurrentPlayerState(((PlayerStateChange)c).getNewPlayerState());

            try {
                handlePlayerState(inputChecker.getCurrentPlayerState());
            } catch (RemoteException e) {
                LOGGER.info((Supplier<String>) e);
            }

            System.out.println("if you want to see your valid input for this current state insert : help");
        }

        if(c instanceof GameChange){

            handlleGameState((GameChange)c);
        }

        if(c instanceof ReconnectionChange){

            handleReconnection((ReconnectionChange)c);
        }

        if(c instanceof PlayerDisconnectedChange){

            handlePlayerDisconnected((PlayerDisconnectedChange)c);
        }
    }

    private void handlePlayerDisconnected(PlayerDisconnectedChange c) {

        List<String> usernames = c.getUsername();

        System.out.println("THE FOLLOWING PLAYERS ARE DISCONNECTED: ");

        for (String username : usernames) {

            System.out.println(username.toUpperCase());

        }
    }

    private void handleReconnection(ReconnectionChange c) {
        System.out.println("THE FOLLOWING PLAYERS ARE BACK IN THE GAME: ");

        for (String s : c.getReconnectedPlayerUsernames()) {

            System.out.println(s.toUpperCase());
        }
    }

    private void handlleGameState(GameChange currentGameChange) {

        GameState currentGameState = currentGameChange.getNewGameState();

        inputChecker.setcurrentGameState(currentGameState);

        if(currentGameState == GameState.ENDED){

            inputChecker.setCurrentPlayerState(PlayerState.ENDGAME);

            String winner = ((EndGame)currentGameChange).getWinner();

            System.out.println("THE GAME IS ENDED. THE WINNER IS " + winner + "!\n");

            System.out.println("IN A FEW SECONDS THE GAME WILL BE TERMINATED");

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
                    exit(0);
                }
            }, (long) 10000);

        }

        if(currentGameChange instanceof EndMove){

            String username = ((EndMove)currentGameChange).getUsername();

            System.out.println(username.toUpperCase() +"'S MOVE ENDED");

        }

    }


    private void handlePlayerState(PlayerState currentPlayerState) throws RemoteException {

        inputChecker.setCurrentPlayerState(currentPlayerState);

        commonOutSocket.handlePlayerState(currentPlayerState);

        switch (currentPlayerState){

            case DOACTION:

                readVoid();
                getFamilyPawnsAvailability();

                break;

            case BONUSACTION:
            case CHOOSEACTION:


                readVoid();
                validActions();

                break;

            case CHOOSEWORKERS:

                readVoid();
                getCardsForWorkers();

                break;

            case ACTIVATE_PAY_TO_OBTAIN_CARDS:

                readVoid();
                getPayToObtainCards();

                break;

            case CHOOSECOST:

                readVoid();
                getPossibleCosts();

                break;

            case CHOOSE_COUNCIL_PRIVILEGE:

                readVoid();
                getCouncilPrivileges();

                break;

            case CHOOSE_BONUS_TILE:

                readVoid();
                getBonusTiles();

                break;

            case PRAY:
                System.out.println("you have to decide whether to swear fidelity to the pope or not \n the valid input is : pray / do not pray");
                break;

            /*case DISCARDINGLEADER:

                List<Integer> councilPrivileges = new ArrayList<>();
                councilPrivileges.add(1);
                inputChecker.setCouncilPrivilegeEffectList(councilPrivileges);
                inputChecker.nextPrivilegeEffect();
                inputChecker.askWhichPrivilege();
                break;*/
        }
    }

    private void readVoid() {
        try {
            socketIn.readObject();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }

    private void getAvailableLeaderCards() {

        try {
            Map<Integer, Boolean> validLeaders = (Map<Integer, Boolean>)socketIn.readObject();
            inputChecker.setLeaderCardMap(validLeaders);
            String string = (String)socketIn.readObject();

            if (string.contentEquals("Leader Cards")) {
                List<String> leaders = (List<String>)socketIn.readObject();
                inputChecker.setLeaderCards(leaders);
                inputChecker.showAvailableLeaderCards();
            }
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }


    private void getCouncilPrivileges() {

        try {
            List<Integer> councilPrivileges = (List<Integer>)socketIn.readObject();
            inputChecker.setCouncilPrivilegeEffectList(councilPrivileges);
            inputChecker.nextPrivilegeEffect();
            inputChecker.askWhichPrivilege();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }

    }

    private void getPossibleCosts() {

        try {
            Map<Integer, String> possibleCosts = (Map<Integer, String>)socketIn.readObject();
            inputChecker.setPossibleCosts(possibleCosts);
            inputChecker.askWhichCost();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }

    private void getPayToObtainCards() {

        try {
            Map<String, HashMap<Integer, String>> payToObtainCards = (Map<String, HashMap<Integer, String>>)socketIn.readObject();
            inputChecker.setPayToObtainCardsMap(payToObtainCards);
            inputChecker.askActivateCard();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }

    private void getCardsForWorkers() {

        try {
            Map<Integer, ArrayList<String>> cardsForWorkers = (Map<Integer, ArrayList<String>>)socketIn.readObject();
            inputChecker.setPossibleCardsWorkActionMap(cardsForWorkers);
            inputChecker.printPossibleCardsWorkAction();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }


    private void validActions() {

        try {
            Map<Integer, String> validActions = (Map<Integer, String>)socketIn.readObject();
            inputChecker.setValidActionList(validActions);
            inputChecker.printValidActionList();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }


    private void getGoodSet() {

        try {
            GoodSet goodSet = (GoodSet)socketIn.readObject();
            System.out.println(goodSet);
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }

    private void getCards() {

        try {
            List<String> developmentCards = (List<String>)socketIn.readObject();
            for (String developmentCard : developmentCards) {
                System.out.println(developmentCard);
            }
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }


    private void getFamilyPawnsAvailability() {

        try {
            Map<FamilyPawn, Boolean> familyPawns = (Map<FamilyPawn, Boolean>)socketIn.readObject();
            Map<FamilyPawnType, Boolean> familyPawnsAvailability = new EnumMap<>(FamilyPawnType.class);


            for (Map.Entry<FamilyPawn, Boolean> entry : familyPawns.entrySet()) {
                if(entry.getValue()){

                    System.out.println(entry.getKey());
                }

                familyPawnsAvailability.put(entry.getKey().getType(), entry.getValue());
            }

            /*for (FamilyPawn familyPawn : familyPawns.keySet()) {
                if (familyPawns.get(familyPawn)) {
                    System.out.println(familyPawn);
                }
                familyPawnsAvailability.put(familyPawn.getType(), familyPawns.get(familyPawn));
            }*/

            inputChecker.setFamilyPawnAvailability(familyPawnsAvailability);

        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }

    private void getBonusTiles() {

        try {
            Map<Integer, String> bonusTiles = (Map<Integer, String>)socketIn.readObject();
            inputChecker.setBonusTileMap(bonusTiles);
            inputChecker.askWhichBonusTile();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        } catch (ClassNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }


    public ObjectInputStream getSocketIn() {
        return socketIn;
    }

    public void setClientOutHandlerCLI(ClientOutHandlerCLI clientOutHandlerCLI) {
        this.commonOutSocket = clientOutHandlerCLI.getCommonOutSocket();
    }

    public CommonOutSocket getCommonOutSocket() {
        return commonOutSocket;
    }

    public void setCommonOutSocket(CommonOutSocket commonOutSocket) {
        this.commonOutSocket = commonOutSocket;
    }

    public void setInputChecker(InputChecker inputChecker) {
        this.inputChecker = inputChecker;
    }
}
