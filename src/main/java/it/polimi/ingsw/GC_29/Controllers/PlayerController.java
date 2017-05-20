package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionType;
import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.Tower;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.HashMap;

/**
 * Created by Christian on 19/05/2017.
 */
public class PlayerController {

    private GameStatus gameStatus;
    private PlayerStatus playerStatus;
    private ActionBuilder actionBuilder;

    public PlayerController() {
        this.gameStatus = GameStatus.getInstance();
        this.playerStatus = gameStatus.getCurrentPlayer().getStatus();

    }

    public void init(){
        // chiamo askForAction e ciò che mi arriva lo sparo a setActionBuilder
    }

    private ActionType askForAction(){

        return null;
    }

    private void setActionBuilder(ActionType type) {

        if(type == ActionType.SKIPTURN){
            // TODO: gestione fine turno
        }
        else {
            actionBuilder = FactoryActionBuilder.getActionBuilder(type, false, playerStatus);
        }
        // TODO: continuazione processo di gestione turno
    }
}
