package it.polimi.ingsw.GC_29.GUI.BonusTile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */
public class BonusTileMain extends Application {

    public static void main(String[] args) {
       Application.launch(BonusTileMain.class);
    }

    @Override
    public void start(Stage stage) throws Exception {

        SplitPane root = FXMLLoader.load(getClass().getResource("/Login2.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.setHeight(400);
        stage.setWidth(500);
        stage.centerOnScreen();
        stage.show();
    }
}
