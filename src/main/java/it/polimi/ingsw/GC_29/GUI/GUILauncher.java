package it.polimi.ingsw.GC_29.GUI;

import it.polimi.ingsw.GC_29.GUI.BonusTile.BonusTileController;
import it.polimi.ingsw.GC_29.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.GUI.Login.Login2Controller;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by AlbertoPennino on 22/06/2017.
 */

public class GUILauncher {
    GameBoardController gameBoardController;
    Login2Controller login2Controller;
    BonusTileController bonusTileController;

    public GUILauncher(){
        Login2Controller login2Controller = new Login2Controller();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login2.fxml"));
        loader.setController(login2Controller);
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        SplitPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogStage.setScene(new Scene(root));
        dialogStage.setTitle("Login");
        dialogStage.setHeight(400);
        dialogStage.setWidth(500);
        dialogStage.centerOnScreen();
        dialogStage.show();
    }

    public void launchLogin(){

    }
    public void launchWait(){
    }
}

