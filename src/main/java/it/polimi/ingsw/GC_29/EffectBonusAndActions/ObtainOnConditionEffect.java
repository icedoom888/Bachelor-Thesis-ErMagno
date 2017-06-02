package it.polimi.ingsw.GC_29.EffectBonusAndActions;


import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Icedoom on 18/05/2017.
 */
public class ObtainOnConditionEffect extends ObtainEffect{

    //TODO da rivedere, i metodi di evaluate sono da unire, c'è un multiplier = 100 che non deve esistere

    private Effect effectForEachCondition;
    private CardColor cardCondition;
    private GoodSet goodsCondition;



    public ObtainOnConditionEffect(Effect effectForEachCondition, CardColor cardCondition) {

        super();

        this.effectForEachCondition = effectForEachCondition;

        this.cardCondition = cardCondition;

        this.goodsCondition = null;
    }

    public ObtainOnConditionEffect(Effect effectForEachCondition, GoodSet goodsCondition){

        super();

        this.effectForEachCondition = effectForEachCondition;

        this.cardCondition = null;

        this.goodsCondition = goodsCondition;
    }


    /**The execute function to evaluate the effect first needs to calculate the @goodsObtained based on the resolution
     * of a particular condition
     * Once calculated the @goodsObtained attribute the execute functions behaves in the same way as the ObtainEffect one
     * @param status
     */
    @Override
    public void execute(Player status) {

        evaluateActualGoodsObtained(status);

        System.out.println("With your current resources the goodset you will acquire is:"+"\n"+goodsObtained);

        super.execute(status);
    }

    /** evaluateActualGoodsObtain builds the @goodsObtained attribute evaluating the 2 conditions
     * @param status
     */
    public void evaluateActualGoodsObtained(Player status){

        evaluateCardCondition(status);

        evaluateGoodsCondition(status);
    }

    /** evaluateCardCondition looks in the playerstatus at the number of cards of the same color as the cardCondition,
     * that number is the multiplier.
     * The multiplier will be multiplied for the @effectForEachCondition attribute in order to calculate the goodsObtain attribute
     * @param status
     */
    private void evaluateCardCondition(Player status){

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
        for (int i = 0; i < multiplier; i++) {
            effectForEachCondition.execute(status);
        }
    }

    /**evaluateGoodsCondition looks in the playerstatus at the @actualGoodSet attribute
     * By dividing it by the @goodsCondition attribute it calculates the multiplier:
     * the multiplier is the lowest number got from the division.
     * The multiplier will be multiplied for the @effectForEachCondition attribute in order to calculate the goodsObtain attribute
     * @param status
     */
    private void evaluateGoodsCondition(Player status){

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
        for (int i = 0; i < multiplier; i++) {
            effectForEachCondition.execute(status);
        }
    }

    @Override
    public String toString() {
        return "ObtainOnConditionEffect{" +
                "effectForEachCondition=" + effectForEachCondition +
                ", cardCondition=" + cardCondition +
                ", goodsCondition=" + goodsCondition +
                ", goodsObtained=" + goodsObtained +
                '}';
    }
}
