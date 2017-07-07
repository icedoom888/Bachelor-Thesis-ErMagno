package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Model.PlayerColor;
import it.polimi.ingsw.GC_29.Server.SuspendPlayer;

/**
 * Created by Christian on 03/07/2017.
 */
public class Disconnection extends Input {

    private PlayerColor playerColor;

    public Disconnection(PlayerColor playerColor){

        this.playerColor = playerColor;
    }

    @Override
    public void perform(Model model, Controller controller) {

        SuspendPlayer suspendPlayer = new SuspendPlayer(controller, model, model.getPlayer(playerColor));

        suspendPlayer.run();

        controller.getPlayerDisconnected().add(model.getPlayer(playerColor));


    }
}
