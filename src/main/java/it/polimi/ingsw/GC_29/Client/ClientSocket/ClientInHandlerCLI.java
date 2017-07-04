package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Controllers.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class ClientInHandlerCLI implements Runnable {

    private ObjectInputStream socketIn;
    private CommonOutSocket commonOutSocket;
    //private CommonView commonView;
    private InputChecker inputChecker;

    public ClientInHandlerCLI(ObjectInputStream socketIn) {
        this.socketIn = socketIn;
        //this.commonView = new CommonView();
    }


    @Override
    public void run() {

        System.out.println("Client In Running");

        Boolean b = true;

        while(b){

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
                        getCards();
                        break;
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




                }

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }




    private void updateClient() {

        Change c = null;
        try {
            c = (Change)socketIn.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(c);
        System.out.println(inputChecker.getPlayerColor());

        if(c instanceof PlayerStateChange){

            inputChecker.setCurrentPlayerState(((PlayerStateChange)c).getNewPlayerState());

            try {
                handlePlayerState(inputChecker.getCurrentPlayerState());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            System.out.println("if you want to see your valid input for this current state insert : help");
        }

        if(c instanceof GameChange){
            inputChecker.setcurrentGameState(((GameChange)c).getNewGameState());
            //TODO: if relation with the church chiedo se questo player è stato scomunicato passando dallo stub e poi printo quello che devo
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
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void getCouncilPrivileges() {

        try {
            List<Integer> councilPrivileges = (List<Integer>)socketIn.readObject();
            inputChecker.setCouncilPrivilegeEffectList(councilPrivileges);
            inputChecker.nextPrivilegeEffect();
            inputChecker.askWhichPrivilege();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void getPossibleCosts() {

        try {
            Map<Integer, String> possibleCosts = (Map<Integer, String>)socketIn.readObject();
            inputChecker.setPossibleCosts(possibleCosts);
            inputChecker.askWhichCost();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getPayToObtainCards() {

        try {
            Map<String, HashMap<Integer, String>> payToObtainCards = (Map<String, HashMap<Integer, String>>)socketIn.readObject();
            inputChecker.setPayToObtainCardsMap(payToObtainCards);
            inputChecker.askActivateCard();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getCardsForWorkers() {

        try {
            Map<Integer, ArrayList<String>> cardsForWorkers = (Map<Integer, ArrayList<String>>)socketIn.readObject();
            inputChecker.setPossibleCardsWorkActionMap(cardsForWorkers);
            inputChecker.printPossibleCardsWorkAction();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void validActions() {

        try {
            Map<Integer, String> validActions = (Map<Integer, String>)socketIn.readObject();
            inputChecker.setValidActionList(validActions);
            inputChecker.printValidActionList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void getGoodSet() {

        try {
            GoodSet goodSet = (GoodSet)socketIn.readObject();
            System.out.println(goodSet);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getCards() {

        try {
            List<String> developmentCards = (List<String>)socketIn.readObject();
            for (String developmentCard : developmentCards) {
                System.out.println(developmentCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void getFamilyPawnsAvailability() {

        try {
            Map<FamilyPawn, Boolean> familyPawns = (Map<FamilyPawn, Boolean>)socketIn.readObject();
            HashMap<FamilyPawnType, Boolean> familyPawnsAvailability = new HashMap<>();

            for (FamilyPawn familyPawn : familyPawns.keySet()) {
                if (familyPawns.get(familyPawn)) {
                    System.out.println(familyPawn);
                }
                familyPawnsAvailability.put(familyPawn.getType(), familyPawns.get(familyPawn));
            }

            inputChecker.setFamilyPawnAvailability(familyPawnsAvailability);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getBonusTiles() {

        try {
            Map<Integer, String> bonusTiles = (Map<Integer, String>)socketIn.readObject();
            inputChecker.setBonusTileMap(bonusTiles);
            inputChecker.askWhichBonusTile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
