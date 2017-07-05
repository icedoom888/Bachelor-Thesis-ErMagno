package it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect;


import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by AlbertoPennino on 27/06/2017.
 */
public class ChooseEffectController {

    private InputInterfaceGUI sender;
    private Set<Integer> choices;
    private GameBoardController gameBoardController;

    private PayToObtainController payToObtainController;
    //private Map<Integer, String> currentPayToObtainEffectsMap;

   @FXML
    private TextArea effects;

   @FXML
    private TextField effectChoosen;

   @FXML
    private Text error;

   @FXML
    private Button submit;


    /**
     * called when the player clicks on the submit button,
     * controls if the text field is empty: if it is shows an error message,
     * if it isn't checks if the choice made is allowed and then sends the player choice via InputInterfaceGUI
     * @param event
     */
    public void sendSubmit(ActionEvent event){


        Integer choiceMade = Integer.valueOf(effectChoosen.getText());

        if(!choices.isEmpty() && choices.contains(choiceMade)) {

            gameBoardController.getChooseEffectPane().setVisible(false);

            payToObtainController.setCurrentPayToObtainEffectIndex(choiceMade);
            payToObtainController.seeNextCard();

        }
        else {
            error.setVisible(true);
        }
    }

    /**
     * called to show in the main text area the options the player can choose from
     * @param newEffects
     */
    public void updateShownEffects(String newEffects){
        effects.setText(newEffects);
        error.setVisible(false);
        effectChoosen.setText("");
        gameBoardController.getChooseEffectPane().setVisible(true);
    }

    public void setSender(InputInterfaceGUI sender) {
        this.sender = sender;
    }

    public void setChoices(Set<Integer> choices) {
        this.choices = choices;
    }

    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    /**
     * called to set a local variable with the possible choices,
     * and created the string containing the choices to be set into the main text  Area
     * @param currentPayToObtainEffectsMap
     */
    public void askWhichEffect(Map<Integer, String> currentPayToObtainEffectsMap) {

        this.choices = currentPayToObtainEffectsMap.keySet();

        Set<Integer> keys = currentPayToObtainEffectsMap.keySet();

        StringBuilder stringBuilder = new StringBuilder();

        for (Integer key : keys) {
            stringBuilder.append(key)
                    .append(") ")
                    .append(currentPayToObtainEffectsMap.get(key))
                    .append("\n");
        }

        updateShownEffects(stringBuilder.toString());


    }

    public void setPayToObtainController(PayToObtainController payToObtainController) {
        this.payToObtainController = payToObtainController;
    }
}
