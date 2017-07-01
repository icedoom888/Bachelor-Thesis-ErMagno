package it.polimi.ingsw.GC_29.ProveGSon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class MainGSonToFileExcommunication {

    public static void main(String[] args) throws IOException {


        /* GameBoard

        int[] victoryPointsForFaithTrack = {0,1,2,3,4,5,7,9,11,13,15,17,19,22,25,30};

        FaithPointsTrack faithPointsTrack = new FaithPointsTrack(5,16,victoryPointsForFaithTrack);


        GameBoard gameBoard = new GameBoard(5, faithPointsTrack);*/

        ArrayList<ExcommunicationTile> tiles = new ArrayList<>();

        ArrayList<GoodType> fortile21 = new ArrayList<>();
        fortile21.add(GoodType.WOOD);
        fortile21.add(GoodType.STONE);

        ArrayList<GoodSet> fortile20= new ArrayList<>();
        fortile20.add(new GoodSet(1,0,0,0,0, 0,0));
        fortile20.add(new GoodSet(0,1,0,0,0,0,0));
        fortile20.add(new GoodSet(0,0,1,0,0,0,0));
        fortile20.add(new GoodSet(0,0,0,1,0,0,0));



        ExcommunicationTile tile1 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_1_1.png",Era.FIRST,null,null, new BonusAndMalusOnGoods(new GoodSet(0,0,0,0,0,-1,0)),null,null);
        ExcommunicationTile tile2 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_1_2.png",Era.FIRST,null,null, new BonusAndMalusOnGoods(new GoodSet(0,0,-1,0,0,0,0)),null,null);
        ExcommunicationTile tile3 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_1_3.png",Era.FIRST,null,null, new BonusAndMalusOnGoods(new GoodSet(0,0,0,-1,0,0,0)),null,null);
        ExcommunicationTile tile4 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_1_4.png",Era.FIRST,null,null, new BonusAndMalusOnGoods(new GoodSet(-1,-1,0,0,0,0,0)),null,null);

        ExcommunicationTile tile5 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_1_7.png",Era.FIRST,null, new BonusAndMalusOnAction(ZoneType.ANYZONE,-1,FamilyPawnType.COLORED), null,null,null);
        ExcommunicationTile tile6 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_1_6.png",Era.FIRST,null, new BonusAndMalusOnAction(ZoneType.PRODUCTION,-3,FamilyPawnType.ANY), null,null,null);
        ExcommunicationTile tile7 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_1_5.png",Era.FIRST,null, new BonusAndMalusOnAction(ZoneType.HARVEST,-3,FamilyPawnType.ANY), null,null,null);

        ExcommunicationTile tile8 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_2_4.png",Era.SECOND,null, new BonusAndMalusOnAction(ZoneType.PURPLETOWER,-4,FamilyPawnType.ANY), null,null,null);
        ExcommunicationTile tile9 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_2_1.png",Era.SECOND,null, new BonusAndMalusOnAction(ZoneType.GREENTOWER,-4,FamilyPawnType.ANY), null,null,null);
        ExcommunicationTile tile10 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_2_2.png",Era.SECOND,null, new BonusAndMalusOnAction(ZoneType.YELLOWTOWER,-4,FamilyPawnType.ANY), null,null,null);
        ExcommunicationTile tile11 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_2_3.png",Era.SECOND,null, new BonusAndMalusOnAction(ZoneType.BLUETOWER,-4,FamilyPawnType.ANY), null,null,null);

        ExcommunicationTile tile12 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_2_5.png",Era.SECOND,SpecialBonusAndMalus.NOMARKET,null, null,null,null);
        ExcommunicationTile tile13 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_2_7.png",Era.SECOND,SpecialBonusAndMalus.SKIPFIRSTTURN,null, null,null,null);
        ExcommunicationTile tile14 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_2_6.png",Era.SECOND,SpecialBonusAndMalus.TWOWORKERS,null, null,null,null);

        ExcommunicationTile tile15 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_3_1.png",Era.THIRD, SpecialBonusAndMalus.NOVICTORYFROMBLUE,null, null,null,null);
        ExcommunicationTile tile16 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_3_3.png",Era.THIRD,SpecialBonusAndMalus.NOVICTORYFROMGREEN,null, null,null,null);
        ExcommunicationTile tile17 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_3_2.png",Era.THIRD,SpecialBonusAndMalus.NOVICTORYFROMPURPLE,null, null,null,null);

        ExcommunicationTile tile18 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_3_5.png",Era.THIRD,null,null, null,null,new ObtainOnConditionEffect( new ObtainEffect(0,0,0,0,-1,0,0), new GoodSet(0,0,0,0,0,1,0)));
        ExcommunicationTile tile19 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_3_4.png",Era.THIRD,null,null, null,null,new ObtainOnConditionEffect( new ObtainEffect(0,0,0,0,-1,0,0), new GoodSet(0,0,0,0,5,0,0)));

        ExcommunicationTile tile20 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_3_7.png",Era.THIRD,null,null, null,null,new ObtainForMoreCondition(new ObtainEffect(0,0,0,0,-1,0,0),fortile20));

        ExcommunicationTile tile21 = new ExcommunicationTile("lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/excomm_3_6.png",Era.THIRD,null,null, null,null, new ObtainForCost(CardColor.YELLOW,fortile21, new GoodSet(0,0,0,0,-1,0,0)));


        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        tiles.add(tile5);
        tiles.add(tile6);
        tiles.add(tile7);
        tiles.add(tile8);
        tiles.add(tile9);
        tiles.add(tile10);
        tiles.add(tile11);
        tiles.add(tile12);
        tiles.add(tile13);
        tiles.add(tile14);
        tiles.add(tile15);
        tiles.add(tile16);
        tiles.add(tile17);
        tiles.add(tile18);
        tiles.add(tile19);
        tiles.add(tile20);
        tiles.add(tile21);


        // toJson

        FileWriter fileWriter = new FileWriter("/Users/icedoom/excommunications");

        final RuntimeTypeAdapterFactory<Effect> typeFactory = RuntimeTypeAdapterFactory
                .of(Effect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(ObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect")
                .registerSubtype(ActionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect")
                .registerSubtype(BonusEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusEffect")
                .registerSubtype(CouncilPrivilegeEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect")
                .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect")
                .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect")
                .registerSubtype(Special.class,"it.polimi.ingsw.GC_29.EffectBonusAndActions.Special")
                .registerSubtype(ObtainForMoreCondition.class,"it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainForMoreCondition")
                .registerSubtype(ObtainForCost.class,"it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainForCost");



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


        gson.toJson(tiles, fileWriter);

        fileWriter.close();

    }


}