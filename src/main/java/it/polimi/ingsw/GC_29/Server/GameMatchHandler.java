package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRemoteInterface;

import java.rmi.RemoteException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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

    public GameMatchHandler(){

        newGameList = new HashMap<>();
    }

    synchronized public void addClient(ClientRemoteInterface clientStub) throws RemoteException {

        if(!lobbyCreated){

            lobbyCreated = true;

            indexMatch++;

            currentMatchID = "match number:" + indexMatch;

            System.out.println("LOBBY CREATA");

            newGameList.put(currentMatchID, new ServerNewGame(clientStub));

            currentClientListSize++;
        }

        else if(currentClientListSize < maxNumberOfPlayers){

            newGameList.get(currentMatchID).addClient(clientStub);
            currentClientListSize++;
        }
    }


    public void evaluateMinCondition(){

        if(!minClientNumberReached && currentClientListSize >= 2){

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
