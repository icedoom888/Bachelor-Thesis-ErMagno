package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Model.PlayerColor;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Lorenzotara on 14/06/17.
 *
 * ClientSocketCLI has the function to connect the client to the server,
 * handle the login and start the threads of communication.
 */
public class ClientSocketCLI {

    private final int PORT = 29999;
    private final String IP = "127.0.0.1";
    private final String NAME = "socket";
    private final String error = "Input not allowed for your current state";

    private PlayerColor playerColor;

    private Socket socket;
    private ObjectInputStream socketIn;
    private ObjectOutputStream socketOut;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;

    private ClientOutHandlerCLI clientOutHandlerCLI;
    private ClientInHandlerCLI clientInHandlerCLI;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());



    public ClientSocketCLI() throws IOException {
    }


    public void startClientCLI() throws IOException {
        System.out.println("Client avviato");

        try {
            connectCLI();

            loginCLI();

            playNewGameCLI();

        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

    }


    /**
     * Until the player has not given right username and passwords,
     * this method continue to ask him to insert username and password.
     * When the form is correctly filled, it receives the player color assigned
     * and saves it.
     */
    private void loginCLI() {

        try {
            boolean logged = false;

            while (!logged) {

                outVideo.println("Insert login:");
                String username = inKeyboard.readLine();

                outVideo.println("Inserire password:");
                String password = inKeyboard.readLine();

                socketOut.writeObject("login");
                socketOut.flush();
                socketOut.writeObject("cli");
                socketOut.flush();
                socketOut.writeObject(username);
                socketOut.flush();
                socketOut.writeObject(password);
                socketOut.flush();

                logged = socketIn.readBoolean();

                if (logged) {

                    outVideo.println("LoginSocket correctly done");

                    PlayerColor playerColor = (PlayerColor)socketIn.readObject();

                    setPlayerColor(playerColor);

                }

                else
                    outVideo.println("Nome utente in uso con altra password");


            }


        } catch (Exception e) {
            System.out.println("Exception: " + e);
            LOGGER.log(Level.INFO, e.getMessage(), e);

            try {
                socket.close();
            } catch (IOException ex) {
                LOGGER.log(Level.INFO, e.getMessage(), e);
            }
        }

    }

    /**
     * playNewGameCLI starts the clientInHandlerCLI and the
     * clientOutHandlerCLI. Therefore it sets the player color in the inputChecker
     * that will be an attribute of the previous classes.
     */
    private void playNewGameCLI() {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        clientInHandlerCLI.setClientOutHandlerCLI(clientOutHandlerCLI);

        InputChecker inputChecker = new InputChecker();
        inputChecker.setPlayerColor(playerColor);

        clientInHandlerCLI.setInputChecker(inputChecker);

        clientOutHandlerCLI.setInputChecker(inputChecker);


        executor.submit(clientInHandlerCLI);
        executor.submit(clientOutHandlerCLI);
    }


    /**
     * connectCLI connects the client to the server
     */
    private void connectCLI() {

        try {

            System.out.println("Il client tenta di connettersi");

            socket = new Socket(IP, PORT);

            System.out.println("Connection created");

            //canali di comunicazione


            inKeyboard = new BufferedReader(new InputStreamReader(System.in));
            outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);


            this.socketOut = new ObjectOutputStream(/*new BufferedOutputStream*/(socket.getOutputStream()));
            this.socketIn = new ObjectInputStream(/*new BufferedInputStream*/((socket.getInputStream())));


            //Creates one thread to send messages to the server
            //Creates one thread to receive messages from the server

            this.clientOutHandlerCLI = new ClientOutHandlerCLI(socketOut);
            this.clientInHandlerCLI = new ClientInHandlerCLI(socketIn);



            System.out.println("Client connesso");

        } catch (Exception e) {

            LOGGER.log(Level.INFO, e.getMessage(), e);

            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                LOGGER.log(Level.INFO, e.getMessage(), e);
            }
        }
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
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
