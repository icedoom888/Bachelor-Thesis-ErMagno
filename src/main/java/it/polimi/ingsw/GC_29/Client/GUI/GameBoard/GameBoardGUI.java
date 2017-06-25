package it.polimi.ingsw.GC_29.Client.GUI.GameBoard;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameBoardGUI extends Application{
    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Gameboard Started");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));
        AnchorPane root = loader.load();
        stage.setScene(new Scene(root));
        stage.setHeight(700);
        stage.setWidth(1100);
        stage.centerOnScreen();
        stage.setTitle("Lorenzo il Magnifico");
        stage.show();
    }

}
