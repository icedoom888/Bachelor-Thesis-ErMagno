package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRemoteInterface;
import it.polimi.ingsw.GC_29.Components.PersonalBoard;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMI.RMIView;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;
import it.polimi.ingsw.GC_29.Server.Socket.PlayerSocket;
import it.polimi.ingsw.GC_29.Server.Socket.ServerSocketView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Christian on 12/06/2017.
 */
public class ServerNewGame implements Runnable {

    private ArrayList<ClientRemoteInterface> clientRMIList;
    private HashMap<Player, PlayerSocket> playersSocketMap;
    private ArrayList<Player> players;

    private Boolean minClientNumberReached = false;

    private long elapsedTime = 300000;

    private long startTime;

    private final int maxNumberOfPlayers = 2;

    private ArrayList<PlayerColor> playerColors = new ArrayList<>();

    private GameSetup gameSetup;

    private final static int PORT = 29999;

    private final String NAME = "rmiView";

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private HashMap<ServerSocketView, Player> serverSocketViews = new HashMap<>();



    public ServerNewGame(ClientRemoteInterface client) throws RemoteException {


        addColors();

        clientRMIList = new ArrayList<>();

        players = new ArrayList<>();

        client.setPlayerColor(playerColors.remove(0));

        Player player = new Player(client.getUserName(), client.getPlayerColor(), new PersonalBoard(6));

        players.add(player);

        clientRMIList.add(client);

        playersSocketMap = new HashMap<>();

    }

    public ServerNewGame(String username, PlayerSocket playerSocket) {

        addColors();

        PlayerColor playerColor = playerColors.remove(0);

        Player player = new Player(username, playerColor, new PersonalBoard(6));

        clientRMIList = new ArrayList<>();

        playersSocketMap = new HashMap<>();
        playersSocketMap.put(player, playerSocket);
        players = new ArrayList<>();
        players.add(player);

        ObjectOutputStream socketOut = playerSocket.getSocketOut();

        try {
            socketOut.writeObject(playerColor);
            socketOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addColors() {

        playerColors.add(PlayerColor.BLUE);
        playerColors.add(PlayerColor.GREEN);
        playerColors.add(PlayerColor.RED);
        playerColors.add(PlayerColor.YELLOW);

    }

    @Override
    public void run() {

        System.out.println("ESEGUO NUOVA PARTITA");


        try {
            gameSetup = new GameSetup(players);
            gameSetup.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Controller controller = null;
        try {
            controller = new Controller(gameSetup.getGameStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        TrackController trackController = new TrackController(gameSetup.getGameStatus());


        /*try {
            startRMIView(gameSetup.getGameStatus(), controller);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }*/

        //startSocketView();


        for (Player player : playersSocketMap.keySet()) {

            try {

                System.out.println("Creo server socket view");

                ServerSocketView serverSocketView = new ServerSocketView(playersSocketMap.get(player), gameSetup.getGameStatus());

                serverSocketView.registerObserver(controller);
                gameSetup.getGameStatus().registerObserver(serverSocketView);
                gameSetup.getGameStatus().getPlayer(player.getPlayerColor()).registerObserver(serverSocketView);

                player.getActualGoodSet().registerObserver(trackController);

                //serverSocketView.notifyObserver(new Initialize(player.getPlayerColor()));

                serverSocketViews.put(serverSocketView, player);
                //executorService.submit(serverSocketView);

            }

            catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("DOVE TI BLOCCHI");

        for (ClientRemoteInterface clientRemoteInterface : clientRMIList) {
            System.out.println("Non entri nel for? giusto");


            try {
                // Create the RMI View, that will be shared with the client
                RMIView rmiView = new RMIView(gameSetup.getGameStatus());

                RMIViewRemote viewRemote=(RMIViewRemote) UnicastRemoteObject.
                        exportObject(rmiView, 0);

                //controller observes this view
                rmiView.registerObserver(controller);

                //this view observes the model
                gameSetup.getGameStatus().registerObserver(rmiView);
                gameSetup.getGameStatus().getPlayer(clientRemoteInterface.getPlayerColor()).registerObserver(rmiView);

                try {
                    RMIViewRemote rmiViewStub = rmiView;
                    clientRemoteInterface.initializeNewGame(rmiViewStub);
                    System.out.println("CLIENT AVVISATI NUOVA PARTITA");
                }

                catch (RemoteException e) {
                        e.printStackTrace();}
                //La passo al client

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }


        System.out.println("Qua arrivi?");

        try {
            gameSetup.setGoodsForPlayers();
            gameSetup.setExcommunicationTiles();
        } catch (Exception e) {
            e.printStackTrace();
        }

        controller.setCardsOnTowers();

        for (ServerSocketView serverSocketView : serverSocketViews.keySet()) {

            try {
                serverSocketView.notifyObserver(new Initialize(serverSocketViews.get(serverSocketView).getPlayerColor()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            executorService.submit(serverSocketView);

        }




        Boolean b = true;

        while (b){}

    }

    private void startSocketView() {

        //TODO: implementa socket view
        Boolean b = true;
        while (b){}
    }

    /*private void startRMIView(GameStatus gameStatus, Controller controller) throws RemoteException, AlreadyBoundException {
        //create the registry to publish remote objects
        Registry registry = LocateRegistry.createRegistry(PORT);
        System.out.println("Constructing the RMI registry");
        // Create the RMI View, that will be shared with the client
        RMIView rmiView=new RMIView();
        //controller observes this view
        rmiView.registerObserver(controller);
        //this view observes the model
        gameStatus.registerObserver(rmiView);
        // publish the view in the registry as a remote object
        RMIViewRemote viewRemote=(RMIViewRemote) UnicastRemoteObject.
                exportObject(rmiView, 0);
        System.out.println("Binding the server implementation to the registry");
        registry.bind(NAME, rmiView); // TODO: il bind verrà chiamato ad ogni inizio partita --> ti darà eccezione AlreadyBound, sistema
    }*/

    public void addClient(String username, PlayerSocket playerSocket) {

        PlayerColor playerColor = playerColors.remove(0);
        Player player = new Player(username, playerColor, new PersonalBoard(6));
        players.add(player);
        playersSocketMap.put(player, playerSocket);

        ObjectOutputStream socketOut = playerSocket.getSocketOut();

        try {
            socketOut.writeObject(playerColor);
            socketOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addClient(ClientRemoteInterface clientStub) throws RemoteException {

        clientStub.setPlayerColor(playerColors.remove(0));
        clientRMIList.add(clientStub);
        Player player = new Player(clientStub.getUserName(), clientStub.getPlayerColor(), new PersonalBoard(6));
        players.add(player);    }

    /*public void evaluateMinCondition(){
        if(!minClientNumberReached && clientRMIList.size() >= 2){
            minClientNumberReached = true;
            startTime = System.currentTimeMillis();
        }
    }
    public boolean evaluateConditionNewGame() {
        Boolean result = ((System.currentTimeMillis() - startTime >= elapsedTime && minClientNumberReached) || clientRMIList.size() == maxNumberOfPlayers);
        if(result){
            System.out.println("LE CONDIZIONI PER NUOVA PARTITA SONO VALIDE");
        }
        return result;
    }*/


    public ArrayList<ClientRemoteInterface> getClientRMIList() {
        return clientRMIList;
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }


}