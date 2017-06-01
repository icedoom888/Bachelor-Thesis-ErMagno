package it.polimi.ingsw.GC_29.ProveGSon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.GC_29.Components.DevelopmentCard;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileReader;

import static org.testng.Assert.*;

/**
 * Created by AlbertoPennino on 01/06/2017.
 */
public class MainGSonFromFileTest {

    @Test
    public void testMain() throws Exception {
        final RuntimeTypeAdapterFactory<Effect> typeFactory = RuntimeTypeAdapterFactory
                .of(Effect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(ObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect")
                .registerSubtype(ActionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect")
                .registerSubtype(BonusEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusEffect")
                .registerSubtype(CouncilPrivilegeEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect")
                .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect")
                .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect");

       /* final RuntimeTypeAdapterFactory<ObtainEffect> typeFactory1 = RuntimeTypeAdapterFactory
                .of(ObtainEffect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect")
                .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect");*/



        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(typeFactory);
        //gsonBuilder.registerTypeAdapterFactory(typeFactory1);

        final Gson gson = gsonBuilder.create();

        FileReader fileReader = new FileReader("/Users/icedoom/Desktop/blueCards");

        DevelopmentCard[] card;

        //ObtainEffectInstanceCreator creator = new ObtainEffectInstanceCreator();

        card = gson.fromJson(fileReader, DevelopmentCard[].class);

        System.out.println(card);

    }

}