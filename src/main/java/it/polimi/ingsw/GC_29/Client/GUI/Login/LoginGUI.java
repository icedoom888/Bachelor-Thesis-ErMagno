package it.polimi.ingsw.GC_29.Client.GUI.Login;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;

import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

/**
 * Created by AlbertoPennino on 20/06/2017.
 */
public class LoginGUI extends Application {

    LoginController loginController;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        Scene root = new Scene(loader.load());

        //Get a controller instance
        loginController = loader.getController();

        stage.setScene(root);
        stage.setTitle("Login");
        stage.setHeight(400);
        stage.setWidth(500);
        stage.centerOnScreen();
        stage.show();
    }

    public LoginController getController() {

        return loginController;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
