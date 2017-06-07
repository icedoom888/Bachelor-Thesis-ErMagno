package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.Player;

import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public class DoAction extends Input {

    int actionIndex;

    Player currentPlayer;

    public DoAction(int actionIndex){

        this.actionIndex = actionIndex;
    }

    @Override
    public void esegui(GameStatus model) throws RemoteException {

        currentPlayer = model.getCurrentPlayer();

        //TODO: qui o lato client controllare che l'input sia valido (actionIndex)

        Action actionSelected = currentPlayer.getCurrentValidActionsList().get(actionIndex);

        actionSelected.execute();

        //TODO: ripetizione di codice --> REFACTOR

        if(!currentPlayer.getCurrentBonusActionList().isEmpty()){

            model.getCurrentPlayer().setPlayerState(PlayerState.BONUSACTION);

        }

        else {

            // TODO: qui ci va la logica (chiamando opportuni metodi di questa classe) del GameController sulla gestione di fine giro

            currentPlayer.setPlayerState(PlayerState.TURNTERMINATED);

            int index = (model.getTurnOrder().indexOf(currentPlayer) + 1) % model.getTurnOrder().size() ;

            // controlli sull'index e eventuali chiamate

            currentPlayer = model.getTurnOrder().get(index);

            currentPlayer.setPlayerState(PlayerState.DOACTION);
        }

    }

}
