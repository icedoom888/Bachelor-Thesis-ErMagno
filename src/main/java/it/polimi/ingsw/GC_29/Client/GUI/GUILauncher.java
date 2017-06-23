package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.Client.GUI.BonusTile.BonusTileController;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginController;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginGUI;
import it.polimi.ingsw.GC_29.Client.GUI.Login.WaitingNewGame;
import it.polimi.ingsw.GC_29.Server.Socket.Login;
import javafx.application.Application;

/**
 * Created by AlbertoPennino on 22/06/2017.
 */

public class GUILauncher {
    GameBoardController gameBoardController;
    LoginController loginController;
    BonusTileController bonusTileController;

    public GUILauncher(){
       GameBoardController gameBoardController = new GameBoardController();
       LoginController loginController = new LoginController();
       BonusTileController bonusTileController = new BonusTileController();
    }

    public void launchLogin(){
        Application.launch(LoginGUI.class);

    }

    public void launchWait() {
        Application.launch(WaitingNewGame.class);

    }
}
