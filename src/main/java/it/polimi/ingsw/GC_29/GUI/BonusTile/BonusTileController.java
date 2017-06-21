package it.polimi.ingsw.GC_29.GUI.BonusTile;

import it.polimi.ingsw.GC_29.GUI.ClientSocketView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
        ClientSocketView.sendInput(string+"0");

    }

    public void executeButton2(ActionEvent event) {

        String string = executeButton();
        ClientSocketView.sendInput(string+"1");
    }

    public void executeButton3(ActionEvent event) {

        String string = executeButton();
        ClientSocketView.sendInput(string+"2");

    }

    public void executeButton4(ActionEvent event) {

        String string = executeButton();
        ClientSocketView.sendInput(string+"3");

    }
    public void executeButton5(ActionEvent event) {

        String string = executeButton();
        ClientSocketView.sendInput(string+"4");

    }

    private String executeButton() {
        return "bonus tile ";
    }

}
