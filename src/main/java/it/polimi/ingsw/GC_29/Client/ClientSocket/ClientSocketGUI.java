package it.polimi.ingsw.GC_29.Client.ClientSocket;


import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.EnumInterface;
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
 * Created by Lorenzotara on 23/06/17.
 *
 * As ClientSocketCLI, ClientSocketGUI handles the login and the socket connection
 * with the server
 */
public class ClientSocketGUI {

    private final int PORT = 29999;
    private final String IP = "127.0.0.1";
    private final String NAME = "socket";
    private final String error = "Input not allowed for your current state";

    private PlayerColor playerColor;
    private EnumInterface enumInterface;

    private Socket socket;
    private ObjectInputStream socketIn;
    private ObjectOutputStream socketOut;


    private ClientInHandlerGUI clientInHandlerGUI;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());


    public void startClientGUI() {

        System.out.println("Client avviato GUI");
        try {

            connectGUI();

        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

    }

    /**
     * Using GUI, the client has to decide his username, password and kind of connection
     * in once. If the connection is socket, the connection is established and the server
     * checks if the data are correct. In a negative case, the server closes the socket after
     * having warned the client that the login was not successful and this method closes the socket too.
     * @param username
     * @param password
     * @return
     */
    public boolean loginGUI(String username, String password) {

        boolean logged = false;

        try {

            socketOut.writeObject("login");
            socketOut.flush();
            socketOut.writeObject("gui");
            socketOut.flush();
            socketOut.writeObject(username);
            socketOut.flush();
            socketOut.writeObject(password);
            socketOut.flush();

            logged = socketIn.readBoolean();

            if (logged) {

                PlayerColor playerColor = (PlayerColor)socketIn.readObject();

                this.playerColor = playerColor;

                // the input checker must be the same for in and out handler
                InputChecker inputChecker = new InputChecker();
                inputChecker.setPlayerColor(playerColor);
                clientInHandlerGUI.setInputChecker(inputChecker);
                clientInHandlerGUI.getCommonOutSocket().setInputChecker(inputChecker);

             }

            else socket.close();


        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);

            try {
                socket.close();
            } catch (IOException ex) {
                LOGGER.log(Level.INFO, e.getMessage(), e);
            }
        }

        return logged;

    }


    /**
     * connectGUI create the commonOutSocket and set the clientInHandlerGUI after having
     * chosen the right socket.
     */
    public void connectGUI() {

        try {

            System.out.println("Il client tenta di connettersi");

            socket = new Socket(IP, PORT);

            System.out.println("Connection created");

            //canali di comunicazione

            this.socketOut = new ObjectOutputStream((socket.getOutputStream()));
            this.socketIn = new ObjectInputStream(((socket.getInputStream())));


            //Creates one thread to receive messages from the server

            CommonOutSocket commonOutSocket = new CommonOutSocket(socketOut);
            this.clientInHandlerGUI = new ClientInHandlerGUI(socketIn, commonOutSocket);

            System.out.println("Client connesso");

        } catch (Exception e) {

            System.out.println("Exception: " + e);
            LOGGER.log(Level.INFO, e.getMessage(), e);

            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                LOGGER.log(Level.INFO, e.getMessage(), e);
            }
        }
    }


    /**
     * playNewGameGUI starts clientInHandlerGUI
     */
    public void playNewGameGUI() {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(clientInHandlerGUI);

    }

    public ClientInHandlerGUI getClientInHandlerGUI() {
        return clientInHandlerGUI;
    }


    public Socket getSocket() {
        return socket;
    }
}
