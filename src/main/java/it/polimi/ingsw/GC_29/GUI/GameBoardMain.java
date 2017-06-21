package it.polimi.ingsw.GC_29.GUI;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameBoardMain extends Application{
    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
            URL location = getClass().getResource("GameBoard.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(location);

            Parent page = fxmlLoader.<Parent>load();
            Scene scene = new Scene(page,1100,800);
            stage.setScene(scene);
            stage.setTitle("Lorenzo il Magnifico");
            stage.show();
    }

}
