package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.Market;
import it.polimi.ingsw.GC_29.Components.ShopName;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class MarketAction extends Action {

    private ShopName shopName;

    public MarketAction(ShopName houseSelected) {
        super(ZoneType.MARKET);

        this.shopName = houseSelected;

        this.actionSpaceSelected = GameStatus.getInstance().getGameBoard().getMarket().getShop(shopName);
    }

    @Override
    public String toString() {
        return "MarketAction{" + super.toString() + ", shopName = " + shopName + "}";
    }
}
