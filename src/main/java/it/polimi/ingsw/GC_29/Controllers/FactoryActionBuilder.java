package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.Tower;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionType;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.HashMap;

/**
 * Created by Christian on 20/05/2017.
 */
public class FactoryActionBuilder {

    static  HashMap<CardColor,Tower> towerHashMap = GameStatus.getInstance().getGameBoard().getTowerMap();

    public static ActionBuilder getActionBuilder(ActionType type, boolean bonusAction, PlayerStatus playerStatus){

        switch (type){
            case GREENTOWER:
                return new TowerActionBuilder(ActionType.GREENTOWER, towerHashMap.get(CardColor.GREEN), playerStatus, bonusAction);

            case YELLOWTOWER:
                return  new TowerActionBuilder(ActionType.YELLOWTOWER, towerHashMap.get(CardColor.YELLOW), playerStatus, bonusAction);

            case BLUETOWER:
                return new TowerActionBuilder(ActionType.BLUETOWER, towerHashMap.get(CardColor.BLUE), playerStatus, bonusAction);

            case PURPLETOWER:
                return new TowerActionBuilder(ActionType.PURPLETOWER, towerHashMap.get(CardColor.PURPLE), playerStatus, bonusAction);

            case MARKET:
                return new MarketActionBuilder(playerStatus, bonusAction);

            case COUNCILPALACE:
                return new CouncilPalaceActionBuilder(playerStatus, bonusAction);

            case HARVEST:
                return new WorkActionBuilder(ActionType.HARVEST, playerStatus, bonusAction);

            case PRODUCTION:
                return new WorkActionBuilder(ActionType.PRODUCTION, playerStatus, bonusAction);

            default: return null;
        }
    }
}
