package it.polimi.ingsw.GC_29.Client.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

/**
 * Created by AlbertoPennino on 05/07/2017.
 */
public class ReconnectedPlayersController {

    @FXML
    private TextArea reconnectedPlayersArea;

    /**
     * shows the list of players that reconnected
     * @param playerIds
     */
    public void updateReconnectedPlayers(List<String> playerIds){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");

        for (String id : playerIds){
            stringBuilder.append("\n");
            stringBuilder.append(id);

        }
        reconnectedPlayersArea.setText(stringBuilder.toString());
    }
}
