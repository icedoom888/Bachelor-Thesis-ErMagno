package it.polimi.ingsw.GC_29.Server.Socket;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.LeaderCard;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Query.*;
import it.polimi.ingsw.GC_29.Server.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class ServerSocketView extends View implements Runnable {

    private Socket socket;
    private GetValidActions validActionQuery;
    private ObjectInputStream socketIn;
    private ObjectOutputStream socketOut;
    private GameStatus model;
    private PlayerColor playerColor;
    private final String username;
    private static final Object socketLock = new Object();


    public ServerSocketView(PlayerSocket playerSocket, GameStatus model, PlayerColor playerColor, String playerID) throws IOException {
        this.socket = playerSocket.getSocket();
        this.socketIn = playerSocket.getSocketIn();
        this.socketOut = playerSocket.getSocketOut();
        this.model = model;
        this.validActionQuery = new GetValidActions();
        this.playerColor = playerColor;
        this.username = playerID;

    }

    @Override
    public void update(Change o) throws IOException {

        System.out.println("UPDATE from Server");
        System.out.println(o);

        /*socketOut.writeObject("Change");

        socketOut.flush();
        socketOut.reset();
        socketOut.writeObject(o);
        socketOut.flush();*/

        try {

            socketOut.writeObject("Change");
            socketOut.flush();
            socketOut.reset();
            socketOut.writeObject(o);
            socketOut.flush();

        } catch (Exception e){

            System.out.println("ECCEZIONE CATCHATA IN UPDATE");
            handleDisconnection();
        }

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
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

                        System.out.println("la stringa ricevuta è" + string);

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

                            /*case "council privileges chosen leader":
                                councilPrivileges = (List<Integer>)socketIn.readObject();
                                PlayerColor playerColor = (PlayerColor)socketIn.readObject();
                                notifyObserver(new PrivilegeChosenLeader(councilPrivileges, playerColor));
                                break;*/

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
                                System.out.println("DISCARDING LEADER FROM SERVER\n\n");
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

                                try {
                                    notifyObserver(new Closed());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return;


                        }
                    }

                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e){
                    System.out.println("ECCEZIONE CATCHATA IN RUN");
                    handleDisconnection();
                    b = false;
                    try {
                        this.socket.close();
                        this.socketIn.close();
                        this.socketOut.close();
                        System.out.println("SOCKET CHIUSI");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("SONO USCITO DA HANDLE DISCONNECTION");
                }
                /*catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }

    }

    private void handleQuery(Query q) {

        System.out.println("VIEW: received the query " + q);

        /*if (q instanceof GetFamilyPawns) {

            try {
                socketOut.writeObject("Family Pawn Availability");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GetFamilyPawnAvailability query = (GetFamilyPawnAvailability) q;

            Map<FamilyPawnType, Boolean> familyPawnAvailability = query.perform(model);
            try {
                this.socketOut.writeObject(familyPawnAvailability);
                this.socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

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
/*
            try {
                socketOut.writeObject("Valid Actions");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/

            sendOut("Valid Actions");

            GetValidActions query = (GetValidActions) q;

            Map<Integer, String> validActions = query.perform(model);

            sendOut(validActions);
        }

        if (q instanceof GetCardsForWorkers) {
/*
            try {
                socketOut.writeObject("Get Cards For Workers");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/

            sendOut("Get Cards For Workers");


            GetCardsForWorkers query = (GetCardsForWorkers) q;

            Map<Integer, ArrayList<String>> cardsForWorkers = query.perform(model);

            sendOut(cardsForWorkers);
        }

        if (q instanceof GetPossibleCosts) {
/*
            try {
                socketOut.writeObject("Get Possible Costs");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/

            sendOut("Get Possible Costs");

            GetPossibleCosts query = (GetPossibleCosts) q;

            Map<Integer, String> possibleCosts = query.perform(model);

            sendOut(possibleCosts);
        }

        if (q instanceof GetCouncilPrivileges) {
/*
            try {
                socketOut.writeObject("Get Council Privileges");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/

            sendOut("Get Council Privileges");

            GetCouncilPrivileges query = (GetCouncilPrivileges) q;

            List<Integer> councilPrivileges = query.perform(model);

            sendOut(councilPrivileges);
        }

        if (q instanceof GetGoodSet) {
/*
            try {
                socketOut.writeObject("Get GoodSet");
                socketOut.flush();
                this.socketOut.reset();

            } catch (IOException e) {
                e.printStackTrace();
            }
*/

            sendOut("Get GoodSet");

            GetGoodSet query = (GetGoodSet) q;

            GoodSet goodSet = query.perform(model);

            sendOut(goodSet);
        }

        if (q instanceof GetDevelopmentCard) {
/*
            try {
                socketOut.writeObject("Get Development Cards");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/

            sendOut("Get Development Cards");

            GetDevelopmentCard query = (GetDevelopmentCard) q;

            List<String> developmentCards = query.perform(model);

            sendOut(developmentCards);
        }

        if (q instanceof GetTowerCard) {
/*
            try {
                socketOut.writeObject("Get Tower Cards");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/

            sendOut("Get Tower Cards");


            GetTowerCard query = (GetTowerCard) q;

            List<String> towerCards = query.perform(model);

            sendOut(towerCards);
        }

        if (q instanceof GetFamilyPawnAvailability) {
/*
            try {
                socketOut.writeObject("Get Family Pawns Availability");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/

            sendOut("Get Family Pawns Availability");


            GetFamilyPawnAvailability query = (GetFamilyPawnAvailability) q;

            Map<FamilyPawn, Boolean> familyPawns = query.perform(model);

            sendOut(familyPawns);
        }

        if (q instanceof GetBonusTile) {

            /*

            try {
                socketOut.writeObject("Get Bonus Tile");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/

            sendOut("Get Bonus Tile");

            GetBonusTile query = (GetBonusTile) q;

            Map<Integer, String> bonusTiles = query.perform(model);

            sendOut(bonusTiles);
        }

        if (q instanceof GetPayToObtainCards) {

            /*
            try {
                socketOut.writeObject("Get Pay To Obtain Cards");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/
            sendOut("Get Pay To Obtain Cards");


            GetPayToObtainCards query = (GetPayToObtainCards) q;

            Map<String, HashMap<Integer, String>> payToObtainCards = query.perform(model);

            sendOut(payToObtainCards);

        }

        if (q instanceof GetExcommunication) {

            /*
            try {
                socketOut.writeObject("Get Excommunication");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */

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
            System.out.println("ECCEZIONE CATCHATA IN SENDOUT");
            e.printStackTrace();
        }
    }

    private void handleDisconnection(){
        model.getPlayer(playerColor).unregisterObserver(this);
        model.unregisterObserver(this);
        logoutInterface.clientDisconnected(model.getPlayer(playerColor).getPlayerID());
        System.out.println("CLIENT DISCONNECTED. CLIENT SUSPENDED");
        //gameStatus.getPlayer(playerColor).setPlayerState(PlayerState.SUSPENDED);
        try {
            notifyObserver(new Disconnection(playerColor));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
