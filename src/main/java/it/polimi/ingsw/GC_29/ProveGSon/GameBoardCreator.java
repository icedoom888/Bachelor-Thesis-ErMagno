package it.polimi.ingsw.GC_29.ProveGSon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lorenzotara on 25/05/17.
 */
public class GameBoardCreator {

    public static void main(String[] args) throws IOException {

        // Ospitare i mendicanti

        ArrayList<Effect> immediateEffectsOIM = new ArrayList<>();
        immediateEffectsOIM.add(new ObtainOnConditionEffect(new GoodSet(1,1,1,1,1,1,1), CardColor.BLUE));
        immediateEffectsOIM.add(new ObtainEffect(new GoodSet(1,1,1,1,1,1,1)));



        ArrayList<Effect> permanentEffectsOIM = new ArrayList<>();
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

        ExcommunicationTile tile1 = new ExcommunicationTile(Era.FIRST, "name1", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()),  new BonusAndMalusOnCost(null, null, null, true), "descriptiom1");
        ExcommunicationTile tile2 = new ExcommunicationTile(Era.FIRST, "name2", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()),  new BonusAndMalusOnCost(null, null, null, true),  "descriptiom2");
        ExcommunicationTile tile3 = new ExcommunicationTile(Era.FIRST, "name3", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()),  new BonusAndMalusOnCost(null, null, null, true), "descriptiom3");

        GameBoard gameBoard = new GameBoard(4);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile1, tile2, tile3);

        gameBoard.getTowerMap().get(CardColor.BLUE).getFloor(3).setDevelopmentCard(ospitareIMendicanti);

        // to Json

        FileWriter fileWriter = new FileWriter("/Users/Lorenzotara/Desktop/gameBoard");

        final RuntimeTypeAdapterFactory<Effect> typeFactory = RuntimeTypeAdapterFactory
                .of(Effect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(ObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect")
                .registerSubtype(ActionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect")
                .registerSubtype(BonusEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusEffect")
                .registerSubtype(CouncilPrivilegeEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect")
                .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect")
                .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect");

        final RuntimeTypeAdapterFactory<ExcommunicationTile> typeFactory1 = RuntimeTypeAdapterFactory
                .of(ExcommunicationTile.class, "@class");




        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(typeFactory);
        //gsonBuilder.registerTypeAdapterFactory(typeFactory1);
        gsonBuilder.enableComplexMapKeySerialization();


        Gson gson = gsonBuilder.setPrettyPrinting().create();


        gson.toJson(gameBoard, fileWriter);

        fileWriter.close();

        // from Json

        FileReader fileReader = new FileReader("/Users/Lorenzotara/Desktop/gameBoard");


        GameBoard gameBoardFromFile = gson.fromJson(fileReader, GameBoard.class);

        System.out.println("GameBoard creata in Java\n\n");
        System.out.println(gameBoard + "\n\n\n");

        System.out.println("GameBoard caricata da file\n\n");
        System.out.println(gameBoardFromFile + "\n\n\n");

        System.out.println(gameBoard.equals(gameBoardFromFile));

        //gameBoardFromFile.getTowerMap().get(CardColor.BLUE).getFloor(3).setDevelopmentCard(ospitareIMendicanti);
        DevelopmentCard card = gameBoardFromFile.getTowerMap().get(CardColor.BLUE).getFloor(3).getDevelopmentCard();


        System.out.println(card);

    }
}
