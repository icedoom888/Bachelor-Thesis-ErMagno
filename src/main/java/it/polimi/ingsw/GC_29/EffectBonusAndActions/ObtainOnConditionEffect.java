package it.polimi.ingsw.GC_29.EffectBonusAndActions;


import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Icedoom on 18/05/2017.
 */
public class ObtainOnConditionEffect extends ObtainEffect{

    private GoodSet goodsForEachCondition;
    private CardColor cardCondition;
    private GoodSet goodsCondition;



    public ObtainOnConditionEffect(GoodSet goodsForEachCondition, CardColor cardCondition) {

        super();

        this.goodsForEachCondition = goodsForEachCondition;

        this.cardCondition = cardCondition;

        this.goodsCondition = null;
    }

    public ObtainOnConditionEffect(GoodSet goodsForEachCondition, GoodSet goodsCondition){

        super();

        this.goodsForEachCondition = goodsForEachCondition;

        this.cardCondition = null;

        this.goodsCondition = goodsCondition;
    }


    /**The execute function to evaluate the effect first needs to calculate the @goodsObtained based on the resolution
     * of a particular condition
     * Once calculated the @goodsObtained attribute the execute functions behaves in the same way as the ObtainEffect one
     * @param status
     */
    @Override
    public void execute(PlayerStatus status) {

        evaluateActualGoodsObtained(status);

        System.out.println("With your current resources the goodset you will acquire is:"+"\n"+goodsObtained);

        super.execute(status);
    }

    /** evaluateActualGoodsObtain builds the @goodsObtained attribute evaluating the 2 conditions
     * @param status
     */
    public void evaluateActualGoodsObtained(PlayerStatus status){

        evaluateCardCondition(status);

        evaluateGoodsCondition(status);
    }

    /** evaluateCardCondition looks in the playerstatus at the number of cards of the same color as the cardCondition,
     * that number is the multiplier.
     * The multiplier will be multiplied for the @goodsForEachCondition attribute in order to calculate the goodsObtain attribute
     * @param status
     */
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
            goodsObtained.getMapGoodSet().put(type,goodsForEachCondition.getGoodAmount(type)*multiplier);
        }
    }

    /**evaluateGoodsCondition looks in the playerstatus at the @actualGoodSet attribute
     * By dividing it by the @goodsCondition attribute it calculates the multiplier:
     * the multiplier is the lowest number got from the division.
     * The multiplier will be multiplied for the @goodsForEachCondition attribute in order to calculate the goodsObtain attribute
     * @param status
     */
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

            goodsObtained.getMapGoodSet().put(type,goodsForEachCondition.getGoodAmount(type)*multiplier);
        }
    }

    @Override
    public String toString() {
        return "ObtainOnConditionEffect{" +
                "goodsForEachCondition=" + goodsForEachCondition +
                ", cardCondition=" + cardCondition +
                ", goodsCondition=" + goodsCondition +
                ", goodsObtained=" + goodsObtained +
                '}';
    }
}
