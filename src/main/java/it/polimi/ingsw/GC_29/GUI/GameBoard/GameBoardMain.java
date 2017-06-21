package it.polimi.ingsw.GC_29.GUI.GameBoard;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameBoardMain extends Application{
    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GameBoard.fxml"));
        stage.setScene(new Scene(root));
        stage.setHeight(700);
        stage.setWidth(1100);
        stage.centerOnScreen();
        stage.setTitle("Lorenzo il Magnifico");
        stage.show();
    }

}
