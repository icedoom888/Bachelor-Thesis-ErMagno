package it.polimi.ingsw.GC_29.EffectBonusAndActions;


import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Icedoom on 18/05/2017.
 */
public class PayToObtainEffect implements Effect{

    private GoodSet cost;
    private Effect effect;

    public PayToObtainEffect(GoodSet cost, Effect effect) {
        this.cost = cost;
        this.effect= effect;
    }

    /** The execute of a @PayToObtainEffect first check's if the cost is payable based on the player's ActualGoodSet
     * if the cost is payable it is detracted from the player's ActualGoodSet,
     * and the @goodsObtained are handled with the same process as the ObtainEffects
     * if the cost isn't payable the execution stops: the cost isn't detracted from the status and the @goodsObtained are not added either
     */
    @Override
    public void execute(Player status) {

        if(checkSufficientGoods(status)){

            System.out.println("Resources sufficient to activate!");

            status.getActualGoodSet().subGoodSet(cost);

            System.out.println("The actualGoodSet after the detraction is: "+ "\n"+status.getActualGoodSet());

            effect.execute(status);
        }

        else{

            System.out.println("Resources not sufficient!");

        }
    }

    /** checkSufficientGoods is used to make sure the price of the effect is payable with the player's resources:
     * it creates a copy of the player's actualGoodSet and detracts from it the cost of the PayToObtainEffect
     * checks every single GoodAmount making sure they are all positive
     * if there is a negative amount the function returns false.
     * @param status
     * @return boolean that indicates if the player's resources are enough to activate the effect
     */
    private boolean checkSufficientGoods(Player status){

        GoodSet playerGoodSet = status.getActualGoodSet();

        if (playerGoodSet.enoughResources(cost)){
            return true;
        } else return false;

    }

    @Override
    public String toString() {
        return "PayToObtainEffect{" +
                "cost=" + cost +
                ", effect=" + effect +
                '}';
    }
}
