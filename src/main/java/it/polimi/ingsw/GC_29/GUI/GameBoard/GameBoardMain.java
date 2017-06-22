package it.polimi.ingsw.GC_29.GUI.GameBoard;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameBoardMain extends Application{
    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));
        Parent root = loader.load();
        GameBoardController gameBoardController = loader.getController();
        stage.setScene(new Scene(root));
        stage.setHeight(700);
        stage.setWidth(1100);
        stage.centerOnScreen();
        stage.setTitle("Lorenzo il Magnifico");
        stage.show();
    }

}
