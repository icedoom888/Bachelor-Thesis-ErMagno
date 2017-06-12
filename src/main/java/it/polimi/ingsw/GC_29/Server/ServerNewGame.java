package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRemoteInterface;
import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Christian on 12/06/2017.
 */
public class ServerNewGame implements Runnable {

    private ArrayList<ClientRemoteInterface> clientList;

    private Boolean minClientNumberReached = false;

    private long elapsedTime = 300000;

    private long startTime;

    private final int maxNumberOfPlayers = 2;

    private ArrayList<PlayerColor> playerColors = new ArrayList<>();

    private GameSetup gameSetup;

    private final static int PORT = 29999;

    private final String NAME = "rmiView";


    public ServerNewGame(ClientRemoteInterface client) throws RemoteException {


        playerColors.add(PlayerColor.BLUE);
        playerColors.add(PlayerColor.GREEN);
        playerColors.add(PlayerColor.RED);
        playerColors.add(PlayerColor.YELLOW);

        clientList = new ArrayList<>();

        client.setPlayerColor(playerColors.remove(0));

        clientList.add(client);



    }

    @Override
    public void run() {

        System.out.println("ESEGUO NUOVA PARTITA");

        try {
            gameSetup = new GameSetup(clientList);
            gameSetup.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Controller controller = new Controller(gameSetup.getGameStatus());

        try {
            startRMIView(gameSetup.getGameStatus(), controller);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        //startSocketView();

        for (ClientRemoteInterface clientRemoteInterface : clientList) {

            try {
                clientRemoteInterface.initializeNewGame();
                System.out.println("CLIENT AVVISATI NUOVA PARTITA");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    private void startSocketView() {

        //TODO: implementa socket view
        Boolean b = true;
        while (b){}
    }

    private void startRMIView(GameStatus gameStatus, Controller controller) throws RemoteException, AlreadyBoundException {

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
    }

    public void addClient(ClientRemoteInterface clientStub) {

        clientList.add(clientStub);
    }

    /*public void evaluateMinCondition(){

        if(!minClientNumberReached && clientList.size() >= 2){

            minClientNumberReached = true;

            startTime = System.currentTimeMillis();
        }

    }

    public boolean evaluateConditionNewGame() {

        Boolean result = ((System.currentTimeMillis() - startTime >= elapsedTime && minClientNumberReached) || clientList.size() == maxNumberOfPlayers);

        if(result){
            System.out.println("LE CONDIZIONI PER NUOVA PARTITA SONO VALIDE");
        }

        return result;
    }*/


    public ArrayList<ClientRemoteInterface> getClientList() {
        return clientList;
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }
}