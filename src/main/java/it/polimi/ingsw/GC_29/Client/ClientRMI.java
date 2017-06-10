package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.Market;
import it.polimi.ingsw.GC_29.Controllers.Input;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Server.Query;
import it.polimi.ingsw.GC_29.Server.RMIView;
import it.polimi.ingsw.GC_29.Server.RMIViewRemote;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Christian on 07/06/2017.
 */
public class ClientRMI {

    private final static int RMI_PORT = 52365;


    private final static String HOST = "127.0.0.1";

    private final static int PORT = 52365;

    private static final String NAME = "lorenzo";

    private static final String error = "Input not allowed for your current state";

    private static final ArrayList<String> parseredAnswerList = new ArrayList<>();



    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {

        //Get the remote registry
        Registry registry = LocateRegistry.getRegistry(HOST, PORT);

        //get the stub (local object) of the remote view
        RMIViewRemote serverStub = (RMIViewRemote) registry.lookup(NAME);

        ClientRMIView rmiView=new ClientRMIView();

        // register the client view in the server side (to receive messages from the server)
        serverStub.registerClient(rmiView);


        Scanner stdIn = new Scanner(System.in);

        while (true) {
            //Capture input from user
            String inputLine = stdIn.nextLine();
            System.out.println("SENDING "+inputLine);
            Input input;
            Query query;

            try {


                //vedi il commento nel metodo inputParser
                inputLine = inputChecker(inputLine, rmiView, serverStub);

                // Call the appropriate method in the server
                switch (inputLine) {
                    case "skip action":
                        serverStub.skipAction();
                        break;
                    case "end turn":
                        serverStub.endTurn();
                        break;
                    case "use family pawn":
                        serverStub.usePawnChosen(rmiView.getFamilyPawnChosen());
                        rmiView.setValidActionList(serverStub.getValidActionList());
                        System.out.println(rmiView.getValidActionList());
                        break;
                    case "see valid action list":
                        rmiView.setValidActionList(serverStub.getValidActionList());
                        rmiView.printValidActionList();
                        break;
                    case "execute action":
                        serverStub.doAction(rmiView.getActionIndex());
                        break;
                    case "I want to pray":
                        serverStub.pray(true, rmiView.getPlayerColor());
                        break;
                    case "I don't want to pray":
                        serverStub.pray(false, rmiView.getPlayerColor());
                        break;

                    case "help":
                        rmiView.handleHelp();
                        break;

                    default:
                        rmiView.handleHelp();
                        break;
                }

            } catch (IOException e1) {

                e1.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            break; // TODO: gestione client disconnesso!
        }
    }





    private static String inputChecker(String inputLine, ClientRMIView rmiView, RMIViewRemote serverStub){

        String checkedString = inputLine;

        PlayerState currentPlayerState = rmiView.getCurrentPlayerState();

        List<Instruction> instructionList = rmiView.getInstructionSet().getInstructions(currentPlayerState);

        for(Instruction instruction : instructionList){

            if(instruction.isRegex()){

                Pattern pattern = Pattern.compile(instruction.getRegex());
                Matcher matcher = pattern.matcher(inputLine);

                if(matcher.find()){

                    checkedString = handleRegex(checkedString, instruction, rmiView, serverStub);

                    return checkedString;
                }
            }

            else if(instruction.getInstruction().equals(checkedString)){

                return checkedString;

            }
        }

        checkedString = "invalid input";

        return checkedString;
    }



    private static String handleRegex(String inputLine, Instruction instruction, ClientRMIView rmiView, RMIViewRemote serverStub) {



        String[] parts = inputLine.split(" ");
        String lastWord = parts[parts.length - 1];

        switch (instruction.getInstruction()){

            case ("use family pawn (insert type)"):

                return handleUseFamilyPawn(lastWord, rmiView, serverStub);

            case ("activate leader card (insert index)"):

                return handleLeaderCard(lastWord, true, rmiView);

            case ("discard leader card (insert index)"):

                return handleLeaderCard(lastWord, false, rmiView);

            case ("execute action (insert index)"):

                return handleExecuteAction(lastWord, rmiView);

        }

        Integer.parseInt(lastWord);

        return null;
    }

    private static String handleExecuteAction(String lastWord, ClientRMIView rmiView){

        int index = Integer.parseInt(lastWord);

        ArrayList<Action> validActionList = rmiView.getValidActionList();

        if( index < validActionList.size() && validActionList.get(index).getValid()){

            rmiView.setActionIndex(index);

            return "execute action";
        }

        else {

            return "invalid input";
        }

    }

    private static String handleLeaderCard(String lastWord, boolean b, ClientRMIView rmiView) {

        // TODO: implementa quando fai Leader Card

        return "";
    }

    private static String handleUseFamilyPawn(String lastWord,ClientRMIView rmiView, RMIViewRemote serverStub) {

        lastWord = lastWord.toUpperCase();

        Map<FamilyPawnType, Boolean> pawnAvailability = serverStub.getFamilyPawnAvailability();

        for(FamilyPawnType familyPawnType : FamilyPawnType.values()){

            if(FamilyPawnType.valueOf(lastWord) == familyPawnType && pawnAvailability.get(familyPawnType)){

                rmiView.setFamilyPawnChosen(familyPawnType);

                return "use family pawn";
            }
        }

        return "invalid input";
    }


    /*private static String inputParser(String inputLine, ClientRMIView rmiView) {

        //TODO: controlli sull'input grazie a regular expressions e al playerState e GameChange


        parseredAnswerList.add(skipActionParser(inputLine, rmiView));
        parseredAnswerList.add(pawnParser(inputLine, rmiView));

        return null;

    }

    private static String skipActionParser(String inputLine, ClientRMIView rmiView) {

        String newInput = inputLine;

        Pattern patternSkipAction = Pattern.compile("\bskip\b && \baction\b");

        Matcher matcherSkipAction = patternSkipAction.matcher(inputLine);

        if(matcherSkipAction.find()){
            if(rmiView.getCurrentPlayerState() != PlayerState.DOACTION){
                newInput = error;
            }
            else{
                newInput = "skip action";
            }
        }
        return newInput;
    }

    private static String pawnParser(String inputLine, ClientRMIView rmiView) {

        String newInput = inputLine;

        Pattern patternChooseBlackPawn = Pattern.compile("\bred\b && \bpawn\b");
        Pattern patternChooseWhitePawn = Pattern.compile("\bblue\b && \bpawn\b");
        Pattern patternChooseOrangePawn = Pattern.compile("\bgreen\b && \bpawn\b");
        Pattern patternChooseNeutralPawn = Pattern.compile("\bneutral\b && \bpawn\b");

        Matcher matcherChooseBlackPawn = patternChooseBlackPawn.matcher(inputLine);
        Matcher matcherChooseWhitePawn = patternChooseWhitePawn.matcher(inputLine);
        Matcher matcherChooseOrangePawn = patternChooseOrangePawn.matcher(inputLine);
        Matcher matcherChooseNeutralPawn = patternChooseNeutralPawn.matcher(inputLine);

        if (matcherChooseBlackPawn.find()) {
            if (rmiView.getCurrentPlayerState() != PlayerState.DOACTION || rmiView.getCurrentPlayerState() != PlayerState.CHOOSEACTION) {
                newInput = error;

            }
            else {
                rmiView.setFamilyPawnChosen(FamilyPawnType.BLACK);
                newInput = "use family pawn";
            }
        }

        if (matcherChooseWhitePawn.find()) {
            if (rmiView.getCurrentPlayerState() != PlayerState.DOACTION || rmiView.getCurrentPlayerState() != PlayerState.CHOOSEACTION) {
                newInput = error;

            }
            else {
                rmiView.setFamilyPawnChosen(FamilyPawnType.WHITE);
                newInput = "use family pawn";
            }
        }

        if (matcherChooseOrangePawn.find()) {
            if (rmiView.getCurrentPlayerState() != PlayerState.DOACTION || rmiView.getCurrentPlayerState() != PlayerState.CHOOSEACTION) {
                newInput = error;

            }
            else {
                rmiView.setFamilyPawnChosen(FamilyPawnType.ORANGE);
                newInput = "use family pawn";
            }
        }

        if (matcherChooseNeutralPawn.find()) {
            if (rmiView.getCurrentPlayerState() != PlayerState.DOACTION || rmiView.getCurrentPlayerState() != PlayerState.CHOOSEACTION) {
                newInput = error;

            }
            else {
                rmiView.setFamilyPawnChosen(FamilyPawnType.NEUTRAL);
                newInput = "use family pawn";
            }
        }


        return newInput;

    }*/
}
