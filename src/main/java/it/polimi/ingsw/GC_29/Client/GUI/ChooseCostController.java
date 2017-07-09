package it.polimi.ingsw.GC_29.Client.GUI;


import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
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

    private InputInterfaceGUI sender;

    @FXML
    private TextArea shownCosts;

    @FXML
    private TextField chosenCost;

    @FXML
    private Button submit;

    @FXML
    private Text error;

    /**
     * called when the player clicks on the submit button,
     * controls if the text field is empty: if it is shows an error message,
     * if it isn't sends the player choice via InputInterfaceGUI
     * @param event
     */
    public void sendSubmit(ActionEvent event){
        if(!chosenCost.getText().isEmpty()){
            sender.sendInput("cost " + chosenCost.getText());
        }
        else {
            error.setVisible(true);
        }
    }

    /**
     * when called updates the text in the main text area in order to show the new costs the player has to choose from
     * @param newCosts
     */
    public void updateShownCosts(String newCosts){
        shownCosts.setText(newCosts);
    }

    public void setSender(InputInterfaceGUI sender) {
        this.sender = sender;
    }
}
