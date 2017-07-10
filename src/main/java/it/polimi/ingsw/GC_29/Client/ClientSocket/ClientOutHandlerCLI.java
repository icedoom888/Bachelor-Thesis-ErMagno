package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.InputChecker;

import java.io.*;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Lorenzotara on 14/06/17.
 *
 * ClienOutHandlerCLI waits for the client to write an input
 * from the keyboard. Then it sends it to the commonOutSocket.
 */
public class ClientOutHandlerCLI implements Runnable {

    private final BufferedReader inKeyboard;

    private ClientInHandlerCLI clientInHandlerCLI;
    private CommonOutSocket commonOutSocket;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());




    public ClientOutHandlerCLI(ObjectOutputStream socketOut) {
        this.commonOutSocket = new CommonOutSocket(socketOut);
        this.inKeyboard = new BufferedReader(new InputStreamReader(System.in));

    }



    @Override
    public void run() {

        // Handles output messages, from the client to the server
        System.out.println("CLIENT OUT HANDLER CLI RUNNING");

        Boolean b = true;
        while (b) {

            String inputLine = null;
            try {
                inputLine = inKeyboard.readLine();
            } catch (IOException e) {
                LOGGER.log(Level.INFO, e.getMessage(), e);
            }

            commonOutSocket.sendInput(inputLine);

        }

    }

    public CommonOutSocket getCommonOutSocket() {
        return commonOutSocket;
    }

    public void setInputChecker(InputChecker inputChecker) {
        commonOutSocket.setInputChecker(inputChecker);
    }

}
