package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Player.Player;

import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public class SkipAction extends Input {

    @Override
    public void esegui(GameStatus model, Controller controller) throws Exception {

        Player currentPlayer = model.getCurrentPlayer();

        if(!currentPlayer.getCurrentBonusActionList().isEmpty()){

            model.getCurrentPlayer().setPlayerState(PlayerState.BONUSACTION);

        }

        else {

            // TODO: qui ci va la logica (chiamando opportuni metodi di questa classe) del GameController sulla gestione di fine giro

            currentPlayer.setPlayerState(PlayerState.ENDTURN);

            int index = (model.getTurnOrder().indexOf(currentPlayer) + 1) % model.getTurnOrder().size() ;

            // controlli sull'index e eventuali chiamate

            currentPlayer = model.getTurnOrder().get(index);

            currentPlayer.setPlayerState(PlayerState.DOACTION);

        }
    }
}
