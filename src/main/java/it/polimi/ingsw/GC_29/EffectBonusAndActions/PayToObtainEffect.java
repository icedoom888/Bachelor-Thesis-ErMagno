package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Icedoom on 18/05/2017.
 */
/* @JsonDeserialize(as = PayToObtainEffect.class) */

public class PayToObtainEffect extends ObtainEffect {

    //private String name = "payToObtainEffect";
    private GoodSet cost;

    public PayToObtainEffect(
            GoodSet cost,
            GoodSet goodsObtained) {

        super(goodsObtained); // va chiamato poichè non esiste costruttore di defaut classe padre
        this.cost = cost;
    }

    /** The execute of a @PayToObtainEffect first check's if the cost is payable based on the player's ActualGoodSet
     * if the cost is payable it is detracted from the player's ActualGoodSet,
     * and the @goodsObtained are handled with the same process as the ObtainEffects
     * if the cost isn't payable the execution stops: the cost isn't detracted from the status and the @goodsObtained are not added either
     */
    @Override
    public void execute(PlayerStatus status) {
        if(checkSufficientGoods(status)){
            System.out.println("Resources sufficient to activate!");
            status.updateGoodSet(cost);
            System.out.println("The actualGoodSet after the detraction is: "+ "\n"+status.getActualGoodSet());

            super.execute(status);
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
    private boolean checkSufficientGoods(PlayerStatus status){
        GoodSet newGoodset = new GoodSet();
        newGoodset.addGoodSet(status.getActualGoodSet());
        newGoodset.addGoodSet(cost);
        for(GoodType type : GoodType.values()){
            if(newGoodset.getGoodAmount(type)<0){
                return false;
            }
        }
        return true;
    }
}
