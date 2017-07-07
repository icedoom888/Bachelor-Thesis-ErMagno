package it.polimi.ingsw.GC_29.Server.Socket;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.EnumInterface;
import it.polimi.ingsw.GC_29.Server.GameMatchHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Supplier;
import java.util.logging.Logger;

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

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());



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

                    String gameInterface = (String)socketIn.readObject();

                    if (gameInterface.contentEquals("gui")) {
                        enumInterface = EnumInterface.GUI;
                    }

                    if (gameInterface.contentEquals("cli")) {
                        enumInterface = EnumInterface.CLI;
                    }

                    username = (String)socketIn.readObject();
                    String password = (String)socketIn.readObject();

                    if (!gameMatchHandler.verifyLoggedClient(username)) {

                        String pw = gameMatchHandler.getUserPassword().get(username);

                        if (pw == null) {
                            gameMatchHandler.getUserPassword().put(username, password);
                            logged = true;
                        }

                        else {
                            logged = password.equals(pw);
                        }
                    }

                    else logged = false;


                }

                else System.out.println("NON è LOGIN ma sei in LOGIN del Server");

                socketOut.writeBoolean(logged);
                socketOut.flush();

                if (enumInterface == EnumInterface.GUI) break;

            } catch (IOException e) {
                LOGGER.info((Supplier<String>) e);
            } catch (ClassNotFoundException e) {
                LOGGER.info((Supplier<String>) e);
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
