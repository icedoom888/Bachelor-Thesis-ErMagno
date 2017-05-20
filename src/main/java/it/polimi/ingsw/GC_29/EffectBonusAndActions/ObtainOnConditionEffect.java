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
    public void execute(Player player) {
        evaluateActualGoodsObtained(player);
        super.execute(player);
    }

    public void evaluateActualGoodsObtained(Player player){
        evaluateCardCondition(player);
        evaluateGoodsCondition(player.getStatus());
    }

    private void evaluateCardCondition(Player player){
        if(cardCondition==null){return;}
        int multiplier=0;

        if(cardCondition==CardColor.BLUE){
            multiplier = player.getPersonalBoard().getFamilyLane().getNumberOfCardsPresent();
        }
        if(cardCondition==CardColor.YELLOW){
            multiplier = player.getPersonalBoard().getBuildingLane().getNumberOfCardsPresent();
        }
        if(cardCondition==CardColor.PURPLE){
            multiplier = player.getPersonalBoard().getVenturesLane().getNumberOfCardsPresent();
        }
        if(cardCondition==CardColor.GREEN){
            multiplier = player.getPersonalBoard().getTerritoryLane().getNumberOfCardsPresent();
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
