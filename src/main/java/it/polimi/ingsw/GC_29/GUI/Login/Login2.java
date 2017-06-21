package it.polimi.ingsw.GC_29.GUI.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by AlbertoPennino on 20/06/2017.
 */
public class Login2 extends Application {

    public static void main(String[]args) {
        Application.launch(args);
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
