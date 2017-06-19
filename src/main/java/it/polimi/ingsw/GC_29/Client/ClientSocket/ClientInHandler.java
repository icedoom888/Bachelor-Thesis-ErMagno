package it.polimi.ingsw.GC_29.Client.ClientSocket;

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
public class ClientInHandler implements Runnable {

    private ObjectInputStream socketIn;
    private ClientOutHandler clientOutHandler;
    private CommonView commonView;

    public ClientInHandler(ObjectInputStream socketIn) {
        this.socketIn = socketIn;
        this.commonView = new CommonView();
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
        System.out.println(commonView.getPlayerColor());

        if(c instanceof PlayerStateChange){

            commonView.setCurrentPlayerState(((PlayerStateChange)c).getNewPlayerState());

            try {
                handlePlayerState(commonView.getCurrentPlayerState());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            System.out.println("if you want to see your valid input for this current state insert : help");
        }

        if(c instanceof GameChange){
            commonView.setCurrentGameState(((GameChange)c).getNewGameState());
            //TODO: if relation with the church chiedo se questo player è stato scomunicato passando dallo stub e poi printo quello che devo
        }
    }


    private void handlePlayerState(PlayerState currentPlayerState) throws RemoteException {

        commonView.getInputChecker().setCurrentPlayerState(currentPlayerState);

        clientOutHandler.handlePlayerState(currentPlayerState);

        switch (currentPlayerState){

            case DOACTION:

                try {
                    String input = (String)socketIn.readObject();

                    if (input.contentEquals("Get Family Pawns Availability")) {
                        getFamilyPawnsAvailability();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case CHOOSEACTION:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Valid Actions")) {
                        validActions();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            //TODO: inserire gestione altri stati se necessario

            case CHOOSEWORKERS:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Get Cards For Workers")) {
                        getCardsForWorkers();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case ACTIVATE_PAY_TO_OBTAIN_CARDS:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Get Pay To Obtain Cards")) {
                        getPayToObtainCards();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case CHOOSECOST:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Get Possible Costs")) {
                        getPossibleCosts();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case CHOOSE_COUNCIL_PRIVILEGE:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Get Council Privileges")) {
                        getCouncilPrivileges();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

        }
    }

    private void getCouncilPrivileges() {

        try {
            List<Integer> councilPrivileges = (List<Integer>)socketIn.readObject();
            commonView.getInputChecker().setCouncilPrivilegeEffectList(councilPrivileges);
            commonView.getInputChecker().nextPrivilegeEffect();
            commonView.getInputChecker().askWhichPrivilege();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void getPossibleCosts() {

        try {
            Map<Integer, String> possibleCosts = (Map<Integer, String>)socketIn.readObject();
            commonView.getInputChecker().setPossibleCosts(possibleCosts);
            commonView.getInputChecker().askWhichCost();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getPayToObtainCards() {

        try {
            Map<String, HashMap<Integer, String>> payToObtainCards = (Map<String, HashMap<Integer, String>>)socketIn.readObject();
            commonView.getInputChecker().setPayToObtainCardsMap(payToObtainCards);
            commonView.getInputChecker().askActivateCard();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getCardsForWorkers() {

        try {
            Map<Integer, ArrayList<String>> cardsForWorkers = (Map<Integer, ArrayList<String>>)socketIn.readObject();
            commonView.getInputChecker().setPossibleCardsWorkActionMap(cardsForWorkers);
            commonView.getInputChecker().printPossibleCardsWorkAction();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void validActions() {

        try {
            Map<Integer, String> validActions = (Map<Integer, String>)socketIn.readObject();
            commonView.getInputChecker().setValidActionList(validActions);
            commonView.getInputChecker().printValidActionList();
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

            commonView.getInputChecker().setFamilyPawnAvailability(familyPawnsAvailability);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ObjectInputStream getSocketIn() {
        return socketIn;
    }

    public void setClientOutHandler(ClientOutHandler clientOutHandler) {
        this.clientOutHandler = clientOutHandler;
    }

    public void setCommonView(CommonView commonView) {
        this.commonView = commonView;
    }

    public CommonView getCommonView() {
        return commonView;
    }
}
