package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.ClientViewRemote;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Client.Instruction;
import it.polimi.ingsw.GC_29.Client.InstructionSet;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMIViewRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Christian on 07/06/2017.
 */
public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote, Serializable {


    private ArrayList<Action> validActionList;

    private PlayerColor playerColor;

    PlayerState currentPlayerState;

    GameState currentGameState;

    private transient InputChecker inputChecker;

    private transient RMIViewRemote serverViewStub;


    protected ClientRMIView(PlayerColor playerColor, RMIViewRemote serverViewStub) throws RemoteException {
        super();

        this.serverViewStub = serverViewStub;

        inputChecker = new InputChecker();

        this.playerColor = playerColor;

    }

    public ArrayList<Action> getValidActionList() {
        return validActionList;
    }

    public void setValidActionList(ArrayList<Action> validActionList) {
        this.validActionList = validActionList;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public InputChecker getInputChecker() {
        return inputChecker;
    }


    @Override
    public void updateClient(Change c) throws RemoteException {
        // Just prints what was received from the server
        System.out.println(c);
        System.out.println(playerColor);

        if(c instanceof PlayerStateChange){

            currentPlayerState = ((PlayerStateChange)c).getNewPlayerState();

            handlePlayerState(currentPlayerState);

            System.out.println("if you want to see your valid input for this current state insert : help");
        }

        if(c instanceof GameChange){
            currentGameState = ((GameChange)c).getNewGameState();
            //TODO: if relation with the church chiedo se questo player è stato scomunicato passando dallo stub e poi printo quello che devo
        }
    }

    private void handlePlayerState(PlayerState currentPlayerState) throws RemoteException {

        inputChecker.setCurrentPlayerState(currentPlayerState);

        switch (currentPlayerState){

            case DOACTION:
                inputChecker.setFamilyPawnAvailability(serverViewStub.getFamilyPawnAvailability());
                break;


            //TODO: inserire gestione altri stati se necessario
        }
    }


    public void handleHelp(){

        List<Instruction> instructionList = inputChecker.getInstructionSet().getInstructions(currentPlayerState);

        System.out.println("your valid input in this current state are:");

        for (Instruction instruction : instructionList) {

            System.out.println("");
            System.out.println(instruction.getInstruction());

        }
    }

    
    public void printValidActionList() {

        for (Action action : validActionList) {
            if(action.getValid()){
                System.out.println("action index:" + validActionList.indexOf(action) + ") " + action);
            }
        }
    }

}
