package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.Client.ClientSocket.*;
import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginController;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Modality;
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

    @Override
    public void start(Stage stage) throws Exception {

        ///////LOGIN///////

        //FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("FXML/Login.fxml"));
        /*
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));


        loginController = loginLoader.getController();
        System.out.println(loginController);
        loginController.setConnected(false);

        SplitPane root = loginLoader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.setHeight(400);
        stage.setWidth(500);
        stage.centerOnScreen();
        stage.show();
        */


        System.out.println("Sono dentro a login");

        //loginStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        SplitPane root = loader.load();
        System.out.println("ROOT: " + root);
        loginController = loader.getController();
        stage.setScene(new Scene(root));
        //Stage loginStage = new Stage();
        stage.setTitle("Login");
        stage.setHeight(400);
        stage.setWidth(500);
        stage.centerOnScreen();
        //stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
        System.out.println(loginController);
        System.out.println("arrivi qui?");

        /*Stage loginStage = new Stage();
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        SplitPane root = loginLoader.load();
        loginController = loginLoader.getController();

        loginStage.setScene(new Scene(root));
        loginStage.setTitle("Login");
        loginStage.setHeight(400);
        loginStage.setWidth(500);
        loginStage.centerOnScreen();
        //loginStage.initModality(Modality.WINDOW_MODAL);
        System.out.println(loginStage.getModality());
        loginStage.showAndWait();
        System.out.println(loginStage.isShowing());
        Thread.sleep(1000000000);
        System.out.println("arrivi qui?");
*/

        /*
        LoginGUI loginGUI = new LoginGUI();
        //Stage newStage = new Stage();
        loginController = new LoginController();

        System.out.println(loginController);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        SplitPane root = loader.load();
        stage.setScene(new Scene(root));

        loginGUI.setLoginController(loginController);
        loginGUI.start(stage);
        loginController = loginGUI.getController();
        System.out.println(loginController);

*/

        logged = false;

        loginController.setConnected(false);

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


}
