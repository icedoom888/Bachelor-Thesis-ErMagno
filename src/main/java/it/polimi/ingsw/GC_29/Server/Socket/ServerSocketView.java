package it.polimi.ingsw.GC_29.Server.Socket;

import it.polimi.ingsw.GC_29.Controllers.Change;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Server.GameMatchHandler;
import it.polimi.ingsw.GC_29.Server.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class ServerSocketView extends View implements Runnable {

    private Socket socket;

    private GameStatus gameStatus;


    public ServerSocketView(Socket socket, GameStatus gameStatus) throws IOException {
        this.socket = socket;
        this.gameStatus = gameStatus;
    }

    @Override
    public void update(Change o) throws Exception {

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public void run() {



    }


}
