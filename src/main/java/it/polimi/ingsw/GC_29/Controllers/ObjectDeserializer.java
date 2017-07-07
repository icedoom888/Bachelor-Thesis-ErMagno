package it.polimi.ingsw.GC_29.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.GC_29.Model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by Christian on 02/06/2017.
 */
public class ObjectDeserializer {

    private static final Logger LOGGER = Logger.getLogger(ObjectDeserializer.class.getName());
    final RuntimeTypeAdapterFactory<Effect> typeFactory = RuntimeTypeAdapterFactory
            .of(Effect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
            .registerSubtype(ObtainEffect.class, "it.polimi.ingsw.GC_29.Model.ObtainEffect")
            .registerSubtype(ActionEffect.class, "it.polimi.ingsw.GC_29.Model.ActionEffect")
            .registerSubtype(BonusEffect.class, "it.polimi.ingsw.GC_29.Model.BonusEffect")
            .registerSubtype(CouncilPrivilegeEffect.class, "it.polimi.ingsw.GC_29.Model.CouncilPrivilegeEffect")
            .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.Model.ObtainOnConditionEffect")
            .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.Model.PayToObtainEffect")
            .registerSubtype(Special.class, "it.polimi.ingsw.GC_29.Model.Special")
            .registerSubtype(ObtainForCost.class, "it.polimi.ingsw.GC_29.Model.ObtainForCost")
            .registerSubtype(ObtainForMoreCondition.class, "it.polimi.ingsw.GC_29.Model.ObtainForMoreCondition");

    private GsonBuilder gsonBuilder;

    Type listType = new TypeToken<ArrayList<DevelopmentCard>>(){}.getType();

    private HashMap<Integer, String> gameBoardFromFileMap;

    private final String bonustilesFilePath = "bonusTiles/bonusTiles";
    private final String excommunicationFilePath = "excommunicationTiles/excommunications";
    private final String leaderFilePath = "leaderCards/leader";

    private Type bonusTileMapType = new TypeToken<Map<Integer,BonusTile>>(){}.getType();
    private Type excommunicationTileListType = new TypeToken<List<ExcommunicationTile>>(){}.getType();
    private Type leaderCardsListType = new TypeToken<List<LeaderCard>>(){}.getType();

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

        gameBoardFromFileMap.put(2,"gameBoards/gameBoard2Players");
        gameBoardFromFileMap.put(3,"gameBoards/gameBoard3Players");
        gameBoardFromFileMap.put(4,"gameBoards/gameBoard4Players");
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

    public Map<Integer, BonusTile> getBonusTiles() throws FileNotFoundException {

        FileReader fileReader = new FileReader(bonustilesFilePath);

        Map<Integer, BonusTile> bonusTiles = gsonBuilder.create().fromJson(fileReader, bonusTileMapType);

        try {
            fileReader.close();
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        }

        return bonusTiles;

    }

    public EnumMap<Era, List<ExcommunicationTile>> getExcommunicationTiles() throws FileNotFoundException {

        FileReader fileReader = new FileReader(excommunicationFilePath);

        List<ExcommunicationTile> excommunicationTiles = gsonBuilder.create().fromJson(fileReader, excommunicationTileListType);

        EnumMap<Era, List<ExcommunicationTile>> eraAndExcomm = new EnumMap<>(Era.class);

        ArrayList<ExcommunicationTile> firstEra = new ArrayList<>();
        ArrayList<ExcommunicationTile> secondEra = new ArrayList<>();
        ArrayList<ExcommunicationTile> thirdEra = new ArrayList<>();

        for (ExcommunicationTile excommunicationTile : excommunicationTiles) {

            switch (excommunicationTile.getEra()) {

                case FIRST:
                    firstEra.add(excommunicationTile);
                    break;

                case SECOND:
                    secondEra.add(excommunicationTile);
                    break;

                case THIRD:
                    thirdEra.add(excommunicationTile);
                    break;

            }
        }

        eraAndExcomm.put(Era.FIRST, firstEra);
        eraAndExcomm.put(Era.SECOND, secondEra);
        eraAndExcomm.put(Era.THIRD, thirdEra);

        return eraAndExcomm;
    }

    public ArrayList<LeaderCard> getLeaderCards() throws FileNotFoundException {

        FileReader fileReader = new FileReader(leaderFilePath);

        return gsonBuilder.create().fromJson(fileReader, leaderCardsListType);
    }
}

