package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Client.ClientRMI.GameRMI;
import it.polimi.ingsw.GC_29.Client.ClientSocket.CommonOutSocket;

import java.util.List;
import java.util.Map;

/**
 * Created by Lorenzotara on 24/06/17.

public class ChooseDistribution {

    private Distribution distribution;
    private CommonOutSocket commonOutSocket;
    private GameRMI gameRMI;


    public ChooseDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public void sendInput(String input) {
        switch (distribution) {

            case SOCKET:
                commonOutSocket.sendInput(input);
                break;

            case RMI:
                gameRMI.sendInput(input);
                break;
        }
    }


    public void setCommonOutSocket(CommonOutSocket commonOutSocket) {
        this.commonOutSocket = commonOutSocket;
    }

    public void setGameRMI(GameRMI gameRMI) {
        this.gameRMI = gameRMI;
    }

    public void sendInput(Map<String, Integer> activatedCardMap) {
        switch (distribution){

            case SOCKET:
                commonOutSocket.setActivatedCardMap(activatedCardMap);
                break;

            case RMI:
                gameRMI.setActivatedCardMap(activatedCardMap);
                break;

        }

        sendInput("activated cards GUI");

    }

    public void sendInput(List<Integer> councilPrivilegeEffectChosenList) {

        switch (distribution){

            case SOCKET:
                commonOutSocket.setCouncilPrivilegeEffectChosenList(councilPrivilegeEffectChosenList);
                break;

            case RMI:
                gameRMI.set
        }

        sendInput("council privileges chosen GUI");
    }

    public Distribution getDistribution() {
        return distribution;
    }
}
 */