package it.polimi.ingsw.GC_29.Client.GUI.Login;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by AlbertoPennino on 20/06/2017.
 */
public class LoginGUI extends Application {

    private LoginController loginController;
    static Stage stage = new Stage();

    public static void main(String[]args) {
        Application.launch(args);
    }




    @Override
    public void start(Stage stage) throws Exception {




        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        SplitPane root = loader.load();

        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.setHeight(400);
        stage.setWidth(600);
        stage.centerOnScreen();
        stage.show();
    }

    public LoginController getController() {

        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
