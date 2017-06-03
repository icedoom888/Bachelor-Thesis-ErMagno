package it.polimi.ingsw.GC_29.ProveGSon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class MainGSonToFile {

    public static void main(String[] args) throws IOException {


        // Ospitare i mendicanti

        ArrayList<Effect> immediateEffectsOIM = new ArrayList<>();
        immediateEffectsOIM.add(new ObtainOnConditionEffect(new ObtainEffect(new GoodSet(1,1,1,1,1,1,1)), CardColor.BLUE));
        immediateEffectsOIM.add(new ObtainEffect(new GoodSet(1,1,1,1,1,1,1)));



        ArrayList<Effect> permanentEffectsOIM = new ArrayList<>();
        //permanentEffectsOIM.add(new ActionEffect(ZoneType.SKIPTURN,4,new Discount(new GoodSet(), new GoodSet(), false)));
        permanentEffectsOIM.add(new ObtainEffect(new GoodSet()));
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

        /*FileWriter fileWriter = new FileWriter("C:\\Users\\Christian\\Desktop\\goodsForPlayerSetup");

        final RuntimeTypeAdapterFactory<Effect> typeFactory = RuntimeTypeAdapterFactory
                .of(Effect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(ObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect")
                .registerSubtype(ActionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect")
                .registerSubtype(BonusEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusEffect")
                .registerSubtype(CouncilPrivilegeEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect")
                .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect")
                .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect");



        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapterFactory(typeFactory)
                .enableComplexMapKeySerialization();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<ZoneType, Tower>>() {
                }.getType(),
                new EnumMapInstanceCreator<ZoneType, Tower>(ZoneType.class)).create();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<Era, ExcommunicationSlot>>() {
                }.getType(),
                new EnumMapInstanceCreator<Era, ExcommunicationSlot>(Era.class)).create();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<ZoneType, Workspace>>() {
                }.getType(),
                new EnumMapInstanceCreator<ZoneType, Workspace>(ZoneType.class)).create();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<GoodType, Integer>>() {
                }.getType(),
                new EnumMapInstanceCreator<GoodType, Integer>(GoodType.class)).create();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<ShopName, ActionSpace>>() {
                }.getType(),
                new EnumMapInstanceCreator<ShopName, ActionSpace>(ShopName.class)).create();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<CardColor, Lane>>() {
                }.getType(),
                new EnumMapInstanceCreator<CardColor, Lane>(CardColor.class)).create();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<FieldType, ActionSpace>>() {
                }.getType(),
                new EnumMapInstanceCreator<FieldType, ActionSpace>(FieldType.class)).create();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<CardColor, ArrayDeque<DevelopmentCard>>>() {
                }.getType(),
                new EnumMapInstanceCreator<CardColor, ArrayDeque<DevelopmentCard>>(CardColor.class)).create();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<Era, ArrayList<ExcommunicationTile>>>() {
                }.getType(),
                new EnumMapInstanceCreator<Era,ArrayList<ExcommunicationTile>>(Era.class)).create();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<CardColor, Integer>>() {
                }.getType(),
                new EnumMapInstanceCreator<CardColor, Integer>(CardColor.class)).create();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<FamilyPawnType, Boolean>>() {
                }.getType(),
                new EnumMapInstanceCreator<FamilyPawnType, Boolean>(FamilyPawnType.class)).create();



        Gson gson = gsonBuilder.setPrettyPrinting().create();

        ArrayList<GoodSet> goodSets = new ArrayList<>();
        goodSets.add(new GoodSet(2,2,5,3,0,0,0));
        goodSets.add(new GoodSet(2,2,6,3,0,0,0));
        goodSets.add(new GoodSet(2,2,7,3,0,0,0));
        goodSets.add(new GoodSet(2,2,8,3,0,0,0));

        gson.toJson(goodSets, fileWriter);

        fileWriter.close();*/

    }


}