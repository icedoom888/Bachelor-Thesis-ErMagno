package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;

/**
 * Created by Lorenzotara on 14/06/17.
 */
public class ClientInHandler implements Runnable {

    private ObjectInputStream socketIn;
    private ClientOutHandler clientOutHandler;

    public ClientInHandler(ObjectInputStream socketIn) {
        this.socketIn = socketIn;
    }

    @Override
    public void run() {

        while(true){

            // handles input messages coming from the server, just showing them to the user
            try {
                Object object = socketIn.readObject();

                if(object instanceof Set<?>){

                    System.out.println("These are your valid actions: ");
                    System.out.println(object);
                    Set<Action> validActions = (Set<Action>)object;
                    for (Action validAction : validActions) {
                        System.out.println(validAction);
                    }
                }

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



    public ObjectInputStream getSocketIn() {
        return socketIn;
    }

    public void setClientOutHandler(ClientOutHandler clientOutHandler) {
        this.clientOutHandler = clientOutHandler;
    }
}
