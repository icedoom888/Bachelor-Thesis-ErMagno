package it.polimi.ingsw.GC_29.Client.GUI;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * Created by AlbertoPennino on 05/07/2017.
 */
public class EndGameController {

    @FXML
    private Text winnerName;

    /**
     * Shows the winner's name
     * @param winner
     */
    public void updateWinner(String winner){
        winnerName.setText(winner);
    }
}
