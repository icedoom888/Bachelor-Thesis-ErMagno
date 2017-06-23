package it.polimi.ingsw.GC_29.Client.GUI.BonusTile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */
public class BonusTileMain extends Application {

    public static void main(String[] args) {
       Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/BonusTile.fxml"));
        HBox root = loader.load();
        BonusTileController bonusTileController = loader.getController();
        stage.setScene(new Scene(root));
        stage.setTitle("Choose BonusTile");
        stage.setHeight(450);
        stage.setWidth(500);
        stage.centerOnScreen();
        stage.show();
    }
}
