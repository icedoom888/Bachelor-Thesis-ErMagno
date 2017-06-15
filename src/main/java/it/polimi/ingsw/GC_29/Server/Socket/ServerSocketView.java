package it.polimi.ingsw.GC_29.Server.Socket;

import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Query.GetFamilyPawnAvailability;
import it.polimi.ingsw.GC_29.Server.Query.GetValidActions;
import it.polimi.ingsw.GC_29.Server.Query.Query;
import it.polimi.ingsw.GC_29.Server.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class ServerSocketView extends View implements Runnable {

    private Socket socket;
    private GetValidActions validActionQuery;
    private ObjectInputStream socketIn;
    private ObjectOutputStream socketOut;
    private GameStatus model;


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

                        case "throw dices":
                            notifyObserver(new ThrowDices());
                            break;

                        case "family pawn chosen":
                            FamilyPawnType familyPawnType = (FamilyPawnType) socketIn.readObject();
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

                        case "i want to pray":
                            PlayerColor playerColor = (PlayerColor)socketIn.readObject();
                            notifyObserver(new Pray(true, playerColor));
                            break;

                        case "i don't want to pray":
                            playerColor = (PlayerColor)socketIn.readObject();
                            notifyObserver(new Pray(false, playerColor));
                            break;


                    }
                }
                /*if (object instanceof GetPrigionieri) {
                    // In this particular case handles the action by sending back data to the client,
                    //  since it is not modifying the model, just querying it.
                    GetPrigionieri query = (GetPrigionieri) object;
                    System.out.println("VIEW: received the query " + query);
                    Set<Prigioniero> prigionieri=query.perform(model);
                    System.out.println(prigionieri);
                    this.socketOut.writeObject(prigionieri);
                    this.socketOut.flush();

                }*/

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

        if (q instanceof GetFamilyPawnAvailability) {

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
        }

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
    }


}
