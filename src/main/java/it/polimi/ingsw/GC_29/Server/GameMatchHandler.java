package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRemoteInterface;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Controllers.Input.JoinGame;
import it.polimi.ingsw.GC_29.Model.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMI.RMIView;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;
import it.polimi.ingsw.GC_29.Server.Socket.PlayerSocket;
import it.polimi.ingsw.GC_29.Server.Socket.ServerSocketView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by Christian on 12/06/2017.
 */
public class GameMatchHandler implements LogoutInterface{

    private Map<String,ServerNewGame> newGameMap;

    private List<String> loggedPlayersList;

    private Map<String, ServerNewGame> clientsCurrentMatchMap;

    private Boolean lobbyCreated = false;

    private final int maxNumberOfPlayers = 2;

    private Timer timer;

    private boolean minClientNumberReached = false;

    private long startTime;

    private int indexMatch = 0;

    private String currentMatchID;

    private final long elapsedTime = 30000;

    private int currentClientListSize = 0;

    private final Map<String, String> userPassword = new HashMap<>();

    private ExecutorService executor = Executors.newCachedThreadPool();

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());


    public GameMatchHandler(){

        newGameMap = new HashMap<>();

        loggedPlayersList = new ArrayList<>();

        clientsCurrentMatchMap = new HashMap<>();
    }



    synchronized public void addClient(String username, PlayerSocket playerSocket) throws RemoteException {

        loggedPlayersList.add(username);

        if(clientsCurrentMatchMap.containsKey(username)){

            try {
                reconnectClient(playerSocket, username);
            } catch (IOException e) {
                LOGGER.info((Supplier<String>) e);
            }

            return;
        }

        if(!lobbyCreated){

            lobbySettings();

            newGameMap.put(currentMatchID, new ServerNewGame(username, playerSocket, this));

        }

        else if(currentClientListSize < maxNumberOfPlayers){

            newGameMap.get(currentMatchID).addClient(username, playerSocket);
            currentClientListSize++;
        }

        evaluateConditions();

    }



    synchronized public void addClient(ClientRemoteInterface clientStub) throws RemoteException {

        loggedPlayersList.add(clientStub.getUserName());

        if(clientsCurrentMatchMap.containsKey(clientStub.getUserName())){

            reconnectClient(clientStub);

            return;
        }

        System.out.println("IL CLIENT NON E' IN ALCUNA PARTITA");

        if(!lobbyCreated){

            lobbySettings();

            newGameMap.put(currentMatchID, new ServerNewGame(clientStub, this));

        }

        else if(currentClientListSize < maxNumberOfPlayers){

            newGameMap.get(currentMatchID).addClient(clientStub);
            currentClientListSize++;
        }

        evaluateConditions();

    }

    synchronized private void reconnectClient(PlayerSocket playerSocket, String username) throws IOException {

        System.out.println("Creo server socket view");

        System.out.println("SONO IN RECONNECT CLIENT SOCKET");

        ServerNewGame clientCurrentMatch = clientsCurrentMatchMap.get(username);

        PlayerColor playerColor = clientCurrentMatch.getClientPlayerColorMap().get(username);

        ObjectOutputStream socketOut = playerSocket.getSocketOut();

        try {
            socketOut.writeObject(playerColor);
            socketOut.flush();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        }

        Model model = clientCurrentMatch.getGameSetup().getModel();


        ServerSocketView serverSocketView = new ServerSocketView(playerSocket, model, playerColor, username);

        serverSocketView.registerObserver(clientCurrentMatch.getController());
        System.out.println("SONO IN GAMEMATCHHANDLER AGGIUNGO AI RECONNECTED " + model.getPlayer(playerColor).getPlayerID());
        clientCurrentMatch.getController().getPlayerReconnected().add(model.getPlayer(playerColor));


        model.registerObserver(serverSocketView);
        model.getPlayer(playerColor).registerObserver(serverSocketView);

        model.getPlayer(playerColor).getActualGoodSet().registerObserver(clientCurrentMatch.getTrackController());

        serverSocketView.registerLogout(this);
        setClientMatch(model.getPlayer(playerColor).getPlayerID(),  clientCurrentMatch);


        serverSocketView.notifyObserver(new JoinGame(playerColor));


        executor.submit(serverSocketView);


    }

    synchronized private void reconnectClient(ClientRemoteInterface clientStub) throws RemoteException {


        String username = clientStub.getUserName();

        ServerNewGame clientCurrentMatch = clientsCurrentMatchMap.get(username);

        clientStub.setPlayerColor(clientCurrentMatch.getClientPlayerColorMap().get(username));

        Model model = clientCurrentMatch.getGameSetup().getModel();

        RMIView rmiView = new RMIView(model, clientStub.getPlayerColor(), username);

        UnicastRemoteObject.exportObject(rmiView, 0);

        //controller observes this view
        rmiView.registerObserver(clientCurrentMatch.getController());
        //add the reconnected player in a list of the controller
        clientCurrentMatch.getController().getPlayerReconnected().add(model.getPlayer(clientStub.getPlayerColor()));

        //this view observes the model
        clientCurrentMatch.getGameSetup().getModel().registerObserver(rmiView);
        clientCurrentMatch.getGameSetup().getModel().getPlayer(clientStub.getPlayerColor()).registerObserver(rmiView);

        clientCurrentMatch.getGameSetup().getModel().getPlayer(clientStub.getPlayerColor()).getActualGoodSet().registerObserver(clientCurrentMatch.getTrackController());

        //registred view in gameMatchHandler
        rmiView.registerLogout(this);
        setClientMatch(username, clientCurrentMatch);


        RMIViewRemote rmiViewStub = rmiView;
        clientStub.runNewGame(rmiViewStub);

        clientStub.joinGame();

}

    private void lobbySettings() {

        lobbyCreated = true;

        indexMatch++;

        currentMatchID = "match number:" + indexMatch;

        System.out.println("LOBBY CREATA");

        currentClientListSize++;

    }

    private void evaluateConditions() {

        evaluateMinCondition();

        if(evaluateConditionNewGame()){

            System.out.println("NUOVA PARTITA!");

            timer.cancel();

            runNewGame();

        }
    }

    private void runNewGame(){

        executor.submit(getCurrentNewGame());

        lobbyCreated = false;

        minClientNumberReached = false;

        currentClientListSize=0;
    }


    public void evaluateMinCondition(){

        if(!minClientNumberReached && currentClientListSize == 2){

            minClientNumberReached = true;

            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {

                    System.out.println("ESEGUO TASK");
                    runNewGame();
                }
            }, elapsedTime);
        }

    }

    public boolean evaluateConditionNewGame() {

        Boolean result = (currentClientListSize == maxNumberOfPlayers);

        if(result){
            System.out.println("LE CONDIZIONI PER NUOVA PARTITA SONO VALIDE");
        }

        System.out.println(result);
        return result;
    }

    public Map<String, String> getUserPassword() {
        return userPassword;
    }

    public boolean isLobbyCreated() {
        return lobbyCreated;
    }

    public ServerNewGame getCurrentNewGame() {
        return newGameMap.get(currentMatchID);
    }

    public void setLobbyCreated(boolean lobbyCreated) {
        this.lobbyCreated = lobbyCreated;
    }

    public Boolean verifyLoggedClient(String username){

        for (String clientUsername : loggedPlayersList) {

            if(clientUsername.equals(username)){

                return true;
            }
        }

        return false;
    }


    @Override
    public void clientDisconnected(String username) {

        loggedPlayersList.remove(username);

    }

    @Override
    public void setClientMatch(String username, ServerNewGame match) {
        clientsCurrentMatchMap.put(username, match);
        System.out.println("CLIENT LEGATO AD UNA PARTITA");
    }

    @Override
    public Map<String, ServerNewGame> getClientMatch() {
        return clientsCurrentMatchMap;
    }

    @Override
    public Map<String, ServerNewGame> getMatchMap() {

        return newGameMap;
    }

}
