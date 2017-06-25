package it.polimi.ingsw.GC_29.Client.GUI.ChooseCost;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by AlbertoPennino on 25/06/2017.
 */
public class ChooseCostController {

    private ChooseDistribution sender;

    @FXML
    private TextArea shownCosts;

    @FXML
    private TextField chosenCost;

    @FXML
    private Button submit;

    @FXML
    private Text error;

    public void sendSubmit(ActionEvent event){
        if(!chosenCost.getText().isEmpty()){
            sender.sendInput("cost chosen " + chosenCost.getText());
        }
        else {
            error.setVisible(true);
        }
    }

    public void updateShownCosts(String newCosts){
        shownCosts.setText(newCosts);
    }
}
