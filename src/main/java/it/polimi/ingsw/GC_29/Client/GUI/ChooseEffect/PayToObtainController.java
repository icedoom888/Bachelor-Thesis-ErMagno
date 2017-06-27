package it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

/**
 * Created by AlbertoPennino on 27/06/2017.
 */
public class PayToObtainController {

    private ChooseDistribution sender;

    @FXML
    private ImageView card;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    private void sendResponse(ActionEvent event){
        if (event.getSource()==yesButton){
            sender.sendInput("yes");
        }
        else if (event.getSource()==noButton){
            sender.sendInput("no");
        }
    }

    private void updateCard(Image newCard){
        card.setImage(newCard);
    }

    public void setSender(ChooseDistribution sender) {
        this.sender = sender;
    }
}
