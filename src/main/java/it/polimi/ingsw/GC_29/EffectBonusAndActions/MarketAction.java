package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.Market;
import it.polimi.ingsw.GC_29.Components.ShopName;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class MarketAction extends Action {

    private Market market;
    private ShopName houseSelected;

    public MarketAction(FamilyPawn pawnSelected, PlayerStatus playerStatus, ShopName houseSelected) {
        super(pawnSelected, ZoneType.MARKET, playerStatus);

        this.houseSelected = houseSelected;
        this.market = GameStatus.getInstance().getGameBoard().getMarket();
        this.actionSpaceSelected = market.getShop(houseSelected);
    }

}
