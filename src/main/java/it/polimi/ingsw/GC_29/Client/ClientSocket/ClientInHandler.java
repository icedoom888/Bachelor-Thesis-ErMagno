package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Server.Query.GetFamilyPawnAvailability;
import it.polimi.ingsw.GC_29.Server.Query.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

        while(true){

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

                    if (input.contentEquals("Family Pawn Availability")) {
                        Map<FamilyPawnType, Boolean> familyPawnAvailability = (Map<FamilyPawnType, Boolean>)socketIn.readObject();
                        commonView.getInputChecker().setFamilyPawnAvailability(familyPawnAvailability);
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

                    System.out.println("PRINT TEST");

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
        }
    }



    public void printValidActionList() {

        Map<Integer, String> validActionList = commonView.getInputChecker().getValidActionList();

        if(!validActionList.isEmpty()){

            Set<Integer> keys = validActionList.keySet();

            for (Integer key : keys) {
                System.out.println("action index: " + key + ") " + validActionList.get(key));

            }

        }

        else System.out.println("nessuna azione valida");

    }

    private void validActions() {

        try {
            Map<Integer, String> validActions = (Map<Integer, String>)socketIn.readObject();
            commonView.getInputChecker().setValidActionList(validActions);
            printValidActionList();
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
