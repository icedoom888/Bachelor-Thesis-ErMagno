package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

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
    public void execute(PlayerStatus status) {
        GoodSet newCost = activateBonusMalusOnGoods(status.getBonusAndMalusOnGoods(),cost);
        if(checkSufficientGoods(status,newCost)){
            status.updateGoodSet(newCost);
            super.execute(status);
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
