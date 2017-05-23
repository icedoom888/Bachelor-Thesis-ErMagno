package it.polimi.ingsw.GC_29.ProveGSon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

import java.lang.reflect.Type;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class ObtainEffectInstanceCreator implements InstanceCreator<ObtainEffect> {

    @Override
    public ObtainEffect createInstance(Type type) {

        ObtainEffect obtainEffect = new ObtainEffect();
        return obtainEffect;
    }


}