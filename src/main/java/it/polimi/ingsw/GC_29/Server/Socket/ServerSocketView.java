package it.polimi.ingsw.GC_29.Server.Socket;

import it.polimi.ingsw.GC_29.Controllers.Change.Change;
import it.polimi.ingsw.GC_29.Controllers.Input.*;
import it.polimi.ingsw.GC_29.Model.*;
import it.polimi.ingsw.GC_29.Server.ObserverException;
import it.polimi.ingsw.GC_29.Server.View;
import it.polimi.ingsw.GC_29.Query.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by Lorenzotara on 14/06/17.
 *
 * ServerSocketView extends View and it is the view from the server side.
 * It waits for messages from the client: if they are string it means that it has to notify
 * the controller with an Input. If they are queries it means that it has to perform them and
 * then send the result back to the client.
 */
public class ServerSocketView extends View implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ServerSocketView.class.getName());
    private Socket socket;
    private GetValidActions validActionQuery;
    private ObjectInputStream socketIn;
    private ObjectOutputStream socketOut;
    private Model model;
    private PlayerColor playerColor;
    private final String username;
    private static final Object socketLock = new Object();


    public ServerSocketView(PlayerSocket playerSocket, Model model, PlayerColor playerColor, String playerID) throws IOException {
        this.socket = playerSocket.getSocket();
        this.socketIn = playerSocket.getSocketIn();
        this.socketOut = playerSocket.getSocketOut();
        this.model = model;
        this.validActionQuery = new GetValidActions();
        this.playerColor = playerColor;
        this.username = playerID;

    }

    @Override
    public void update(Change o) throws ObserverException {

        System.out.println("UPDATE from Server");
        System.out.println(o);

        try {

            socketOut.writeObject("Change");
            socketOut.flush();
            socketOut.reset();
            socketOut.writeObject(o);
            socketOut.flush();

        } catch (Exception e){

            LOGGER.info((Supplier<String>) e);
            handleDisconnection();
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void run() {

        Boolean b = true;

        while (b) {
            // await for incoming data from the client


            try {

                Object object = socketIn.readObject();

                // Il server riceve una query dal ClientOut
                if (object instanceof Query) {
                    handleQuery((Query)object);
                }

                if (object instanceof String) {
                    String string = (String)object;

                    switch (string) {

                        case "bonus tile":
                            int bonusTile = (int)socketIn.readObject();
                            notifyObserver(new BonusTileChosen(bonusTile));
                            break;

                        case "throw dices":
                            notifyObserver(new ThrowDices());
                            break;

                        case "family pawn chosen":
                            FamilyPawnType familyPawnType = (FamilyPawnType)socketIn.readObject();
                            notifyObserver(new UsePawnChosen(familyPawnType));
                            break;

                        case "skip action":
                            notifyObserver(new SkipAction());
                            break;

                        case "end turn":
                            notifyObserver(new EndTurn());
                            break;

                        case "execute action":
                            int actionIndex = (int)socketIn.readObject();
                            notifyObserver(new ExecuteAction(actionIndex));
                            break;

                        case "number of workers":
                            int workers = (int)socketIn.readObject();
                            notifyObserver(new ActivateCards(workers));
                            break;

                        case "pay to obtain cards chosen":
                            Map<String, Integer> activatedCardMap = (Map<String, Integer>)socketIn.readObject();
                            notifyObserver(new PayToObtainCardsChosen(activatedCardMap));
                            break;


                        case "cost chosen":
                            int costChosen = (int)socketIn.readObject();
                            notifyObserver(new PayCard(costChosen));
                            break;

                        case "council privileges chosen":
                            List<Integer> councilPrivileges = (List<Integer>)socketIn.readObject();
                            notifyObserver(new PrivilegeChosen(councilPrivileges));
                            break;

                        case "pray":
                            playerColor = (PlayerColor)socketIn.readObject();
                            notifyObserver(new Pray(true, playerColor));
                            break;

                        case "do not pray":
                            playerColor = (PlayerColor)socketIn.readObject();
                            notifyObserver(new Pray(false, playerColor));
                            break;

                        case "use leader cards GUI":
                            playerColor = (PlayerColor)socketIn.readObject();
                            notifyObserver(new UseLeaderCardsGUI(playerColor));
                            break;

                        case "activate leader card":
                            int index = (Integer)socketIn.readObject();
                            playerColor = (PlayerColor)socketIn.readObject();
                            notifyObserver(new LeaderAction(true, index, playerColor));
                            break;

                        case "discard leader card":
                            index = (Integer)socketIn.readObject();
                            playerColor = (PlayerColor)socketIn.readObject();
                            notifyObserver(new LeaderAction(false, index, playerColor));
                            break;

                        case "join game":
                            playerColor = (PlayerColor)socketIn.readObject();
                            notifyObserver(new JoinGame(playerColor));
                            break;

                        case "end game":
                            logoutInterface.getClientMatch().remove(username);
                            notifyObserver(new Closed());
                            return;


                    }
                }
            } catch (IOException e) {
                LOGGER.info((Supplier<String>) e);
            } catch (ClassNotFoundException e) {
                LOGGER.info((Supplier<String>) e);
            } catch (Exception e){
                LOGGER.info((Supplier<String>) e);
                handleDisconnection();
                b = false;
                try {
                    this.socket.close();
                    this.socketIn.close();
                    this.socketOut.close();
                    System.out.println("SOCKET CHIUSI");
                } catch (IOException e1) {
                    LOGGER.info((Supplier<String>) e1);
                }
                System.out.println("SONO USCITO DA HANDLE DISCONNECTION");
            }
        }

    }

    /**
     * When the ServerSocketView receives a query, this method understands which
     * class the query is instance of and then performs it.
     * @param q
     */
    private void handleQuery(Query q) {

        System.out.println("VIEW: received the query " + q);


        if (q instanceof GameBoardQuery) {

            sendOut("GameBoard");

            GameBoardQuery query = (GameBoardQuery)q;

            String gameBoard = query.perform(model);

            sendOut(gameBoard);
        }

        if (q instanceof PersonalBoardQuery) {

            sendOut("Personal Board");

            PersonalBoardQuery query = (PersonalBoardQuery)q;

            String personalBoard = query.perform(model);

            sendOut(personalBoard);

        }

        if (q instanceof GetAvailableLeaderCards) {

            sendOut("Leader");

            GetAvailableLeaderCards query = (GetAvailableLeaderCards)q;

            Map<Integer, Boolean> validLeaders = query.perform(model);

            sendOut(validLeaders);
        }

        if (q instanceof LeaderCardsQuery) {

            sendOut("Leader Cards");

            LeaderCardsQuery query = (LeaderCardsQuery)q;

            List<String> leaderCards = query.perform(model);

            sendOut(leaderCards);
        }

        if (q instanceof GetValidActions) {

            sendOut("Valid Actions");

            GetValidActions query = (GetValidActions) q;

            Map<Integer, String> validActions = query.perform(model);

            sendOut(validActions);
        }

        if (q instanceof GetCardsForWorkers) {


            sendOut("Get Cards For Workers");


            GetCardsForWorkers query = (GetCardsForWorkers) q;

            Map<Integer, ArrayList<String>> cardsForWorkers = query.perform(model);

            sendOut(cardsForWorkers);
        }

        if (q instanceof GetPossibleCosts) {

            sendOut("Get Possible Costs");

            GetPossibleCosts query = (GetPossibleCosts) q;

            Map<Integer, String> possibleCosts = query.perform(model);

            sendOut(possibleCosts);
        }

        if (q instanceof GetCouncilPrivileges) {

            sendOut("Get Council Privileges");

            GetCouncilPrivileges query = (GetCouncilPrivileges) q;

            List<Integer> councilPrivileges = query.perform(model);

            sendOut(councilPrivileges);
        }

        if (q instanceof GetGoodSet) {

            sendOut("Get GoodSet");

            GetGoodSet query = (GetGoodSet) q;

            GoodSet goodSet = query.perform(model);

            sendOut(goodSet);
        }

        if (q instanceof GetDevelopmentCard) {

            sendOut("Get Development Cards");

            GetDevelopmentCard query = (GetDevelopmentCard) q;

            List<String> developmentCards = query.perform(model);

            sendOut(developmentCards);
        }

        if (q instanceof GetTowerCard) {

            sendOut("Get Tower Cards");


            GetTowerCard query = (GetTowerCard) q;

            List<String> towerCards = query.perform(model);

            sendOut(towerCards);
        }

        if (q instanceof GetFamilyPawnAvailability) {

            sendOut("Get Family Pawns Availability");


            GetFamilyPawnAvailability query = (GetFamilyPawnAvailability) q;

            Map<FamilyPawn, Boolean> familyPawns = query.perform(model);

            sendOut(familyPawns);
        }

        if (q instanceof GetBonusTile) {

            sendOut("Get Bonus Tile");

            GetBonusTile query = (GetBonusTile) q;

            Map<Integer, String> bonusTiles = query.perform(model);

            sendOut(bonusTiles);
        }

        if (q instanceof GetPayToObtainCards) {

            sendOut("Get Pay To Obtain Cards");


            GetPayToObtainCards query = (GetPayToObtainCards) q;

            Map<String, HashMap<Integer, String>> payToObtainCards = query.perform(model);

            sendOut(payToObtainCards);

        }

        if (q instanceof GetExcommunication) {

            sendOut("Get Excommunication");


            GetExcommunication query = (GetExcommunication) q;

            String excommunicationUrl = query.perform(model);

            sendOut(excommunicationUrl);

        }




    }

    private void sendOut(Object o) {

        try {

            this.socketOut.reset();
            this.socketOut.writeObject(o);
            this.socketOut.flush();

        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        }
    }

    private void handleDisconnection(){
        model.getPlayer(playerColor).unregisterObserver(this);
        model.unregisterObserver(this);
        logoutInterface.clientDisconnected(model.getPlayer(playerColor).getPlayerID());

        notifyObserver(new Disconnection(playerColor));

    }

}
