package it.polimi.ingsw.GC_29.Client.GUI.BonusTile;


import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Map;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */
public class BonusTileController {

    private InputInterfaceGUI sender;
    private GameBoardController gameBoardController;

    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private Button button4;
    @FXML private Button button5;


    /**
     * called when the button1 is clicked,
     * sends the choice made via InputInterfaceGUI
     * @param event
     */
    public void executeButton1(ActionEvent event) {

        String index = "0";

        System.out.println("HO SCELTO TILE");

        String string = executeButton();
        sender.sendInput(string + index);
        gameBoardController.updateBonusTile(index);

        //endExecute(event);

    }

    /**
     * called when the button2 is clicked,
     * sends the choice made via InputInterfaceGUI
     * @param event
     */
    public void executeButton2(ActionEvent event) {

        String index = "1";

        String string = executeButton();
        sender.sendInput(string + index);
        gameBoardController.updateBonusTile(index);
        //endExecute(event);
    }

    /**
     * called when the button3 is clicked,
     * sends the choice made via InputInterfaceGUI
     * @param event
     */
    public void executeButton3(ActionEvent event) {

        String index = "2";

        String string = executeButton();
        sender.sendInput(string + index);
        gameBoardController.updateBonusTile(index);
        //endExecute(event);

    }

    /**
     * called when the button4 is clicked,
     * sends the choice made via InputInterfaceGUI
     * @param event
     */
    public void executeButton4(ActionEvent event) {

        String index = "3";

        String string = executeButton();
        sender.sendInput(string + index);
        gameBoardController.updateBonusTile(index);
        //endExecute(event);

    }

    /**
     * called when the button5 is clicked,
     * sends the choice made via InputInterfaceGUI
     * @param event
     */
    public void executeButton5(ActionEvent event) {

        String index = "4";

        String string = executeButton();
        sender.sendInput(string + index);
        gameBoardController.updateBonusTile(index);
        //endExecute(event);

    }

    /**
     * used to build the answer string
     * @return
     */
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

    /**
     * sets the InputInterfaceGUI
     * @param sender
     */
    public void setSender(InputInterfaceGUI sender) {
        this.sender = sender;
    }

    /**
     * This function shows to the player the bonus tiles he can select,
     * those not available are disabled
     * @param bonusTiles
     */
    public void setBonusTiles(Map<Integer, String> bonusTiles) {
        for (int i=0;i<5;i++){
            if (!bonusTiles.containsKey(i)){
                switch (i){
                    case (0):
                        button1.setDisable(true);
                        break;
                    case (1):
                        button2.setDisable(true);
                        break;
                    case (2):
                        button3.setDisable(true);
                        break;
                    case (3):
                        button4.setDisable(true);
                        break;
                    case (4):
                        button5.setDisable(true);
                        break;
                    default:
                        break;

                }
            }

        }
    }

    /**
     * set the gameBoardController
     * @param gameBoardController
     */
    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

}
