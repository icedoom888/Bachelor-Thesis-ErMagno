package it.polimi.ingsw.GC_29.ProveGSon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class MainGSonToFile {

    public static void main(String[] args) throws IOException {


        // Ospitare i mendicanti

        ArrayList<Effect> immediateEffectsOIM = new ArrayList<Effect>();
        immediateEffectsOIM.add(new ObtainOnConditionEffect(new GoodSet(1,1,1,1,1,1,1), CardColor.BLUE));
        immediateEffectsOIM.add(new ObtainEffect(new GoodSet(1,1,1,1,1,1,1)));



        ArrayList<Effect> permanentEffectsOIM = new ArrayList<Effect>();
        //permanentEffectsOIM.add(new ActionEffect(ZoneType.SKIPTURN,4,new Discount(new GoodSet(), new GoodSet(), false)));
        //permanentEffectsOIM.add(new ObtainEffect(new GoodSet()));
        //permanentEffectsOIM.add(new ActionEffect(ZoneType.SKIPTURN, 4, new Discount(new GoodSet(), new GoodSet(),false)));



        DevelopmentCard ospitareIMendicanti = new DevelopmentCard(
                "Ospitare i Mendicanti",
                Era.FIRST,
                new CardCost(false, true, new Cost(new GoodSet(4,0,0,0,0,0,0), new GoodSet()), new Cost(new GoodSet(), new GoodSet())),
                CardColor.PURPLE,
                immediateEffectsOIM,
                permanentEffectsOIM,
                false,
                0);

        // toJson

        FileWriter fileWriter = new FileWriter("/Users/Lorenzotara/Desktop/cartaprova");

        final RuntimeTypeAdapterFactory<Effect> typeFactory = RuntimeTypeAdapterFactory
                .of(Effect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(ObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect")
                .registerSubtype(ActionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect")
                .registerSubtype(BonusEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusEffect")
                .registerSubtype(CouncilPrivilegeEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect")
                .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect")
                .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect");

        /*final RuntimeTypeAdapterFactory<ObtainEffect> typeFactory1 = RuntimeTypeAdapterFactory
                .of(ObtainEffect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect")
                .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect");*/



        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(typeFactory);
        //gsonBuilder.registerTypeAdapterFactory(typeFactory1);

        Gson gson = gsonBuilder.setPrettyPrinting().create();


        gson.toJson(ospitareIMendicanti, fileWriter);

        fileWriter.close();
    }


}