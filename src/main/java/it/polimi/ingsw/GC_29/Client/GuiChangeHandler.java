package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Model.FamilyPawn;
import it.polimi.ingsw.GC_29.Model.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import org.testng.collections.Lists;

import java.util.*;

/**
 * Created by Christian on 01/07/2017.
 *
 * GuiChangeHandler handles the communication between the ClientInHandlerGUI or
 * ClientRMIViewGUI and the GUI.
 * It has a list of GUIChangeListener that advise the GUI of the changes that happened
 * in the model or answers to the requests of the client
 */
public class GuiChangeHandler {

    protected List<GuiChangeListener> listeners = Lists.newArrayList();
    protected InputChecker inputChecker;


    private void fireLeaders(Map<Integer, Boolean> leadersAvailable) {
        for (GuiChangeListener listener : listeners) {
            listener.sendLeaderCards(leadersAvailable);
        }
    }

    private void firePray(String excommunicationUrl) {
        for (GuiChangeListener listener : listeners) {
            listener.pray(excommunicationUrl);
        }
    }


    protected void firePlayerState(PlayerState currentPlayerState) {

        for (GuiChangeListener listener : listeners) {
            listener.changeState(currentPlayerState);
        }

    }


    private void fireValidActions() {

        for (GuiChangeListener listener : listeners) {

            listener.validActions(inputChecker.getValidActionList());

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


    protected void validActionsGUI(Map<Integer, String> validActions) {

        inputChecker.setValidActionList(validActions);
        fireValidActions();


    }



    protected void getFamilyPawnsAvailabilityGUI(Map<FamilyPawn, Boolean> familyPawns) {

       Map<FamilyPawnType, Boolean> familyPawnsAvailability = new EnumMap<>(FamilyPawnType.class);

        for (Map.Entry<FamilyPawn, Boolean> entry : familyPawns.entrySet()) {

            familyPawnsAvailability.put(entry.getKey().getType(), entry.getValue());
        }

        inputChecker.setFamilyPawnAvailability(familyPawnsAvailability);

        fireFamilyPawns(familyPawns);

    }



    protected void getCardsForWorkersGUI(Map<Integer, ArrayList<String>> cardsForWorkers) {

        inputChecker.setPossibleCardsWorkActionMap(cardsForWorkers);

        fireCardsForWorkers(cardsForWorkers);

    }


    protected void getPayToObtainCardsGUI(Map<String, HashMap<Integer, String>> payToObtainCards) {

        inputChecker.setPayToObtainCardsMap(payToObtainCards);

        firePayToObtainCards(payToObtainCards);

    }



    protected void getPossibleCostsGUI(Map<Integer, String> possibleCosts) {

        inputChecker.setPossibleCosts(possibleCosts);

        firePossibleCosts(possibleCosts);

    }



    protected void getCouncilPrivilegesGUI(List<Integer> councilPrivileges) {

        inputChecker.setCouncilPrivilegeEffectList(councilPrivileges);

        fireCouncilPrivileges(councilPrivileges);

    }



    protected void getBonusTilesGUI(Map<Integer, String> bonusTiles) {

        inputChecker.setBonusTileMap(bonusTiles);

        fireBonusTiles(bonusTiles);

    }


    protected void getExcommunicationTileUrl(String excommunicationUrl) {

        firePray(excommunicationUrl);

    }

    protected void getAvailableLeaders(Map<Integer, Boolean> leadersAvailable) {
        fireLeaders(leadersAvailable);
    }

    public void setInputChecker(InputChecker inputChecker) {
        this.inputChecker = inputChecker;
    }


    public void endGame(String winner) {
        for (GuiChangeListener listener : listeners) {
            System.out.println("\n\nLANCIANDO END GAME\n\n");
            listener.endGame(winner);
        }
    }
}
