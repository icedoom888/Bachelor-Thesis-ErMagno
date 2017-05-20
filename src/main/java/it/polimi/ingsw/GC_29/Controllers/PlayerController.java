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
    private ActionBuilder actionBuilder;

    public void init(){
        // chiamo askForAction e ciò che mi arriva lo sparo a setActionBuilder
    }

    private ActionType askForAction(){
        return null;
    }

    private void setActionBuilder(ActionType type) {

        PlayerStatus playerStatus = gameStatus.getCurrentPlayer().getStatus();
        HashMap<CardColor,Tower> towerHashMap = gameStatus.getGameBoard().getTowerMap();

        switch (type){
            case GREENTOWER:
                actionBuilder = new TowerActionBuilder(ActionType.GREENTOWER, towerHashMap.get(CardColor.GREEN), playerStatus);
                break;
            case YELLOWTOWER:
                actionBuilder = new TowerActionBuilder(ActionType.YELLOWTOWER, towerHashMap.get(CardColor.YELLOW), playerStatus);
                break;
            case BLUETOWER:
                actionBuilder = new TowerActionBuilder(ActionType.BLUETOWER, towerHashMap.get(CardColor.BLUE), playerStatus);
                break;
            case PURPLETOWER:
                actionBuilder = new TowerActionBuilder(ActionType.PURPLETOWER, towerHashMap.get(CardColor.PURPLE), playerStatus);
                break;
            case MARKET:
                actionBuilder = new MarketActionBuilder();
                break;
            case COUNCILPALACE:
                actionBuilder = new CouncilPalaceActionBuilder();
                break;
            case HARVEST:
                actionBuilder = new WorkActionBuilder(ActionType.HARVEST);
                break;
            case PRODUCTION:
                actionBuilder = new WorkActionBuilder(ActionType.PRODUCTION);
                break;
            case SKIPTURN:
                /* TODO: qui vado avanti con la chiusura del turno del player */
                break;
        }
    }
}
