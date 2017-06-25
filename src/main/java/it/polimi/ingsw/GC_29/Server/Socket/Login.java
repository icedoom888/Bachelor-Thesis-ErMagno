package it.polimi.ingsw.GC_29.Server.Socket;

import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.EnumInterface;
import it.polimi.ingsw.GC_29.Server.GameMatchHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class Login {

    private final ObjectInputStream socketIn;
    private final ObjectOutputStream socketOut;
    private final Socket socket;
    private final GameMatchHandler gameMatchHandler;
    private String username;
    private boolean logged;
    private EnumInterface enumInterface;


    public Login(PlayerSocket playerSocket, GameMatchHandler gameMatchHandler) throws IOException {
        this.socketIn = playerSocket.getSocketIn();
        this.socketOut = playerSocket.getSocketOut();
        this.socket = playerSocket.socket;
        this.gameMatchHandler = gameMatchHandler;
        this.logged = false;
    }

    public String login() {


        while (!logged) {
            try {

                String login = (String)socketIn.readObject();

                if (login.contentEquals("login")) {

                    String distribution = (String)socketIn.readObject();

                    if (distribution.contentEquals("gui")) {
                        enumInterface = EnumInterface.GUI;
                    }

                    if (distribution.contentEquals("cli")) {
                        enumInterface = EnumInterface.CLI;
                    }

                    username = (String)socketIn.readObject();
                    String password = (String)socketIn.readObject();
                    String pw = gameMatchHandler.getUserPassword().get(username);

                    if (pw == null) {
                        gameMatchHandler.getUserPassword().put(username, password);
                        logged = true;
                    }

                    else {
                        logged = password.equals(pw);
                    }


                }

                else System.out.println("NON è LOGIN ma sei in LOGIN del Server");

                socketOut.writeBoolean(logged);
                socketOut.flush();

                if (enumInterface == EnumInterface.GUI) break;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return username;

    }

    public Socket getSocket() {
        return socket;
    }

    public EnumInterface getEnumInterface() {
        return enumInterface;
    }

    public boolean isLogged() {
        return logged;
    }
}
