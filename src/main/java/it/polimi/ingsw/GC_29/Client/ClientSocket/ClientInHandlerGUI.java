package it.polimi.ingsw.GC_29.Client.ClientSocket;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class ClientInHandlerGUI implements Runnable {

    private CommonOut commonOut;
    private ObjectInputStream socketIn;
    private CommonView commonView;

    public ClientInHandlerGUI(ObjectInputStream socketIn) {
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

                System.out.println("Update da Server View arrivato a Client In GUI");

                switch (input) {

                    case "Change":
                        updateClientGUI();
                        break;
                    case "Valid Actions":
                        validActionsGUI();
                        break;
                    case "Get GoodSet":
                        getGoodSetGUI();
                        break;
                    case "Get Development Cards":
                        getCardsGUI();
                        break;
                    case "Get Tower Cards":
                        getCardsGUI();
                        break;
                    case "Get Family Pawns Availability":
                        getFamilyPawnsAvailabilityGUI();
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



    private void updateClientGUI() {

    }

    private void validActionsGUI() {

    }

    private void getGoodSetGUI() {

    }

    private void getCardsGUI() {

    }

    private void getFamilyPawnsAvailabilityGUI() {

    }


    public CommonView getCommonView() {
        return commonView;
    }

    public void setCommonOut(CommonOut commonOut) {
        this.commonOut = commonOut;
    }

    public void setCommonView(CommonView commonView) {

    }
}
