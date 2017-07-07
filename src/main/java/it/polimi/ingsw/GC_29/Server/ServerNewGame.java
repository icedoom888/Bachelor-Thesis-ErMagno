package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRemoteInterface;
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
import java.util.logging.Logger;

/**
 * Created by Christian on 12/06/2017.
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

    //private final int maxNumberOfPlayers = 2;

    private ArrayList<PlayerColor> playerColors = new ArrayList<>();

    private GameSetup gameSetup;

    private Controller controller;

    TrackController trackController;

    private final static int PORT = 29999;

    private final String NAME = "rmiView";

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private HashMap<ServerSocketView, Player> serverSocketViews = new HashMap<>();

    private Boolean isRunning = true;



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
            LOGGER.info((Supplier<String>) e);
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
            LOGGER.info((Supplier<String>) e);
        }

        try {
            controller = new Controller(gameSetup.getModel());
            controller.setCurrentMatch(this);
        } catch (Exception e) {
            LOGGER.info((Supplier<String>) e);
        }

        trackController = new TrackController(gameSetup.getModel());


        /*try {
            startRMIView(gameSetup.getModel(), controller);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }*/

        //startSocketView();


        for (Player player : playersSocketMap.keySet()) {

            try {

                System.out.println("Creo server socket view");

                ServerSocketView serverSocketView = new ServerSocketView(playersSocketMap.get(player), gameSetup.getModel(), player.getPlayerColor(), player.getPlayerID());

                serverSocketView.registerObserver(controller);
                gameSetup.getModel().registerObserver(serverSocketView);
                gameSetup.getModel().getPlayer(player.getPlayerColor()).registerObserver(serverSocketView);

                player.getActualGoodSet().registerObserver(trackController);

                serverSocketView.registerLogout(logoutInterface);
                logoutInterface.setClientMatch(gameSetup.getModel().getPlayer(player.getPlayerColor()).getPlayerID(),  this);

                //serverSocketView.notifyObserver(new Initialize(player.getPlayerColor()));

                serverSocketViews.put(serverSocketView, player);
                //executorService.submit(serverSocketView);

            }

            catch (IOException e) {
                LOGGER.info((Supplier<String>) e);
            } catch (Exception e) {
                LOGGER.info((Supplier<String>) e);
            }
        }

        System.out.println("DOVE TI BLOCCHI");

        for (ClientRemoteInterface clientRemoteInterface : clientRMIList) {
            System.out.println("Non entri nel for? giusto");


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
                    LOGGER.info((Supplier<String>) e);
                }
                //La passo al client

            } catch (RemoteException e) {
                LOGGER.info((Supplier<String>) e);
            }

        }


        System.out.println("Qua arrivi?");

        try {
            gameSetup.setGoodsForPlayers();
            gameSetup.setExcommunicationTiles();
            gameSetup.setLeaderCards();
        } catch (Exception e) {
            LOGGER.info((Supplier<String>) e);
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
                LOGGER.info((Supplier<String>) e);
            }
        }


        while (isRunning){

            /*try {
                Thread.sleep((long) 15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            b = false;*/

        }

        System.out.println("SONO FUORI CICLO NEW GAME CHIUDO GIOCO");
        if(logoutInterface.getMatchMap().containsValue(this)){
            logoutInterface.getMatchMap().remove(this);
        }

    }

    private void startSocketView() {

        //TODO: implementa socket view
        Boolean b = true;
        while (b){}
    }

    /*private void startRMIView(Model model, Controller controller) throws RemoteException, AlreadyBoundException {
        //create the registry to publish remote objects
        Registry registry = LocateRegistry.createRegistry(PORT);
        System.out.println("Constructing the RMI registry");
        // Create the RMI View, that will be shared with the client
        RMIView rmiView=new RMIView();
        //controller observes this view
        rmiView.registerObserver(controller);
        //this view observes the model
        model.registerObserver(rmiView);
        // publish the view in the registry as a remote object
        RMIViewRemote viewRemote=(RMIViewRemote) UnicastRemoteObject.
                exportObject(rmiView, 0);
        System.out.println("Binding the server implementation to the registry");
        registry.bind(NAME, rmiView); // TODO: il bind verrà chiamato ad ogni inizio partita --> ti darà eccezione AlreadyBound, sistema
    }*/

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
            LOGGER.info((Supplier<String>) e);
        }

    }

    public void addClient(ClientRemoteInterface clientStub) throws RemoteException {

        clientStub.setPlayerColor(playerColors.remove(0));
        clientPlayerColorMap.put(clientStub.getUserName(), clientStub.getPlayerColor());
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

   /* public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }*/


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