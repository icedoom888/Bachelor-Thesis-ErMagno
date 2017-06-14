package it.polimi.ingsw.GC_29.Client.ClientSocket;

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

    private Socket socket;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private ClientOutHandler clientOutHandler;
    private ClientInHandler clientInHandler;

    public ClientSocket() throws IOException {
    }


    public void startClient() throws UnknownHostException, IOException {
        System.out.println("Client avviato");

        try {
            connect();
            login();
            playNewGame();
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

    private void playNewGame() {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(clientInHandler);
        executor.submit(clientOutHandler);
    }

    private void login() {

        try {
            boolean logged = false;

            while (!logged) {

                outVideo.println("Insert login:");
                String username = inKeyboard.readLine();

                outVideo.println("Inserire password:");
                String password = inKeyboard.readLine();

                ObjectOutputStream outSocket = clientOutHandler.getSocketOut();
                ObjectInputStream inSocket = clientInHandler.getSocketIn();

                outSocket.writeObject("login");
                outSocket.flush();
                outSocket.writeObject(username);
                outSocket.flush();
                outSocket.writeObject(password);
                outSocket.flush();

                logged = Boolean.valueOf(inSocket.readBoolean()).booleanValue();

                if (logged)
                    outVideo.println("Login correctly done");
                else
                    outVideo.println("Nome utente in uso con altra password");
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

    }

    private void connect() {

        try {

            System.out.println("Il client tenta di connettersi");

            socket = new Socket(IP, PORT);

            System.out.println("Connection created");

            //canali di comunicazione


            inKeyboard = new BufferedReader(new InputStreamReader(System.in));
            outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);

            //Creates one thread to send messages to the server

            this.clientOutHandler = new ClientOutHandler(
                    new ObjectOutputStream(socket.getOutputStream()));


            //Creates one thread to receive messages from the server

            this.clientInHandler = new ClientInHandler(
                    new ObjectInputStream(socket.getInputStream()));

            clientOutHandler.setClientInHandler(clientInHandler);
            clientInHandler.setClientOutHandler(clientOutHandler);

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
        client.startClient();
    }*/
}
