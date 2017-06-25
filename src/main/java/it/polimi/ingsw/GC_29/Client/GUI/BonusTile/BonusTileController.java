package it.polimi.ingsw.GC_29.Client.GUI.BonusTile;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import it.polimi.ingsw.GC_29.Client.ClientSocket.ClientOutHandlerGUI;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Map;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */
public class BonusTileController {

    private ChooseDistribution sender;
    private GameBoardController gameBoardController;

    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private Button button4;
    @FXML private Button button5;


    public void executeButton1(ActionEvent event) {

        String index = "0";

        String string = executeButton();
        sender.sendInput(string + index);
        //TODO: aggiungi bonus tile a personal board
        gameBoardController.updateBonusTile(index);

        //endExecute(event);

    }

    public void executeButton2(ActionEvent event) {

        String string = executeButton();
        sender.sendInput(string+"1");
        //TODO: aggiungi bonus tile a personal board
        //endExecute(event);
    }

    public void executeButton3(ActionEvent event) {

        String string = executeButton();
        sender.sendInput(string+"2");
        //TODO: aggiungi bonus tile a personal board
        //endExecute(event);

    }

    public void executeButton4(ActionEvent event) {

        String string = executeButton();
        sender.sendInput(string+"3");
        //TODO: aggiungi bonus tile a personal board
        //endExecute(event);

    }
    public void executeButton5(ActionEvent event) {

        String string = executeButton();
        sender.sendInput(string+"4");
        //TODO: aggiungi bonus tile a personal board
        //endExecute(event);

    }

    private String executeButton() {
        return "bonus tile ";
    }

    /*
    private void endExecute(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    */

    public void setSender(ChooseDistribution sender) {
        this.sender = sender;
    }

    public void setBonusTiles(Map<Integer, String> bonusTiles) {

        //TODO: impl



    }

    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }
}
