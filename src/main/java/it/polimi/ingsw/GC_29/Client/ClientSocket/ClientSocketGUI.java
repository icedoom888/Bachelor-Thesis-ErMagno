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
import java.util.logging.Logger;

/**
 * Created by Lorenzotara on 23/06/17.
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
            LOGGER.info((Supplier<String>) e);
        }

    }

    public boolean loginGUI(String username, String password) {

        boolean logged = false;

        try {

            //ObjectOutputStream outSocket = clientOutHandlerGUI.getCommonOutSocket().getSocketOut();
            //ObjectInputStream inSocket = clientInHandlerGUI.getSocketIn();

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

                //outVideo.println("Login correctly done");

                PlayerColor playerColor = (PlayerColor)socketIn.readObject();

                this.playerColor = playerColor;

                //clientOutHandlerGUI.setClientInHandlerGUI(clientInHandlerGUI);

                //CommonView commonView = new CommonView();
                //commonView.setInputChecker(new InputChecker());
                //commonView.setPlayerColor(playerColor);

                // the input checker must be the same for in and out handler
                InputChecker inputChecker = new InputChecker();
                inputChecker.setPlayerColor(playerColor);
                //clientOutHandlerGUI.setPlayerColor(playerColor);
                clientInHandlerGUI.setInputChecker(inputChecker);
                clientInHandlerGUI.getCommonOutSocket().setInputChecker(inputChecker);

             }

            else socket.close();


        } catch (Exception e) {
            LOGGER.info((Supplier<String>) e);

            try {
                socket.close();
            } catch (IOException ex) {
                LOGGER.info((Supplier<String>) ex);
            }
        }

        return logged;

    }


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
            LOGGER.info((Supplier<String>) e);

            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                LOGGER.info((Supplier<String>) ex);
            }
        }
    }


    public void playNewGameGUI() {

        //clientInHandlerGUI.start();

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
