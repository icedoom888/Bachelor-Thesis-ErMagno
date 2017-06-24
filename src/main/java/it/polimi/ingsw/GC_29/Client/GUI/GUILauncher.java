package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.App;
import it.polimi.ingsw.GC_29.Client.GUI.BonusTile.BonusTileController;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardGUI;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginController;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginGUI;
import it.polimi.ingsw.GC_29.Client.GUI.Login.WaitingNewGame;
import it.polimi.ingsw.GC_29.Server.Socket.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by AlbertoPennino on 22/06/2017.
 */

public class GUILauncher extends Application implements Runnable {
    GameBoardController gameBoardController;
    LoginController loginController;
    BonusTileController bonusTileController;
    Stage stage;

    public GUILauncher(Stage stage){
       GameBoardController gameBoardController = new GameBoardController();
       LoginController loginController = new LoginController();
       BonusTileController bonusTileController = new BonusTileController();
       this.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
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

    public void launchLogin(){
        Application.launch(LoginGUI.class);

    }

    public void launchWait() {
        Application.launch(WaitingNewGame.class);

    }

    @Override
    public void run() {
        //Application.launch(GameBoardGUI.class);

        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
