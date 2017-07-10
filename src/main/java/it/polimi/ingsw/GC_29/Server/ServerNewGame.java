package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRemoteInterface;
import it.polimi.ingsw.GC_29.Controllers.Input.Disconnection;
import it.polimi.ingsw.GC_29.Controllers.Input.Initialize;
import it.polimi.ingsw.GC_29.Model.PersonalBoard;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Model.Player;
import it.polimi.ingsw.GC_29.Model.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMI.RMIView;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;
import it.polimi.ingsw.GC_29.Server.Socket.PlayerSocket;
import it.polimi.ingsw.GC_29.Server.Socket.ServerSocketView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Christian on 12/06/2017.
 *
 * An instance of ServerNewGame is created when a game is created. It has a list of all the players
 * that are playing and a map for players playing in socket that contains the player and socket assigned.
 */
public class ServerNewGame implements Runnable {

    private ArrayList<ClientRemoteInterface> clientRMIList;
    private HashMap<Player, PlayerSocket> playersSocketMap;
    private ArrayList<Player> players;
    private LogoutInterface logoutInterface;
    private Map<String, PlayerColor> clientPlayerColorMap;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());


    private Boolean minClientNumberReached = false;

    private long elapsedTime = 300000;

    private long startTime;

    private ArrayList<PlayerColor> playerColors = new ArrayList<>();

    private GameSetup gameSetup;

    private Controller controller;

    TrackController trackController;

    private final static int PORT = 29999;

    private final String NAME = "rmiView";

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private HashMap<ServerSocketView, Player> serverSocketViews = new HashMap<>();

    private Boolean isRunning = true;


    /**
     * Constructor for first player playing in RMI
     * @param client
     * @param logoutInterface
     * @throws RemoteException
     */
    public ServerNewGame(ClientRemoteInterface client, LogoutInterface logoutInterface) throws RemoteException {

        this.logoutInterface = logoutInterface;

        addColors();

        PlayerColor playerColor = playerColors.remove(0);

        client.setPlayerColor(playerColor);

        clientRMIList = new ArrayList<>();

        players = new ArrayList<>();

        clientPlayerColorMap = new HashMap<>();

        clientPlayerColorMap.put(client.getUserName(), playerColor);

        Player player = new Player(client.getUserName(), playerColor, new PersonalBoard(6));

        players.add(player);

        clientRMIList.add(client);

        playersSocketMap = new HashMap<>();

    }



    /**
     * Constructor for first player playing in Socket
     * @param username
     * @param playerSocket
     * @param logoutInterface
     */
    public ServerNewGame(String username, PlayerSocket playerSocket, LogoutInterface logoutInterface) {

        this.logoutInterface = logoutInterface;

        clientPlayerColorMap = new HashMap<>();

        addColors();

        PlayerColor playerColor = playerColors.remove(0);

        clientPlayerColorMap.put(username, playerColor);

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
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

    }

    private void addColors() {

        playerColors.add(PlayerColor.BLUE);
        playerColors.add(PlayerColor.GREEN);
        playerColors.add(PlayerColor.RED);
        playerColors.add(PlayerColor.YELLOW);

    }

    /**
     * When max numbers of players for one game is reached or when the timer has ended, the serverNewGame
     * is started. It creates a new GameSetup and initialize the model. Then a controller and a track controller
     * are created. For every player (playing in socket or rmi) the serverView is createdand  is registered
     * as an observer of the model and of the player, the track controller is registered as an observer of
     * the goodSet of the player. A logout interface is set in the serverView and then every view with its
     * corresponding player is saved in a map to handle disconnections.
     */
    @Override
    public void run() {

        System.out.println("ESEGUO NUOVA PARTITA");


        try {
            gameSetup = new GameSetup(players);
            gameSetup.init();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

        try {
            controller = new Controller(gameSetup.getModel());
            controller.setCurrentMatch(this);
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

        trackController = new TrackController(gameSetup.getModel());


        for (Map.Entry<Player, PlayerSocket> entry : playersSocketMap.entrySet()) {

            Player player = entry.getKey();
            PlayerSocket playerSocket = entry.getValue();

            try {

                System.out.println("Creo server socket view");

                ServerSocketView serverSocketView = new ServerSocketView(playerSocket, gameSetup.getModel(), player.getPlayerColor(), player.getPlayerID());

                serverSocketView.registerObserver(controller);
                gameSetup.getModel().registerObserver(serverSocketView);
                gameSetup.getModel().getPlayer(player.getPlayerColor()).registerObserver(serverSocketView);

                player.getActualGoodSet().registerObserver(trackController);

                serverSocketView.registerLogout(logoutInterface);
                logoutInterface.setClientMatch(gameSetup.getModel().getPlayer(player.getPlayerColor()).getPlayerID(),  this);


                serverSocketViews.put(serverSocketView, player);

            }

            catch (IOException e) {
                LOGGER.log(Level.INFO, e.getMessage(), e);
            } catch (Exception e) {
                LOGGER.log(Level.INFO, e.getMessage(), e);
            }

        }


        for (ClientRemoteInterface clientRemoteInterface : clientRMIList) {

            try {
                // Create the RMI View, that will be shared with the client
                RMIView rmiView = new RMIView(gameSetup.getModel(), clientRemoteInterface.getPlayerColor(), clientRemoteInterface.getUserName());

                UnicastRemoteObject.exportObject(rmiView, 0);

                //controller observes this view
                rmiView.registerObserver(controller);

                //this view observes the model
                gameSetup.getModel().registerObserver(rmiView);
                gameSetup.getModel().getPlayer(clientRemoteInterface.getPlayerColor()).registerObserver(rmiView);

                gameSetup.getModel().getPlayer(clientRemoteInterface.getPlayerColor()).getActualGoodSet().registerObserver(trackController);

                //registred view in gameMatchHandler
                rmiView.registerLogout(logoutInterface);
                logoutInterface.setClientMatch(clientRemoteInterface.getUserName(), this);

                try {
                    RMIViewRemote rmiViewStub = rmiView;
                    clientRemoteInterface.runNewGame(rmiViewStub);
                    System.out.println("CLIENT AVVISATI NUOVA PARTITA");
                }

                catch (RemoteException e) {
                    LOGGER.log(Level.INFO, e.getMessage(), e);
                }
                //La passo al client

            } catch (RemoteException e) {
                LOGGER.log(Level.INFO, e.getMessage(), e);
            }

        }

        try {
            gameSetup.setGoodsForPlayers();
            gameSetup.setExcommunicationTiles();
            gameSetup.setLeaderCards();
            gameSetup.sendIdGui();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

        controller.setCardsOnTowers();

        for (Map.Entry<ServerSocketView, Player> entry : serverSocketViews.entrySet()) {

            entry.getKey().notifyObserver(new Initialize(entry.getValue().getPlayerColor()));

            executorService.submit(entry.getKey());
        }

        for (ClientRemoteInterface clientRemoteInterface : clientRMIList) {

            try {
                clientRemoteInterface.initialize();
            } catch (RemoteException e) {
                LOGGER.log(Level.INFO, e.getMessage(), e);
            }
        }


        while (isRunning){


        }

        System.out.println("SONO FUORI CICLO NEW GAME CHIUDO GIOCO");
        if(logoutInterface.getMatchMap().containsValue(this)){
            logoutInterface.getMatchMap().remove(this);
        }

    }


    /**
     * After the creation of the serverNewGame, every time a client wants to enter
     * this game, this method is called and it saves him in the map of username - playerColor
     * and in the socket case also in the map of player - socket. Finally the new created player is
     * added to the list of player
     * @param username
     * @param playerSocket
     */
    public void addClient(String username, PlayerSocket playerSocket) {

        PlayerColor playerColor = playerColors.remove(0);
        System.out.println(clientPlayerColorMap == null);
        System.out.println(username);
        System.out.println(playerColor);
        clientPlayerColorMap.put(username, playerColor);
        Player player = new Player(username, playerColor, new PersonalBoard(6));
        players.add(player);
        playersSocketMap.put(player, playerSocket);

        ObjectOutputStream socketOut = playerSocket.getSocketOut();

        try {
            socketOut.writeObject(playerColor);
            socketOut.flush();
        } catch (IOException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

    }

    /**
     * addClient() case RMI
     * @param clientStub
     * @throws RemoteException
     */
    public void addClient(ClientRemoteInterface clientStub) throws RemoteException {

        clientStub.setPlayerColor(playerColors.remove(0));
        clientPlayerColorMap.put(clientStub.getUserName(), clientStub.getPlayerColor());
        clientRMIList.add(clientStub);
        Player player = new Player(clientStub.getUserName(), clientStub.getPlayerColor(), new PersonalBoard(6));
        players.add(player);
    }



    public ArrayList<ClientRemoteInterface> getClientRMIList() {
        return clientRMIList;
    }



    public GameSetup getGameSetup() {
        return gameSetup;
    }

    public Controller getController() {
        return controller;
    }

    public Map<String, PlayerColor> getClientPlayerColorMap() {
        return clientPlayerColorMap;
    }

    public TrackController getTrackController() {
        return trackController;
    }

    public void setIsRunning(Boolean isRunning){
        this.isRunning = isRunning;
    }

    public LogoutInterface getLogoutInterface() {
        return logoutInterface;
    }
}