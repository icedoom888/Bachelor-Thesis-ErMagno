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
import java.util.*;

/**
 * Created by Lorenzotara on 22/05/17.
 */
/*public class MainGSonToFile {

    public static void main(String[] args) throws IOException {


        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        HashMap<CardColor,Integer> requirementCard1 = new HashMap<>();
        requirementCard1.put(CardColor.BLUE,5);

        HashMap<CardColor,Integer> requirementCard2 = new HashMap<>();
        requirementCard2.put(CardColor.YELLOW,5);

        HashMap<CardColor,Integer> requirementCard8 = new HashMap<>();
        requirementCard8.put(CardColor.YELLOW,2);
        requirementCard8.put(CardColor.GREEN,2);
        requirementCard8.put(CardColor.BLUE,2);
        requirementCard8.put(CardColor.PURPLE,2);

        HashMap<CardColor,Integer> requirementCard9 = new HashMap<>();
        requirementCard9.put(CardColor.ANY,6);

        HashMap<CardColor,Integer> requirementCard11 = new HashMap<>();
        requirementCard11.put(CardColor.YELLOW,3);

        HashMap<CardColor,Integer> requirementCard12 = new HashMap<>();
        requirementCard12.put(CardColor.YELLOW,4);
        requirementCard12.put(CardColor.BLUE,2);

        HashMap<CardColor,Integer> requirementCard14 = new HashMap<>();
        requirementCard14.put(CardColor.PURPLE,4);
        requirementCard14.put(CardColor.YELLOW,2);

        HashMap<CardColor,Integer> requirementCard15 = new HashMap<>();
        requirementCard15.put(CardColor.GREEN,4);
        requirementCard15.put(CardColor.PURPLE,2);

        HashMap<CardColor,Integer> requirementCard16 = new HashMap<>();
        requirementCard16.put(CardColor.BLUE,4);
        requirementCard16.put(CardColor.GREEN,2);

        HashMap<CardColor,Integer> requirementCard17 = new HashMap<>();
        requirementCard17.put(CardColor.PURPLE,5);


        LeaderCard leaderCard1 = new LeaderCard("Ludovico Ariosto","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_02.jpg",new Requirement(null,requirementCard1),true,new Special(SpecialBonusAndMalus.PAWNONOCCUPIEDSPACE));
        LeaderCard leaderCard2 = new LeaderCard("Filippo Brunelleschi","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_03.jpg",new Requirement(null,requirementCard2),true,new Special(SpecialBonusAndMalus.FREETOWER));
        LeaderCard leaderCard3 = new LeaderCard("Sigismondo Malatesta","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_14.jpg",new Requirement(new GoodSet(0,0,0,0,0,7,3),null),true,null, new BonusAndMalusOnAction(ZoneType.ANYZONE,3,FamilyPawnType.NEUTRAL));
        LeaderCard leaderCard4 = new LeaderCard("Girolamo Savonarola","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_05.jpg",new Requirement(new GoodSet(0,0,18,0,0,0,0),null),false,new ObtainEffect(0,0,0,0,0,0,1));
        LeaderCard leaderCard5 = new LeaderCard("Michelangelo Buonarroti","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_08.jpg",new Requirement(new GoodSet(0,10,0,0,0,0,0),null),false,new ObtainEffect(0,0,3,0,0,0,0));
        LeaderCard leaderCard6 = new LeaderCard("Giovanni dalle Bande Nere","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_06.jpg",new Requirement(new GoodSet(0,0,0,0,0,12,0),null),false,new ObtainEffect(1,1,1,0,0,0,0));
        LeaderCard leaderCard7 = new LeaderCard("Sandro Botticelli","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_07.jpg",new Requirement(new GoodSet(10,0,0,0,0,0,0),null),false,new ObtainEffect(0,0,0,0,1,2,0));
        LeaderCard leaderCard8 = new LeaderCard("Ludovico il Moro","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_16.jpg",new Requirement(null,requirementCard8),true,new Special(SpecialBonusAndMalus.CHANGEVALUEOFEVERYPAWN));
        LeaderCard leaderCard9 = new LeaderCard("Lucrezia Borgia","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_13.jpg",new Requirement(null,requirementCard9),true,null, new BonusAndMalusOnAction(ZoneType.ANYZONE,2,FamilyPawnType.COLORED));
        LeaderCard leaderCard10 = new LeaderCard("Sisto IV","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_12.jpg",new Requirement(new GoodSet(6,6,6,6,0,0,0),null),true,new Special(SpecialBonusAndMalus.FIVEVICTORYPOINTSIFPRAY));
        LeaderCard leaderCard11 = new LeaderCard("Cesare Borgia","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_17.jpg",new Requirement(new GoodSet(0,0,12,0,0,0,2),requirementCard11),true,new Special(SpecialBonusAndMalus.NOMILITARYFORTERRITORY));
        LeaderCard leaderCard12 = new LeaderCard("Cosimo de' Medici","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_19.jpg",new Requirement(null,requirementCard12),false,new ObtainEffect(0,0,0,3,1,0,0));
        LeaderCard leaderCard13 = new LeaderCard("Ludovico III Gonzaga","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_09.jpg",new Requirement(new GoodSet(0,0,0,15,0,0,0),null),false,new CouncilPrivilegeEffect(1));
        LeaderCard leaderCard14 = new LeaderCard("Pico della Mirandola","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_11.jpg",new Requirement(null,requirementCard14),true,null , new BonusAndMalusOnCost(ZoneType.ANYTOWER,new GoodSet(0,0,3,0,0,0,0),null,false));
        LeaderCard leaderCard15 = new LeaderCard("Bartolomeo Colleoni","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_20.jpg",new Requirement(null,requirementCard15),false,new ObtainEffect(0,0,0,0,4,0,0));
        LeaderCard leaderCard16 = new LeaderCard("Leonardo da Vinci","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_10.jpg",new Requirement(null,requirementCard16),false,new ActionEffect(ZoneType.PRODUCTION,0,null,true));
        LeaderCard leaderCard17 = new LeaderCard("Francesco Sforza","lorenzo_materiale_grafico_compr/Lorenzo_Leaders_compressed/leaders_f_c_01.jpg",new Requirement(null,requirementCard17),false,new ActionEffect(ZoneType.HARVEST,1,null,true));


        leaderCards.add(leaderCard1);
        leaderCards.add(leaderCard2);
        leaderCards.add(leaderCard3);
        leaderCards.add(leaderCard4);
        leaderCards.add(leaderCard5);
        leaderCards.add(leaderCard6);
        leaderCards.add(leaderCard7);
        leaderCards.add(leaderCard8);
        leaderCards.add(leaderCard9);
        leaderCards.add(leaderCard10);
        leaderCards.add(leaderCard11);
        leaderCards.add(leaderCard12);
        leaderCards.add(leaderCard13);
        leaderCards.add(leaderCard14);
        leaderCards.add(leaderCard15);
        leaderCards.add(leaderCard16);
        leaderCards.add(leaderCard17);



        // toJson

        FileWriter fileWriter = new FileWriter("/Users/icedoom/leader");

        final RuntimeTypeAdapterFactory<Effect> typeFactory = RuntimeTypeAdapterFactory
                .of(Effect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(ObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect")
                .registerSubtype(ActionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect")
                .registerSubtype(BonusEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusEffect")
                .registerSubtype(CouncilPrivilegeEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect")
                .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect")
                .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect")
                .registerSubtype(Special.class,"it.polimi.ingsw.GC_29.EffectBonusAndActions.Special");

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


        gson.toJson(leaderCards, fileWriter);

        fileWriter.close();

    }

}
*/

