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

    private ShopName houseSelected;

    public MarketAction(FamilyPawn pawnSelected, Player player, ShopName houseSelected) {
        super(pawnSelected, ZoneType.MARKET, player);

        this.houseSelected = houseSelected;
        this.actionSpaceSelected = GameStatus.getInstance().getGameBoard().getMarket().getShop(houseSelected);
    }

}
