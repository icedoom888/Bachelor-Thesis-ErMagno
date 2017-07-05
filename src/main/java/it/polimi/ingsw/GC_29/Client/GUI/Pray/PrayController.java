package it.polimi.ingsw.GC_29.Client.GUI.Pray;


import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Created by AlbertoPennino on 25/06/2017.
 */
public class PrayController {

    private InputInterfaceGUI sender;

    @FXML
    private ImageView excommunicationDetails;

    @FXML
    private RadioButton pray;

    @FXML
    private RadioButton getExcommunicated;

    @FXML
    private Text error;

    @FXML
    private Button submit;


    /**
     * called when the player clicks on the submit button,
     * if a choice has been made then it is sent via InputInterfaceGUI
     * else it shows an error message
     * @param event
     */
    public void sendSubmit(ActionEvent event){
        if(pray.isSelected()){
            sender.sendInput("pray");
        }
        else if (getExcommunicated.isSelected()){
            sender.sendInput("do not pray");
        }
        else {
            error.setVisible(true);
        }
    }

    /**
     * updates the image of the excommunication tile on the pane
     * @param newExcommunication
     */
    public void updatePray(String newExcommunication){
        excommunicationDetails.setImage(new Image(newExcommunication));
    }


    /**
     * doesn't allow to both Pray and Don't pray buttons to be selected simultaneously
     * @param event
     */
    public void switchButtons(ActionEvent event){
        if (event.getSource()==pray){
            getExcommunicated.setSelected(false);
        }
        else if (event.getSource()==getExcommunicated){
            pray.setSelected(false);
        }
    }

    public void setSender(InputInterfaceGUI sender) {
        this.sender = sender;
    }
}
