package it.polimi.ingsw.GC_29.Client.GUI.ChooseWorkers;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by AlbertoPennino on 26/06/2017.
 */
public class WorkersController {

    private ChooseDistribution sender;

    @FXML
    private TextArea shownWorkers;

    @FXML
    private TextField chosenWorkers;

    @FXML
    private Button submit;

    @FXML
    private Text error;

    public void sendSubmit(ActionEvent event){
        if(!chosenWorkers.getText().isEmpty()){
            sender.sendInput("workers chosen " + chosenWorkers.getText());
        }
        else {
            error.setVisible(true);
        }
    }
    public void updateShownCosts(String newWorkers){
        shownWorkers.setText(newWorkers);
    }

    public void setSender(ChooseDistribution sender) {
        this.sender = sender;
    }
}
