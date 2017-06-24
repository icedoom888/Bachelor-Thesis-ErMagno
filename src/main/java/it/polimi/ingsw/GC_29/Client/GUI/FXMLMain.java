package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.Client.ClientSocket.*;
import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginChange;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginController;
import it.polimi.ingsw.GC_29.Server.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class FXMLMain extends Application implements Observer<GUIChange> {

    Distribution connection;
    private String username;
    private String password;
    private boolean logged;
    private LoginController loginController;
    private ClientSocketGUI clientSocketGUI;
    private Stage stage;
    private Stage loginStage;
    private GameBoardController gameBoardController;

    @Override
    public void update(GUIChange o) throws Exception {

        if (o instanceof LoginChange) {
            Boolean connected = ((LoginChange) o).getConnected();
            if (connected) {
                signUp();
            }
        }

    }



    @Override
    public void update() {

    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

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


        System.out.println("Sono dentro a FXMLMain");

        loginStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        SplitPane root = loader.load();
        System.out.println("ROOT: " + root);

        /*
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.setHeight(400);
        stage.setWidth(500);
        stage.centerOnScreen();
        stage.show();
        */

        loginStage.setScene(new Scene(root));
        loginStage.setTitle("Login");
        loginStage.setHeight(400);
        loginStage.setWidth(500);
        loginStage.centerOnScreen();
        loginStage.show();


        loginController = loader.getController();


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

        loginController.registerObserver(this);









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

    public void signUp() {

        while (!logged) {

            /*while (!loginController.getConnected()){
                System.out.println("ciao");
            } // when the player submit
            */

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

        loginStage.close();

        ///////WAITING STAGE//////////
        ///////WAITING STAGE//////////




        switch (connection) {
            case SOCKET:
                clientSocketGUI.playNewGameGUI();
                clientSocketGUI.getClientInHandlerGUI().addListener(new GuiChangeListener() {
                    @Override
                    public void onReadingChange(GUIChange guiChange) {
                        if (guiChange instanceof StartGameChange) {
                            updateView();
                        }
                    }

                });
                break;

            case RMI:
                break;
        }
    }

    private void updateView() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Gameboard Started");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));
                AnchorPane root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //GameBoardController gameBoardController = loader.getController();
                //ChangeViewGUI.setGameBoardController(gameBoardController);
                stage.setScene(new Scene(root));
                stage.setHeight(700);
                stage.setWidth(1100);
                stage.centerOnScreen();
                stage.setTitle("Lorenzo il Magnifico");
                stage.show();
            }
        });
    }


    private void startGameboard() {

        /*Stage gameboardStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));

        AnchorPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Launching GameBoard: " + root);
        gameboardStage.setScene(new Scene(root));
        gameboardStage.setTitle("Login");
        gameboardStage.setHeight(400);
        gameboardStage.setWidth(500);
        gameboardStage.centerOnScreen();
        gameboardStage.show();

        gameBoardController = loader.getController();
        */

    }


}
