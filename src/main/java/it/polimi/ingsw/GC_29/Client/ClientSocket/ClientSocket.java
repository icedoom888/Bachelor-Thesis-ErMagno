package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.EnumInterface;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class ClientSocket {

    private final int PORT = 29999;
    private final String IP = "127.0.0.1";
    private final String NAME = "socket";
    private final String error = "Input not allowed for your current state";

    private PlayerColor playerColor;
    private EnumInterface enumInterface;

    private Socket socket;
    private ObjectInputStream socketIn;
    private ObjectOutputStream socketOut;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;

    private ClientOutHandlerCLI clientOutHandlerCLI;
    private ClientInHandlerCLI clientInHandlerCLI;

    private ClientOutHandlerGUI clientOutHandlerGUI;
    private ClientInHandlerGUI clientInHandlerGUI;

    public ClientSocket(EnumInterface enumInterface) throws IOException {
        this.enumInterface = enumInterface;
    }


    public void startClientCLI() throws UnknownHostException, IOException {
        System.out.println("Client avviato");

        try {
            connect();

            loginCLI();

            playNewGameCLI();

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

    public void startClientGUI() {

        System.out.println("Client avviato GUI");
        try {

            connect();

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

            //ObjectOutputStream outSocket = clientOutHandlerGUI.getCommonOut().getSocketOut();
            //ObjectInputStream inSocket = clientInHandlerGUI.getSocketIn();

            socketOut.writeObject("login");
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

                setPlayerColor(playerColor);

                clientInHandlerGUI.getCommonView().setPlayerColor(playerColor);
            }


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

    private void loginCLI() {

        try {
            boolean logged = false;

            while (!logged) {

                outVideo.println("Insert login:");
                String username = inKeyboard.readLine();

                outVideo.println("Inserire password:");
                String password = inKeyboard.readLine();

                //ObjectOutputStream outSocket = clientOutHandlerCLI.getCommonOut().getSocketOut();
                //ObjectInputStream inSocket = clientInHandlerCLI.getSocketIn();

                socketOut.writeObject("login");
                socketOut.flush();
                socketOut.writeObject(username);
                socketOut.flush();
                socketOut.writeObject(password);
                socketOut.flush();

                logged = socketIn.readBoolean();

                if (logged)
                    outVideo.println("Login correctly done");
                else
                    outVideo.println("Nome utente in uso con altra password");


            }

            //TODO: spostato da dentro al while a fuori

            PlayerColor playerColor = (PlayerColor)socketIn.readObject();

            setPlayerColor(playerColor);

            clientInHandlerCLI.getCommonView().setPlayerColor(playerColor);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }

    }

    private void playNewGameCLI() {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        clientInHandlerCLI.setClientOutHandlerCLI(clientOutHandlerCLI);
        //clientOutHandlerCLI.setClientInHandlerCLI(clientInHandlerCLI);

        CommonView commonView = new CommonView();
        commonView.setInputChecker(new InputChecker());
        commonView.setPlayerColor(playerColor);

        clientOutHandlerCLI.setCommonView(commonView);
        clientInHandlerCLI.setCommonView(commonView);

        executor.submit(clientInHandlerCLI);
        executor.submit(clientOutHandlerCLI);
    }

    public void playNewGameGUI() {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        clientInHandlerGUI.setCommonOut(clientOutHandlerGUI.getCommonOut());
        //clientOutHandlerGUI.setClientInHandlerGUI(clientInHandlerGUI);

        CommonView commonView = new CommonView();
        commonView.setInputChecker(new InputChecker());
        commonView.setPlayerColor(playerColor);

        clientOutHandlerGUI.setCommonView(commonView);
        clientInHandlerGUI.setCommonView(commonView);

        executor.submit(clientInHandlerGUI);
        executor.submit(clientOutHandlerGUI);
    }


    private void connect() {

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


            switch (enumInterface) {

                case CLI:
                    this.clientOutHandlerCLI = new ClientOutHandlerCLI(socketOut);
                    this.clientInHandlerCLI = new ClientInHandlerCLI(socketIn);
                    break;

                case GUI:
                    this.clientOutHandlerGUI = new ClientOutHandlerGUI(socketOut);
                    this.clientInHandlerGUI = new ClientInHandlerGUI(socketIn);
            }


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

    /*public static void main(String[] args) throws UnknownHostException, IOException{
        ClientSocket client = new ClientSocket();
        client.startClientCLI();
    }*/

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
