package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Controllers.Model;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class MarketAction extends Action {

    private ShopName shopName;

    public MarketAction(ShopName houseSelected,
                        Model model) {
        super(ZoneType.MARKET, model);

        this.shopName = houseSelected;

        this.actionSpaceSelected = this.model.getGameBoard().getMarket().getShop(shopName);
    }

    @Override
    public String toString() {
        return "MarketAction{" + super.toString() + ", shopName = " + shopName + "}";
    }
}
