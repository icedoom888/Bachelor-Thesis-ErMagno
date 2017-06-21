package it.polimi.ingsw.GC_29.GUI.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        Parent root = FXMLLoader.load(getClass().getResource("/Login2.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.setMinHeight(310);
        stage.setMinWidth(515);
        stage.setHeight(310);
        stage.setWidth(515);
        stage.centerOnScreen();
        stage.show();
    }
}
