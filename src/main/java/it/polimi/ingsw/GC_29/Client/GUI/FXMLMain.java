package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.Client.ClientSocket.*;
import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.EnumInterface;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginController;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class FXMLMain extends Application {

    Distribution connection;
    private String username;
    private String password;
    private boolean logged;
    private LoginController loginController;
    private ClientSocketGUI clientSocketGUI;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ///////LOGIN///////

        //FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("FXML/Login.fxml"));

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));


        /*loginController = loginLoader.getController();
        System.out.println(loginController);
        loginController.setConnected(false);*/

        SplitPane root = loginLoader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.setHeight(400);
        stage.setWidth(500);
        stage.centerOnScreen();
        stage.show();


        LoginGUI loginGUI = new LoginGUI();
        loginGUI.start(stage);
        loginController = loginGUI.getController();
        System.out.println(loginController);


        logged = false;

        while (!logged) {

            while (!loginController.getConnected()){} // when the player submit

            this.connection = loginController.getConnection();


            this.username = loginController.getUsername();
            this.password = loginController.getPassword();

            switch (connection) {

                case SOCKET:

                    connectSocket();

                    break;

                case RMI:
                    break;
            }
        }

        // Login Successful

        stage.close();

        switch (connection) {
            case SOCKET:
                clientSocketGUI.playNewGameGUI();
                break;

            case RMI:
                break;
        }


        ///////WAITING STAGE//////////
        ///////WAITING STAGE//////////




        ////////////////////////////////////////////////////////


    }

    private void connectSocket() {

        try {

            clientSocketGUI = new ClientSocketGUI();
            clientSocketGUI.startClientGUI();
            logged = clientSocketGUI.loginGUI(username, password);

            if (!logged) {
                loginController.setConnected(false);
            }



        } catch (Exception e) {

            System.out.println("Exception: " + e);
            e.printStackTrace();

        }

    }



    /*
    FXMLLoader controlsLoader = new FXMLLoader(getClass().getResource("/djview-controls.fxml"));
    FXMLLoader viewLoader = new FXMLLoader(getClass().getResource("/djview-view.fxml"));
    Scene controlsScene = new Scene(controlsLoader.load());
    Scene viewScene = new Scene(viewLoader.load());

    // Create a controller instance
    FXMLController controller = controlsLoader.getController();
    FXMLViewController viewController = viewLoader.getController();

    BeatModelInterface model = new BeatModel();
        model.init();
                controller.setModel(model);
                viewController.setModel(model);
                primaryStage.setScene(controlsScene);
                primaryStage.show();

                Stage viewStage = new Stage();
                viewStage.setScene(viewScene);
                viewStage.show();

     */

}
