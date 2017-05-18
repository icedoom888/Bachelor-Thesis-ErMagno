package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.*;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 18/05/2017.
 */
public class ObtainOnConditionEffect extends ObtainEffect{

   private GoodSet goodsForEachCondition;
   private CardColor cardCondition;
   private GoodSet goodsCondition;

    public ObtainOnConditionEffect(GoodSet goodsForEachCondition, CardColor cardCondition, GoodSet goodsCondition) {
        super(); // va chiamato poichè non esiste costruttore di default classe padre
        this.goodsForEachCondition = goodsForEachCondition;
        this.cardCondition = cardCondition;
        this.goodsCondition = goodsCondition;
    }

    @Override
    public void execute(PlayerStatus status) {
        evaluateActualGoodsObtained(status);
        super.execute(status); // dopo che hai modificato l'attributo protected lanci la execute del metodo padre, senza chiamare altri metodi.
    }

    private void evaluateActualGoodsObtained(PlayerStatus status){
        // qui modifichi l'attributo goodsObtained della classe padre che è protected
    }
}
