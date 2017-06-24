package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Client.ClientSocket.CommonOutSocket;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public class ChooseDistribution {

    private Distribution distribution;
    private CommonOutSocket commonOutSocket;

    public ChooseDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public void sendInput(String input) {
        switch (distribution) {

            case SOCKET:
                commonOutSocket.sendInput(input);
                break;

            case RMI:
                break;
        }
    }


    public void setCommonOutSocket(CommonOutSocket commonOutSocket) {
        this.commonOutSocket = commonOutSocket;
    }
}
