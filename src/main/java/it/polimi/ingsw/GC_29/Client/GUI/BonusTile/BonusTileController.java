package it.polimi.ingsw.GC_29.Client.GUI.BonusTile;

import it.polimi.ingsw.GC_29.Client.ClientSocket.ClientOutHandlerGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */
public class BonusTileController {

    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private Button button4;
    @FXML private Button button5;


    public void executeButton1(ActionEvent event) {

        String string = executeButton();
        ClientOutHandlerGUI.sendInput(string+"0");
        endExecute(event);

    }

    public void executeButton2(ActionEvent event) {

        String string = executeButton();
        ClientOutHandlerGUI.sendInput(string+"1");
        endExecute(event);
    }

    public void executeButton3(ActionEvent event) {

        String string = executeButton();
        ClientOutHandlerGUI.sendInput(string+"2");
        endExecute(event);

    }

    public void executeButton4(ActionEvent event) {

        String string = executeButton();
        ClientOutHandlerGUI.sendInput(string+"3");
        endExecute(event);

    }
    public void executeButton5(ActionEvent event) {

        String string = executeButton();
        ClientOutHandlerGUI.sendInput(string+"4");
        endExecute(event);

    }

    private String executeButton() {
        return "bonus tile ";
    }

    private void endExecute(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
