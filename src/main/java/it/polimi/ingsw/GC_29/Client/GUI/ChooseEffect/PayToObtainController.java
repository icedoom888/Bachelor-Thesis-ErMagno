package it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

import java.util.*;

/**
 * Created by AlbertoPennino on 27/06/2017.
 */
public class PayToObtainController {

    private ChooseDistribution sender;
    private GameBoardController gameBoardController;
    private ChooseEffectController chooseEffectController;

    private Map<String, HashMap<Integer, String>> payToObtainCards = new HashMap<>();
    private ArrayList<String> payToObtainCardKeys = new ArrayList<>();
    private Map<Integer, String> currentPayToObtainEffectsMap = new HashMap<>();
    private int currentPayToObtainEffectIndex;
    private String currentPayToObtainCard;
    private Boolean cardIsToActivate;
    private Map<String, Integer> activatedCardMap = new HashMap<>();

    @FXML
    private ImageView card;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;


    @FXML
    private void sendResponse(ActionEvent event) {

        //chiudi la schermata
        gameBoardController.getPayToObtainPane().setVisible(false);

        if (event.getSource() == yesButton) {

            cardIsToActivate = true;

        }

        else if (event.getSource()==noButton){

            cardIsToActivate = false;

        }

        if (handleCardDecision()) {

            seeNextCard();
        }
    }

    public void seeNextCard() {

        if (nextCard()) {

            askActivateCard();

        }

        else {

            sender.sendInput(activatedCardMap);

        }
    }

    private boolean nextCard() {

        if(!payToObtainCardKeys.isEmpty()) {

            currentPayToObtainCard = payToObtainCardKeys.remove(0);

            return true;
        }

        else {

            return false;
        }

    }


    public void chooseCards() {

        setPayToObtainCards(payToObtainCards);

        askActivateCard();

    }


    private void setPayToObtainCards(Map<String, HashMap<Integer, String>> payToObtainCardsMap) {

        this.payToObtainCards = payToObtainCardsMap;

        Set<String> keySet = payToObtainCards.keySet();

        String keyArray[] = keySet.toArray(new String [keySet.size()]);

        payToObtainCardKeys = new ArrayList<>(Arrays.asList(keyArray));

        currentPayToObtainCard = payToObtainCardKeys.remove(0);

    }

    private void askActivateCard() {

        //TODO: impl gui

        //TODO: apri la schermata

        gameBoardController.getPayToObtainPane().setVisible(true);

    }

    private void updateCard(Image newCard){
        card.setImage(newCard);
    }


    private boolean handleCardDecision() {

        if(cardIsToActivate) {

            currentPayToObtainEffectsMap = payToObtainCards.get(currentPayToObtainCard);

            if(currentPayToObtainEffectsMap.size() > 1){

                askWhichEffect();

                return false;
            }

            else {

                Set<Integer> keySet = payToObtainCards.get(currentPayToObtainCard).keySet();
                Integer keyArray[] = keySet.toArray(new Integer[keySet.size()]);
                currentPayToObtainEffectIndex = keyArray[0];

                addCard();

                return true;
            }

        }

        return true;
    }



    private void askWhichEffect() {

        if (!currentPayToObtainEffectsMap.isEmpty()) {
            chooseEffectController.askWhichEffect(currentPayToObtainEffectsMap);
        }

    }

    private void addCard() {

        activatedCardMap.put(currentPayToObtainCard, currentPayToObtainEffectIndex);


    }


    public void setSender(ChooseDistribution sender) {
        this.sender = sender;
    }

    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    public void setChooseEffectController(ChooseEffectController chooseEffectController) {
        this.chooseEffectController = chooseEffectController;
    }

    public void setCurrentPayToObtainEffectIndex(Integer currentPayToObtainEffectIndex) {
        this.currentPayToObtainEffectIndex = currentPayToObtainEffectIndex;
    }
}
