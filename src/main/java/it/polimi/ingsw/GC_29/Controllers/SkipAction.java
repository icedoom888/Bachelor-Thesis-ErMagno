package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Model.Player;

import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public class SkipAction extends Input {

    @Override
    public void perform(Model model, Controller controller) throws RemoteException {

        Player currentPlayer = model.getCurrentPlayer();

        if(!currentPlayer.getCurrentBonusActionList().isEmpty()){

            model.getCurrentPlayer().setPlayerState(PlayerState.BONUSACTION);

        }

        else {

            currentPlayer.setPlayerState(PlayerState.ENDTURN);

        }
    }
}
