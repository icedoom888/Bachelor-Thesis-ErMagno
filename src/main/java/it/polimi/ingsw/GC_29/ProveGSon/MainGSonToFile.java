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


        // GameBoard

        int[] victoryPointsForFaithTrack = {0,1,2,3,4,5,7,9,11,13,15,17,19,22,25,30};

        FaithPointsTrack faithPointsTrack = new FaithPointsTrack(5,16,victoryPointsForFaithTrack);


        GameBoard gameBoard = new GameBoard(5, faithPointsTrack);

        // toJson

        FileWriter fileWriter = new FileWriter("C:\\Users\\Christian\\Desktop\\prova");

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


        gson.toJson(gameBoard, fileWriter);

        fileWriter.close();

    }


}