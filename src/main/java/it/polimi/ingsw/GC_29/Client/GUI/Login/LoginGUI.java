package it.polimi.ingsw.GC_29.Client.GUI.Login;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;

import javafx.stage.Stage;

/**
 * Created by AlbertoPennino on 20/06/2017.
 */
public class LoginGUI extends Application {

    public static void main(String[]args) {
        Application.launch(args);
    }

    /*public static void main(String[] args) {
        Application.launch(BonusTileMain.class);
    }*/

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        SplitPane root = loader.load();
        LoginController loginController = loader.getController();
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.setHeight(400);
        stage.setWidth(500);
        stage.centerOnScreen();
        stage.show();
    }
}
