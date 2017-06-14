package it.polimi.ingsw.GC_29.Server.Socket;

import it.polimi.ingsw.GC_29.Server.GameMatchHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class Login {

    private final Socket socket;
    private final ObjectInputStream socketIn;
    private final ObjectOutputStream socketOut;
    private final GameMatchHandler gameMatchHandler;
    private String username;


    public Login(Socket socket, GameMatchHandler gameMatchHandler) throws IOException {
        this.socket = socket;
        this.socketIn = new ObjectInputStream(socket.getInputStream());
        this.socketOut = new ObjectOutputStream(socket.getOutputStream());
        this.gameMatchHandler = gameMatchHandler;
        login();
    }

    private void login() {

        Boolean logged = false;

        while (!logged) {
            try {

                String login = (String)socketIn.readObject();

                if (login.contentEquals("login")) {

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

                if (logged) {
                    gameMatchHandler.addClient(username, socket);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
