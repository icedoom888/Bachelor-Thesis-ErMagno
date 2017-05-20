package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Icedoom on 18/05/2017.
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
        super.execute(status);
    }

    public void evaluateActualGoodsObtained(PlayerStatus status){
        evaluateCardCondition(status);
        evaluateGoodsCondition(status);
    }

    private void evaluateCardCondition(PlayerStatus status){
        if(cardCondition==null){return;}
        int multiplier=0;

        if(cardCondition==CardColor.BLUE){
            multiplier = status.getNumberOfCardsOwned(CardColor.BLUE);
        }
        if(cardCondition==CardColor.YELLOW){
            multiplier = status.getNumberOfCardsOwned(CardColor.YELLOW);
        }
        if(cardCondition==CardColor.PURPLE){
            multiplier = status.getNumberOfCardsOwned(CardColor.PURPLE);
        }
        if(cardCondition==CardColor.GREEN){
            multiplier = status.getNumberOfCardsOwned(CardColor.GREEN);
        }
        for(GoodType type : GoodType.values()) {
            goodsObtained.getHashMapGoodSet().put(type,goodsForEachCondition.getGoodAmount(type)*multiplier);
        }
    }

    private void evaluateGoodsCondition(PlayerStatus status){
        if(goodsCondition==null){return;}
        int multiplier=100;//valore inizializzato alto per necessità

        for(GoodType type : GoodType.values()){
            if(goodsCondition.getGood(type).getAmount()!=0){
                int temporaryMultiplier = status.getActualGoodSet().getGood(type).getAmount()/goodsCondition.getGood(type).getAmount();
                if(temporaryMultiplier<=multiplier){
                    multiplier=temporaryMultiplier;
                }
            }
        }
        for(GoodType type : GoodType.values()) {
            goodsObtained.getHashMapGoodSet().put(type,goodsForEachCondition.getGoodAmount(type)*multiplier);
        }
    }
}
