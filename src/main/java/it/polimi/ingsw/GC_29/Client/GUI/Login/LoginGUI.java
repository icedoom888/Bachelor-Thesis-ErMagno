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

    /*public static void main(String[] args) {
        Application.launch(BonusTileGUI.class);
    }*/




    @Override
    public void start(Stage stage) throws Exception {



        System.out.println("Sono dentro a login");

        //loginStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        SplitPane root = loader.load();
        System.out.println("ROOT: " + root);
        //loginController = loader.getController();
        stage.setScene(new Scene(root));
        Stage loginStage = new Stage();
        stage.setTitle("Login");
        stage.setHeight(400);
        stage.setWidth(600);
        stage.centerOnScreen();
        //stage.initModality(Modality.WINDOW_MODAL);
        System.out.println(stage.getModality());
        stage.show();
        System.out.println(loginController);
        System.out.println("arrivi qui?");
    }

    public LoginController getController() {

        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
