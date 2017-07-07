package it.polimi.ingsw.GC_29.Model;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 13/06/17.
 */
public class ObtainForMoreCondition implements Effect {

    private Effect effectForEachCondition;
    private ArrayList<GoodSet> goodsCondition;

    public ObtainForMoreCondition(Effect effectForEachCondition, ArrayList<GoodSet> goodsCondition) {
        this.effectForEachCondition = effectForEachCondition;
        this.goodsCondition = goodsCondition;
    }

    @Override
    public void execute(Player status) {
        for (GoodSet goodSet : goodsCondition) {
            (new ObtainOnConditionEffect(effectForEachCondition,goodSet)).execute(status);
        }

    }
}
