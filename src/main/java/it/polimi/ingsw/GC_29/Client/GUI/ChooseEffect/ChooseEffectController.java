package it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by AlbertoPennino on 27/06/2017.
 */
public class ChooseEffectController {

    private ChooseDistribution sender;
    private ArrayList<Integer> choices;

   @FXML
    private TextArea effects;

   @FXML
    private TextField effectChoosen;

   @FXML
    private Text error;

   @FXML
    private Button submit;

    public void sendSubmit(ActionEvent event){
        String choiceMade = effectChoosen.getText();
        if(!choiceMade.isEmpty()){
            for (Integer choicePossible : choices){
                if (choicePossible.toString().equals(choiceMade)){
                    sender.sendInput("use effect " + choiceMade);
                    System.out.println("use effect " + choiceMade);
                }
                else {
                    error.setVisible(true);
                }
            }

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

    public void setChoices(ArrayList<Integer> choices) {
        this.choices = choices;
    }
}
