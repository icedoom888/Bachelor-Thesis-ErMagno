package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Icedoom on 18/05/2017.
 */
public class PayToObtainEffect extends ObtainEffect {

    private GoodSet cost;

    public PayToObtainEffect(GoodSet cost, GoodSet goodsObtained) {
        super(goodsObtained); // va chiamato poichè non esiste costruttore di defaut classe padre
        this.cost = cost;
    }

    @Override
    public void execute(Player player) {
        GoodSet newCost = activateBonusMalusOnGoods(player.getStatus().getBonusAndMalusOnGoodsToPayList(),cost);
        if(checkSufficientGoods(player.getStatus(),newCost)){
            update(player.getStatus(),newCost);
            super.execute(player);
        }
    }

    private boolean checkSufficientGoods(PlayerStatus status, GoodSet cost){
        GoodSet newGoodset = status.getActualGoodSet();
        newGoodset.addGoodSet(cost);
        for(GoodType type : GoodType.values()){
            if(newGoodset.getGoodAmount(type)<0){
                return false;
            }
        }
        return true;
    }
}