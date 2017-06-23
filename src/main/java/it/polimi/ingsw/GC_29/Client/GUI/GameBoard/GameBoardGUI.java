package it.polimi.ingsw.GC_29.Client.GUI.GameBoard;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */

import it.polimi.ingsw.GC_29.Client.GUI.ChangeViewGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameBoardGUI extends Application{
    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/polimi/ingsw/GC_29/GUI/GameBoard/GameBoard.fxml"));
        Parent root = loader.load();
        GameBoardController gameBoardController = loader.getController();
        ChangeViewGUI.setGameBoardController(gameBoardController);
        stage.setScene(new Scene(root));
        stage.setHeight(700);
        stage.setWidth(1100);
        stage.centerOnScreen();
        stage.setTitle("Lorenzo il Magnifico");
        stage.show();
    }

}
