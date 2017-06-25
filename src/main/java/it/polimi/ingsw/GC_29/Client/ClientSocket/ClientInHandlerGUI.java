package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.GUI.*;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Controllers.*;
import org.testng.collections.Lists;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class ClientInHandlerGUI implements Runnable {

    private CommonOutSocket commonOutSocket;
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

            commonView.setCurrentPlayerState(((PlayerStateChange)c).getNewPlayerState());

            try {

                handlePlayerState(commonView.getCurrentPlayerState());


            } catch (/*RemoteException*/ Exception e) {
                e.printStackTrace();
            }

            //System.out.println("if you want to see your valid input for this current state insert : help");
        }

        if(c instanceof GameChange){
            commonView.setCurrentGameState(((GameChange)c).getNewGameState());
            //TODO: if relation with the church chiedo se questo player è stato scomunicato passando dallo stub e poi printo quello che devo
        }

        if (c instanceof GUIChange) {

            GUIChange guiChange = (GUIChange)c;
            guiChange.perform(listeners);

        }

    }

    private void handlePlayerState(PlayerState currentPlayerState) {

        commonView.getInputChecker().setCurrentPlayerState(currentPlayerState);

        try {

            commonOutSocket.handlePlayerState(currentPlayerState);

            firePlayerState(currentPlayerState);

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        switch (currentPlayerState){

            case DOACTION:

                try {
                    String input = (String)socketIn.readObject();

                    if (input.contentEquals("Get Family Pawns Availability")) {
                        getFamilyPawnsAvailabilityGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case BONUSACTION:
            case CHOOSEACTION:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Valid Actions")) {
                        validActionsGUI();
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
                        getCardsForWorkersGUI();
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
                        getPayToObtainCardsGUI();
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
                        getPossibleCostsGUI();
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
                        getCouncilPrivilegesGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case CHOOSE_BONUS_TILE:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Get Bonus Tile")) {
                        getBonusTilesGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

        }
    }




    private void fireGuiChangeEvent() {
        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new StartGameChange());
        }
    }

    private void firePlayerState(PlayerState currentPlayerState) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new PlayerStateChange(currentPlayerState));
        }

    }


    private void fireValidActions() {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new ValidActionsChange(commonView.getInputChecker().getValidActionList()));
        }
    }

    /*private void fireGoodSet(GoodSet goodSet) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new GoodSetChange(goodSet));
        }
    }*/

    /*private void fireCardsCards(List<String> developmentCards, String string) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new CardsChange(developmentCards, string));
        }

    }*/

    private void fireFamilyPawns(Map<FamilyPawn, Boolean> familyPawns) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new FamilyPawnChange(familyPawns));
        }
    }

    private void fireCardsForWorkers(Map<Integer, ArrayList<String>> cardsForWorkers) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new CardsForWorkersChange(cardsForWorkers));
        }

    }

    private void firePayToObtainCards(Map<String, HashMap<Integer, String>> payToObtainCards) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new PayToObtainCardsChange(payToObtainCards));
        }
    }

    private void firePossibleCosts(Map<Integer, String> possibleCosts) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new PossibleCostsChange(possibleCosts));
        }

    }

    private void fireCouncilPrivileges(List<Integer> councilPrivileges) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new CouncilPrivilegeChange(councilPrivileges));
        }

    }

    private void fireBonusTiles(Map<Integer, String> bonusTiles) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(new BonusTileChange(bonusTiles));
        }

    }











    public void addListener(GuiChangeListener listener) {
        listeners.add(listener);
    }












    private void validActionsGUI() {

        try {

            Map<Integer, String> validActions = (Map<Integer, String>)socketIn.readObject();
            commonView.getInputChecker().setValidActionList(validActions);
            fireValidActions();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /*private void getGoodSetGUI() {

        try {
            GoodSet goodSet = (GoodSet)socketIn.readObject();

            //TODO: PENSARE ALL'AGGIORNAMENTO DELL GOODSET LATO GUI

            fireGoodSet(goodSet);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/



    /*private void getCardsGUI(String string) {

        try {

            List<String> developmentCards = (List<String>)socketIn.readObject();

            fireCardsCards(developmentCards, string);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }*/



    private void getFamilyPawnsAvailabilityGUI() {

        try {

            Map<FamilyPawn, Boolean> familyPawns = (Map<FamilyPawn, Boolean>)socketIn.readObject();
            HashMap<FamilyPawnType, Boolean> familyPawnsAvailability = new HashMap<>();

            for (FamilyPawn familyPawn : familyPawns.keySet()) {
                familyPawnsAvailability.put(familyPawn.getType(), familyPawns.get(familyPawn));
            }

            commonView.getInputChecker().setFamilyPawnAvailability(familyPawnsAvailability);

            fireFamilyPawns(familyPawns);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    private void getCardsForWorkersGUI() {

        try {

            Map<Integer, ArrayList<String>> cardsForWorkers = (Map<Integer, ArrayList<String>>)socketIn.readObject();
            commonView.getInputChecker().setPossibleCardsWorkActionMap(cardsForWorkers);

            fireCardsForWorkers(cardsForWorkers);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void getPayToObtainCardsGUI() {

        try {

            Map<String, HashMap<Integer, String>> payToObtainCards = (Map<String, HashMap<Integer, String>>)socketIn.readObject();
            commonView.getInputChecker().setPayToObtainCardsMap(payToObtainCards);
            //commonView.getInputChecker().askActivateCard();
            //TODO: guarda bene

            firePayToObtainCards(payToObtainCards);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    private void getPossibleCostsGUI() {

        try {

            Map<Integer, String> possibleCosts = (Map<Integer, String>)socketIn.readObject();
            commonView.getInputChecker().setPossibleCosts(possibleCosts);

            firePossibleCosts(possibleCosts);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    private void getCouncilPrivilegesGUI() {

        try {

            List<Integer> councilPrivileges = (List<Integer>)socketIn.readObject();
            commonView.getInputChecker().setCouncilPrivilegeEffectList(councilPrivileges);
            commonView.getInputChecker().nextPrivilegeEffect();
            //commonView.getInputChecker().askWhichPrivilege();
            //TODO: guarda bene

            fireCouncilPrivileges(councilPrivileges);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    private void getBonusTilesGUI() {

        try {

            Map<Integer, String> bonusTiles = (Map<Integer, String>)socketIn.readObject();
            commonView.getInputChecker().setBonusTileMap(bonusTiles);

            fireBonusTiles(bonusTiles);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




    public CommonView getCommonView() {
        return commonView;
    }

    public void setCommonOutSocket(CommonOutSocket commonOutSocket) {
        this.commonOutSocket = commonOutSocket;
        this.changeViewGUI.setCommonOutSocket(commonOutSocket);

    }

    public void setCommonView(CommonView commonView) {
        this.commonView = commonView;
        this.changeViewGUI.setCommonView(commonView);
    }

    public ChangeViewGUI getChangeViewGUI() {
        return changeViewGUI;
    }


}
