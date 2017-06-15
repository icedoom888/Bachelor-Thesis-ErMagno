package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRemoteInterface;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Components.PersonalBoard;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Server.Socket.PlayerSocket;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Christian on 12/06/2017.
 */
public class GameMatchHandler {

    private Map<String,ServerNewGame> newGameList;

    private Boolean lobbyCreated = false;

    private final int maxNumberOfPlayers = 2;

    private boolean minClientNumberReached = false;

    private long startTime;

    private int indexMatch = 0;

    private String currentMatchID;

    private final long elapsedTime = 300000;

    private int currentClientListSize = 0;

    private final Map<String, String> userPassword = new HashMap<>();
    private ExecutorService executor = Executors.newCachedThreadPool();

    public GameMatchHandler(){

        newGameList = new HashMap<>();
    }



    synchronized public void addClient(String username, PlayerSocket playerSocket) throws RemoteException {

        if(!lobbyCreated){

            lobbySettings();

            newGameList.put(currentMatchID, new ServerNewGame(username, playerSocket));

        }

        else if(currentClientListSize < maxNumberOfPlayers){

            newGameList.get(currentMatchID).addClient(username, playerSocket);
            currentClientListSize++;
        }

        evaluateConditions();

    }



    synchronized public void addClient(ClientRemoteInterface clientStub) throws RemoteException {

        if(!lobbyCreated){

            lobbySettings();

            newGameList.put(currentMatchID, new ServerNewGame(clientStub));

        }

        else if(currentClientListSize < maxNumberOfPlayers){

            newGameList.get(currentMatchID).addClient(clientStub);
            currentClientListSize++;
        }

        evaluateConditions();

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

            executor.submit(getCurrentNewGame());

            lobbyCreated = false;

            minClientNumberReached = false;

            currentClientListSize=0;

        }
    }


    public void evaluateMinCondition(){

        if(!minClientNumberReached && currentClientListSize == 2){

            minClientNumberReached = true;

            startTime = System.currentTimeMillis();
        }

    }

    public boolean evaluateConditionNewGame() {

        Boolean result = ((System.currentTimeMillis() - startTime >= elapsedTime && minClientNumberReached) || currentClientListSize == maxNumberOfPlayers);

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
        return newGameList.get(currentMatchID);
    }

    public void setLobbyCreated(boolean lobbyCreated) {
        this.lobbyCreated = lobbyCreated;
    }


}
