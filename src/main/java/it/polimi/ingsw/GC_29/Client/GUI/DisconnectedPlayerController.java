package it.polimi.ingsw.GC_29.Client.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Created by AlbertoPennino on 05/07/2017.
 */
public class DisconnectedPlayerController {

    @FXML
    private TextArea disconnectedPlayer;

    /**
     * update the name of the disconnected player to show
     * @param playerId
     */
    public void updateDisconnectedPlayer(List<String> playerId){

        StringBuilder text = new StringBuilder("\n");

        for (String username : playerId) {

            text.append(username.toUpperCase()).append("\n");

        }

        System.out.println("SONO IN DISC CONTROLLER. STRINGA = " + text + " FINE");

        disconnectedPlayer.setText(text.toString());
    }
}
