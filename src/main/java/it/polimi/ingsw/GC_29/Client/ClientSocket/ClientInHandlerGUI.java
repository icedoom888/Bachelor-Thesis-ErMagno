package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.GUI.ChangeViewGUI;
import it.polimi.ingsw.GC_29.Client.GUI.GUIChange;
import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Client.GUI.StartGameChange;
import it.polimi.ingsw.GC_29.Controllers.Change;
import it.polimi.ingsw.GC_29.Controllers.GameChange;
import it.polimi.ingsw.GC_29.Controllers.PlayerStateChange;
import it.polimi.ingsw.GC_29.Server.Observable;
import org.testng.collections.Lists;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class ClientInHandlerGUI implements Runnable {

    private CommonOut commonOut;
    private ObjectInputStream socketIn;
    private CommonView commonView;
    private ChangeViewGUI changeViewGUI;
    private List<GuiChangeListener> listeners = Lists.newArrayList();

    public ClientInHandlerGUI(ObjectInputStream socketIn) {
        this.socketIn = socketIn;
        this.changeViewGUI = new ChangeViewGUI(socketIn, commonView);
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

    /*
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
    */



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

            commonView.setCurrentPlayerState(((PlayerStateChange)c).getNewPlayerState());

            try {

                /*for (GuiChangeListener listener : listeners) {
                    listener.onReadingChange();
                }*/

                //OPPURE

                fireGuiChangeEvent();

                //TODO: probabilmente cancellare
                //changeViewGUI.change(commonView.getCurrentPlayerState());
                //handlePlayerState(commonView.getCurrentPlayerState());
            } catch (/*RemoteException*/ Exception e) {
                e.printStackTrace();
            }

            //System.out.println("if you want to see your valid input for this current state insert : help");
        }

        if(c instanceof GameChange){
            commonView.setCurrentGameState(((GameChange)c).getNewGameState());
            //TODO: if relation with the church chiedo se questo player è stato scomunicato passando dallo stub e poi printo quello che devo
        }

    }

    private void fireGuiChangeEvent() {
        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new StartGameChange());
        }
    }

    public void addListener(GuiChangeListener listener) {
        listeners.add(listener);
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
        this.changeViewGUI.setCommonOut(commonOut);

    }

    public void setCommonView(CommonView commonView) {
        this.commonView = commonView;
        this.changeViewGUI.setCommonView(commonView);
    }

    public ChangeViewGUI getChangeViewGUI() {
        return changeViewGUI;
    }


}
