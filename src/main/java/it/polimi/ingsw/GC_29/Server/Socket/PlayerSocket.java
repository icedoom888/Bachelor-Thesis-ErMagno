package it.polimi.ingsw.GC_29.Server.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Lorenzotara on 15/06/17.
 *
 * This class is a container of all information about the socket of the player
 */
public class PlayerSocket {

    Socket socket;
    ObjectInputStream socketIn;
    ObjectOutputStream socketOut;

    public PlayerSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.socketIn = new ObjectInputStream(socket.getInputStream());
        this.socketOut= new ObjectOutputStream(socket.getOutputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getSocketIn() {
        return socketIn;
    }

    public ObjectOutputStream getSocketOut() {
        return socketOut;
    }
}
