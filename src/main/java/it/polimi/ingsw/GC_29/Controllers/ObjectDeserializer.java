package it.polimi.ingsw.GC_29.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import it.polimi.ingsw.GC_29.ProveGSon.EnumMapInstanceCreator;
import it.polimi.ingsw.GC_29.ProveGSon.RuntimeTypeAdapterFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Created by Christian on 02/06/2017.
 */
public class ObjectDeserializer {

    final RuntimeTypeAdapterFactory<Effect> typeFactory = RuntimeTypeAdapterFactory
            .of(Effect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
            .registerSubtype(ObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect")
            .registerSubtype(ActionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect")
            .registerSubtype(BonusEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusEffect")
            .registerSubtype(CouncilPrivilegeEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect")
            .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect")
            .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect");

    private GsonBuilder gsonBuilder;

    Type listType = new TypeToken<ArrayList<DevelopmentCard>>(){}.getType();

    private HashMap<Integer, String> gameBoardFromFileMap;

    private Gson gsonCardDeserializer;

    public ObjectDeserializer(){

        gsonBuilder = new GsonBuilder()
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

        gsonCardDeserializer = gsonBuilder.create();

        gameBoardFromFileMap = new HashMap<>();

        gameBoardFromFileMap.put(2,"gameBoards\\gameBoard2Players\\");
        gameBoardFromFileMap.put(3,"gameBoards\\gameBoard3Players\\");
        gameBoardFromFileMap.put(4,"gameBoards\\gameBoard4Players\\");
        gameBoardFromFileMap.put(5,"gameBoards/gameBoard5Players");

    }

    public ArrayList<DevelopmentCard> getCardDeck(FileReader fileReader){

        return gsonCardDeserializer.fromJson(fileReader, listType);
    }

    public GameBoard getGameBoard(int numberOfPlayers) throws IOException {

        String filePath = gameBoardFromFileMap.get(numberOfPlayers);

        FileReader fileReader = new FileReader(filePath);

        GameBoard gameBoard = gsonBuilder.create().fromJson(fileReader, GameBoard.class);

        fileReader.close();

        return gameBoard;
    }
}

