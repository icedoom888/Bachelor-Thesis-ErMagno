package it.polimi.ingsw.GC_29.Server.Socket;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Query.*;
import it.polimi.ingsw.GC_29.Server.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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
    private static final Object socketLock = new Object();


    public ServerSocketView(PlayerSocket playerSocket, GameStatus model) throws IOException {
        this.socket = playerSocket.getSocket();
        this.socketIn = playerSocket.getSocketIn();
        this.socketOut = playerSocket.getSocketOut();
        this.model = model;
        this.validActionQuery = new GetValidActions();

    }

    @Override
    public void update(Change o) throws Exception {

        System.out.println("UPDATE from Server");
        socketOut.writeObject("Change");
        socketOut.flush();
        socketOut.writeObject(o);
        socketOut.flush();

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
                                PlayerColor playerColor = (PlayerColor)socketIn.readObject();
                                notifyObserver(new Pray(true, playerColor));
                                break;

                            case "do not pray":
                                playerColor = (PlayerColor)socketIn.readObject();
                                notifyObserver(new Pray(false, playerColor));
                                break;


                        }
                    }

                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        if (q instanceof GetValidActions) {

            try {
                socketOut.writeObject("Valid Actions");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GetValidActions query = (GetValidActions) q;

            Map<Integer, String> validActions = query.perform(model);
            try {
                this.socketOut.writeObject(validActions);
                this.socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (q instanceof GetCardsForWorkers) {

            try {
                socketOut.writeObject("Get Cards For Workers");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GetCardsForWorkers query = (GetCardsForWorkers) q;

            Map<Integer, ArrayList<String>> cardsForWorkers = query.perform(model);
            try {
                this.socketOut.writeObject(cardsForWorkers);
                this.socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (q instanceof GetPossibleCosts) {

            try {
                socketOut.writeObject("Get Possible Costs");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GetPossibleCosts query = (GetPossibleCosts) q;

            Map<Integer, String> cardsForWorkers = query.perform(model);
            try {
                this.socketOut.writeObject(cardsForWorkers);
                this.socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (q instanceof GetCouncilPrivileges) {

            try {
                socketOut.writeObject("Get Council Privileges");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GetCouncilPrivileges query = (GetCouncilPrivileges) q;

            List<Integer> councilPrivileges = query.perform(model);
            try {
                this.socketOut.writeObject(councilPrivileges);
                this.socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (q instanceof GetGoodSet) {

            try {
                socketOut.writeObject("Get GoodSet");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GetGoodSet query = (GetGoodSet) q;

            GoodSet goodSet = query.perform(model);
            try {
                this.socketOut.writeObject(goodSet);
                this.socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (q instanceof GetDevelopmentCard) {

            try {
                socketOut.writeObject("Get Development Cards");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GetDevelopmentCard query = (GetDevelopmentCard) q;

            List<String> developmentCards = query.perform(model);
            try {
                this.socketOut.writeObject(developmentCards);
                this.socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (q instanceof GetTowerCard) {

            try {
                socketOut.writeObject("Get Tower Cards");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GetTowerCard query = (GetTowerCard) q;

            List<String> towerCards = query.perform(model);
            try {
                this.socketOut.writeObject(towerCards);
                this.socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (q instanceof GetFamilyPawnAvailability) {

            try {
                socketOut.writeObject("Get Family Pawns Availability");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GetFamilyPawnAvailability query = (GetFamilyPawnAvailability) q;

            Map<FamilyPawn, Boolean> familyPawns = query.perform(model);
            try {
                this.socketOut.writeObject(familyPawns);
                this.socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (q instanceof GetBonusTile) {

            try {
                socketOut.writeObject("Get Bonus Tile");
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GetBonusTile query = (GetBonusTile) q;

            Map<Integer, String> bonusTiles = query.perform(model);
            try {
                this.socketOut.writeObject(bonusTiles);
                this.socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }


}
