package it.polimi.ingsw.GC_29.ProveGSon;

import com.google.gson.InstanceCreator;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect;

import java.lang.reflect.Type;

/**
 * Created by Christian on 24/05/2017.
 */
public class ObtainOnConditionCreator implements InstanceCreator<ObtainOnConditionEffect>
{
    private GoodSet goodSet;
    private GoodSet goodSetCondition;

    public ObtainOnConditionCreator(GoodSet goodSet, GoodSet goodsetCond)
    {
        this.goodSet = goodSet;
        this.goodSetCondition = goodsetCond;
    }

    @Override
    public ObtainOnConditionEffect createInstance(Type type)
    {
        ObtainOnConditionEffect pi = new ObtainOnConditionEffect(goodSet, goodSetCondition);
        return pi;
    }
}