package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.Instruction;
import it.polimi.ingsw.GC_29.Client.InstructionSet;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Query.GetValidActions;
import it.polimi.ingsw.GC_29.Server.Query.Query;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class ClientOutHandler implements Runnable {
    private final PrintWriter outVideo;
    private final BufferedReader inKeyboard;

    private ClientInHandler clientInHandler;

    private Boolean logged = false;

    //TODO: settare playerColor

    private ObjectOutputStream socketOut;

    private FamilyPawnType familyPawnChosen;
    private int actionIndex;
    private ArrayList<Action> validActionList;
    private PlayerState currentPlayerState;
    private GameState currentGameState;
    private PlayerColor playerColor;
    private InstructionSet instructionSet;

    public ClientOutHandler(ObjectOutputStream socketOut) {
        this.socketOut = socketOut;
        this.instructionSet = new InstructionSet();
        this.inKeyboard = new BufferedReader(new InputStreamReader(System.in));
        this.outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
    }

    @Override
    public void run() {

        // Handles output messages, from the client to the server
        System.out.println("CLIENT OUT HANDLER RUNNING");

        Boolean b = true;
        while (b) {

            String inputLine = null;
            try {
                inputLine = inKeyboard.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Input input;
            Query query;
            try {
                // Implements the communication protocol, creating the Actions corresponding to the input of the user

                switch (inputLine) {

                    //inputLine = inputChecker(inputLine, rmiView, serverViewStub);

                    case "skip action":
                        input = new SkipAction();
                        socketOut.writeObject(input);
                        socketOut.flush();
                        break;
                    case "end turn":
                        input = new EndTurn();
                        socketOut.writeObject(input);
                        socketOut.flush();
                        break;
                    case "use family pawn":
                        input = new UsePawnChosen(familyPawnChosen);
                        socketOut.writeObject(input);
                        socketOut.flush();
                        break;
                    case "see valid action list":
                        query = new GetValidActions();
                        socketOut.writeObject(query);
                        socketOut.flush();
                        break;
                    case "execute action":
                        input = new ExecuteAction(actionIndex);
                        socketOut.writeObject(input);
                        socketOut.flush();
                        break;
                    case "I want to pray":
                        input = new Pray(true, playerColor);
                        socketOut.writeObject(input);
                        socketOut.flush();
                        break;
                    case "I don't want to pray":
                        input = new Pray(false, playerColor);
                        socketOut.writeObject(input);
                        socketOut.flush();
                        break;
                    case "help":
                        handleHelp();
                        break;
                    default:
                        handleHelp();
                        break;

                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

    }






    public void setClientInHandler(ClientInHandler clientInHandler) {
        this.clientInHandler = clientInHandler;
    }

    public FamilyPawnType getFamilyPawnChosen() {
        return familyPawnChosen;
    }

    public void setFamilyPawnChosen(FamilyPawnType familyPawnChosen) {
        this.familyPawnChosen = familyPawnChosen;
    }

    public int getActionIndex() {
        return actionIndex;
    }

    public void setActionIndex(int actionIndex) {
        this.actionIndex = actionIndex;
    }

    public ArrayList<Action> getValidActionList() {
        return validActionList;
    }

    public void setValidActionList(ArrayList<Action> validActionList) {
        this.validActionList = validActionList;
    }

    public PlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public void setCurrentPlayerState(PlayerState currentPlayerState) {
        this.currentPlayerState = currentPlayerState;
    }

    public GameState getGameEvent() {
        return currentGameState;
    }

    public void setGameEvent(GameState gameEvent) {
        this.currentGameState = gameEvent;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public void handleHelp(){

        List<Instruction> instructionList = instructionSet.getInstructions(currentPlayerState);

        System.out.println("your valid input in this current state are:");

        for (Instruction instruction : instructionList) {

            System.out.println("");
            System.out.println(instruction.getInstruction());

        }
    }

    public InstructionSet getInstructionSet() {
        return instructionSet;
    }

    public ObjectOutputStream getSocketOut() {
        return socketOut;
    }


}
