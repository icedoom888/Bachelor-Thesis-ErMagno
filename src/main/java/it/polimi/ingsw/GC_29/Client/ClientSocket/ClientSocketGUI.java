package it.polimi.ingsw.GC_29.Client.ClientSocket;


import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.EnumInterface;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    private ClientOutHandlerGUI clientOutHandlerGUI;
    private ClientInHandlerGUI clientInHandlerGUI;



    public void startClientGUI() {

        System.out.println("Client avviato GUI");
        try {

            connectGUI();

            //loginGUI(username, password);

            //playNewGameCLI();

            //chiudi();
            //TODO: gestisci fine partita
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } /*finally {
            // Always close it:
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }*/

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

                //TODO schermata di login successful

                //outVideo.println("Login correctly done");

                PlayerColor playerColor = (PlayerColor)socketIn.readObject();

                this.playerColor = playerColor;

                clientInHandlerGUI.setCommonOutSocket(clientOutHandlerGUI.getCommonOutSocket());
                //clientOutHandlerGUI.setClientInHandlerGUI(clientInHandlerGUI);

                //CommonView commonView = new CommonView();
                //commonView.setInputChecker(new InputChecker());
                //commonView.setPlayerColor(playerColor);

                // the input checker must be the same for in and out handler
                InputChecker inputChecker = new InputChecker();
                clientOutHandlerGUI.setInputChecker(inputChecker);
                clientOutHandlerGUI.setPlayerColor(playerColor);
                clientInHandlerGUI.setInputChecker(inputChecker);

             }

            else socket.close();


        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
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

            this.socketOut = new ObjectOutputStream(/*new BufferedOutputStream*/(socket.getOutputStream()));
            this.socketIn = new ObjectInputStream(/*new BufferedInputStream*/((socket.getInputStream())));


            //Creates one thread to send messages to the server
            //Creates one thread to receive messages from the server


            this.clientOutHandlerGUI = new ClientOutHandlerGUI(socketOut);
            this.clientInHandlerGUI = new ClientInHandlerGUI(socketIn);




            System.out.println("Client connesso");

        } catch (Exception e) {

            System.out.println("Exception: " + e);
            e.printStackTrace();

            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }


    public void playNewGameGUI() {

        //clientInHandlerGUI.start();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(clientInHandlerGUI);
        executor.submit(clientOutHandlerGUI);

    }

    public ClientInHandlerGUI getClientInHandlerGUI() {
        return clientInHandlerGUI;
    }


    public ClientOutHandlerGUI getClientOutHandlerGUI() {
        return clientOutHandlerGUI;
    }

    public Socket getSocket() {
        return socket;
    }
}
