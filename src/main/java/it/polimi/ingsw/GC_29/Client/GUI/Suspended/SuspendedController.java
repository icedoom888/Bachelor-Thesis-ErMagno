package it.polimi.ingsw.GC_29.Client.GUI.Suspended;


import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

/**
 * Created by Lorenzotara on 01/07/17.
 */
public class SuspendedController {

    private InputInterfaceGUI sender;

    @FXML
    private Button joinGame;

    public void joinGame(ActionEvent event) {

        sender.sendInput("join game");

    }


    public void setSender(InputInterfaceGUI sender) {
        this.sender = sender;
    }
}
