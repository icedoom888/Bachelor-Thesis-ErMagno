package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.Market;
import it.polimi.ingsw.GC_29.Controllers.Input;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
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
                inputLine = inputParser(inputLine, rmiView);

                // Call the appropriate method in the server
                switch (inputLine) {
                    case "skip action":
                        serverStub.skipAction();
                        break;
                    case "use family pawn":
                        serverStub.usePawnChosen(rmiView.getFamilyPawnChosen());
                        rmiView.setValidActionList(serverStub.getValidActionList());
                        System.out.println(rmiView.getValidActionList());
                        break;
                    case "get valid action":
                        rmiView.setValidActionList(serverStub.getValidActionList());
                        System.out.println(rmiView.getValidActionList());
                        break;
                    case "execute action":
                        serverStub.doAction(rmiView.getActionIndex());
                        break;

                    default:
                        break;
                }

            } catch (IOException e1) {

                e1.printStackTrace();
            }
            break; // TODO: gestione client disconnesso!
        }
    }

    private static String inputParser(String inputLine, ClientRMIView rmiView) {

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

    }
}
