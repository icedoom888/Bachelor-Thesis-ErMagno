package it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.InputChecker;
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

    private ChooseDistribution sender;
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



    public void sendSubmit(ActionEvent event){

        Integer choiceMade = Integer.valueOf(effectChoosen.getText());

        if(!choices.isEmpty() && choices.contains(choiceMade)) {

            payToObtainController.setCurrentPayToObtainEffectIndex(choiceMade);
            payToObtainController.seeNextCard();

        }
        else {
            error.setVisible(true);
        }
    }

    public void updateShownEffects(String newEffects){
        effects.setText(newEffects);
        error.setVisible(false);
        effectChoosen.setText("");
    }

    public void setSender(ChooseDistribution sender) {
        this.sender = sender;
    }

    public void setChoices(Set<Integer> choices) {
        this.choices = choices;
    }

    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    public void askWhichEffect(Map<Integer, String> currentPayToObtainEffectsMap) {

        this.choices = currentPayToObtainEffectsMap.keySet();

        //TODO: impl

        Set<Integer> keys = currentPayToObtainEffectsMap.keySet();



        /*
        System.out.println("which Pay To Obtain do you want to activate?");

        for (Integer key : keys) {

            System.out.println("effect index: " + key + ") " + currentPayToObtainEffectsMap.get(key));

        }

        System.out.println("insert the effect index:");
        */

    }

    public void setPayToObtainController(PayToObtainController payToObtainController) {
        this.payToObtainController = payToObtainController;
    }
}
