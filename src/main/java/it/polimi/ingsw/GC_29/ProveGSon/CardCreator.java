package it.polimi.ingsw.GC_29.ProveGSon;

import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
        import it.polimi.ingsw.GC_29.Components.*;
        import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

        import java.io.IOException;
        import java.io.FileWriter;
        import java.util.ArrayList;
        import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class CardCreator {
   public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        ArrayList<DevelopmentCard> cards = new ArrayList<>();
        boolean loop = true;

        while (loop) {
            System.out.println("Do you want to add a card to the list?");
            String answer = in.nextLine();

            if (answer.equals("y")){
                System.out.println("Insert card name:");
                String name = in.nextLine();

                System.out.println("Creation era..");
                Era era = createEra(in);

                System.out.println("Creation CardCost..");
                CardCost cardCost = createCardCost(in);

                System.out.println("Creation color..");
                CardColor color = createColor(in);

                System.out.println("Creation immediateEffect list..");
                ArrayList<Effect> immediateEffect = createEffectList();

                System.out.println("Creation permanentEffect list..");
                ArrayList<Effect> permanentEffect = createEffectList();

                System.out.println("Insert withActionValue boolean:");
                String withActionValue_in = in.nextLine();
                Boolean withActionValue = Boolean.parseBoolean(withActionValue_in);

                int actionValue = 0;
                if (withActionValue){
                    System.out.println("Insert actionValue int:");
                    actionValue = in.nextInt();
                }


                DevelopmentCard cardCreated = new DevelopmentCard(name,era,cardCost,color,immediateEffect,permanentEffect,withActionValue,actionValue);
                cards.add(cardCreated);
            }
            if(answer.equals("n")){
               loop = false;
            }
        }
        FileWriter fileWriter = new FileWriter("/Users/icedoom/Desktop/prova");

        final RuntimeTypeAdapterFactory<Effect> typeFactory = RuntimeTypeAdapterFactory
                .of(Effect.class, "@class") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(ObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect")
                .registerSubtype(ActionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect")
                .registerSubtype(BonusEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusEffect")
                .registerSubtype(CouncilPrivilegeEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect")
                .registerSubtype(ObtainOnConditionEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainOnConditionEffect")
                .registerSubtype(PayToObtainEffect.class, "it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect");



        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(typeFactory);
        //gsonBuilder.registerTypeAdapterFactory(typeFactory1);

        Gson gson = gsonBuilder.setPrettyPrinting().create();

        gson.toJson(cards, fileWriter);

        fileWriter.close();
    }


    public static CardCost createCardCost(Scanner in) {
        System.out.println("Insert alternative boolean:");
        String alternative_in = in.nextLine();
        Boolean alternative = Boolean.parseBoolean(alternative_in);

        System.out.println("Insert withPrice boolean:");
        String withPrice_in = in.nextLine();
        Boolean withPrice = Boolean.parseBoolean(withPrice_in);


        Cost mainCost = null;
        if (withPrice) {
            System.out.println("Creation mainCost ..");
            mainCost = createCost(in);
        }

        Cost alternativeCost = null;
        if (alternative) {
            System.out.println("Creation alternativeCost ..");
            alternativeCost = createCost(in);
        }
        CardCost cardCost = new CardCost(alternative,withPrice,mainCost,alternativeCost);
        return cardCost;
    }

    public static Cost createCost(Scanner in) {
        System.out.println("Insert Cost Goodset:");
        GoodSet price = createGoodSet(in);
        System.out.println("Insert NecessaryResources Goodset:");
        GoodSet necessaryResources = createGoodSet(in);
        Cost cost = new Cost(price,necessaryResources);
        return cost;
    }



    public static GoodSet createGoodSet(Scanner in){
        System.out.println("Insert wood amount:");
        int wood = in.nextInt();
        System.out.println("Insert stone amount:");
        int stone = in.nextInt();
        System.out.println("Insert coins amount:");
        int coins = in.nextInt();
        System.out.println("Insert workers amount:");
        int workers = in.nextInt();
        System.out.println("Insert victoryPoints amount:");
        int victoryPoints = in.nextInt();
        System.out.println("Insert militaryPoints amount:");
        int militaryPoints = in.nextInt();
        System.out.println("Insert faithPoints amount:");
        int faithPoints = in.nextInt();

        GoodSet goodSet = new GoodSet(wood,stone,coins,workers,victoryPoints,militaryPoints,faithPoints);
        return goodSet;

    }

    public static ZoneType createZoneType (Scanner in){
        System.out.println("Insert zoneType:");
        String zoneType_in = in.nextLine();
        ZoneType actionType = null;
        switch (zoneType_in){
            case "greenTower":
                actionType = ZoneType.GREENTOWER;
                break;
            case "yellowTower":
                actionType = ZoneType.YELLOWTOWER;
                break;
            case "blueTower":
                actionType = ZoneType.BLUETOWER;
                break;
            case "purpleTower":
                actionType = ZoneType.PURPLETOWER;
                break;
            case "market":
                actionType = ZoneType.MARKET;
                break;
            case "councilPalace":
                actionType = ZoneType.COUNCILPALACE;
                break;
            case "harvest":
                actionType = ZoneType.HARVEST;
                break;
            case "production":
                actionType = ZoneType.PRODUCTION;
                break;
            case "anyTower":
                actionType = ZoneType.ANYTOWER;
                break;
            default:
                actionType = createZoneType(in);
        }
        return actionType;
    }

    public static ArrayList<Effect> createEffectList(){
        Scanner in = new Scanner(System.in);
        ArrayList<Effect> effectlist = new ArrayList<Effect>();
        while (true) {

            System.out.println("Do you want to add an effect to the list?");
            String answer = in.nextLine();

            if (answer.equals("y")){
                effectlist.add(createEffect(in));
            }
            if(answer.equals("n")){
                return effectlist;
            }
        }
    }

    public static Effect createEffect(Scanner in) {
        System.out.println("Insert effect type:");
        String effectType = in.nextLine();
        switch (effectType) {

            case "ObtainEffect":
                System.out.println("Creation goodsObtained GoodSet..");
                ObtainEffect obtainEffect = new ObtainEffect(createGoodSet(in));
                return obtainEffect;

            case "PayToObtainEffect":
                System.out.println("Creation cost goodset..");
                GoodSet cost = createGoodSet(in);
                System.out.println("Creation effect..");
                Effect effect = createEffect(in);
                PayToObtainEffect payToObtainEffect = new PayToObtainEffect(cost, effect);
                return payToObtainEffect;


            case "ObtainOnConditionEffect":
                System.out.println("Creation goodsForEachCondition GoodSet..");
                GoodSet goodsForEachCondition = createGoodSet(in);
                System.out.println("Which kind of condition?");
                Scanner in_3 = new Scanner(System.in);
                String condition = in_3.nextLine();
                CardColor color = null;
                switch (condition) {
                    case "card":
                        color = createColor(in);
                        ObtainOnConditionEffect obtainOnConditionEffect1 = new ObtainOnConditionEffect(goodsForEachCondition, color);
                        return obtainOnConditionEffect1;

                    case "goods":
                        System.out.println("Creation goodsCondition GoodSet..");
                        GoodSet goodsOnCondition = createGoodSet(in);
                        ObtainOnConditionEffect obtainOnConditionEffect2 = new ObtainOnConditionEffect(goodsForEachCondition, goodsOnCondition);
                        return obtainOnConditionEffect2;
                }
                break;

            case "BonusEffect":
                BonusAndMalusOnAction bonusAndMalusOnAction = null;
                BonusAndMalusOnGoods bonusAndMalusOnGoods = null;
                BonusAndMalusOnCost bonusAndMalusOnCost = null;


                System.out.println("Does it have a BonusAndMalusOnAction?");
                String answer1 = in.nextLine();
                if (answer1.equals("y")) {
                    System.out.println("Creation BonusAndMalusOnAction..");
                    ZoneType actionType = createZoneType(in);
                    System.out.println("Insert diceIncrementOrReduction:");
                    int diceIncrementOrReduction = in.nextInt();
                    bonusAndMalusOnAction = new BonusAndMalusOnAction(actionType, diceIncrementOrReduction);
                }

                System.out.println("Does it have a BonusAndMalusOnGoods?");
                Scanner in_1 = new Scanner(System.in);
                String answer2 = in_1.nextLine();
                if (answer2.equals("y")) {
                    System.out.println("Creation BonusAndMalusOnGoods..");
                    System.out.println("Creation goodSetBonusMalus GoodSet..");
                    GoodSet goodSetBonusMalus = createGoodSet(in);
                    bonusAndMalusOnGoods = new BonusAndMalusOnGoods(goodSetBonusMalus);
                }

                System.out.println("Does it have a BonusAndMalusOnCost?");
                Scanner in_2 = new Scanner(System.in);
                String answer3 = in_2.nextLine();
                if (answer3.equals("y")) {
                    bonusAndMalusOnCost = createBonusAndMalusOnCost(in);
                }

                BonusEffect bonusEffect = new BonusEffect(bonusAndMalusOnAction, bonusAndMalusOnGoods, bonusAndMalusOnCost);
                return bonusEffect;

            case "ActionEffect":
                ZoneType actionType = createZoneType(in);

                System.out.println("Insert actionValue int:");
                int actionValue = in.nextInt();

                BonusAndMalusOnCost bonusAndMalusOnCost1 = null;
                System.out.println("Does it have a BonusAndMalusOnCost?");
                Scanner in_4 = new Scanner(System.in);
                String answer4 = in_4.nextLine();
                if (answer4.equals("y")) {
                    bonusAndMalusOnCost1 = createBonusAndMalusOnCost(in);
                }

                ActionEffect actionEffect = new ActionEffect(actionType, actionValue, bonusAndMalusOnCost1);
                return actionEffect;

            case "CouncilPrivilegeEffect":
                System.out.println("Insert numberOfCouncilPrivileges int:");
                int numberOfCouncilPrivileges = in.nextInt();
                CouncilPrivilegeEffect councilPrivilegeEffect = new CouncilPrivilegeEffect(numberOfCouncilPrivileges);
                return councilPrivilegeEffect;

            default:
                return createEffect(in);
        }
        return null;
    }

    public static BonusAndMalusOnCost createBonusAndMalusOnCost(Scanner in){
        System.out.println("Creation BonusAndMalusOnCost..");
        ZoneType actionType = createZoneType(in);
        System.out.println("Creation firstDiscount..");
        GoodSet firstDiscount = createGoodSet(in);
        System.out.println("Insert alternative boolean:");
        Scanner in_1 = new Scanner(System.in);
        Boolean alternative = in_1.nextBoolean();
        GoodSet secondDiscount = null;
        if (alternative){
            System.out.println("Creation secondDiscount..");
            secondDiscount= createGoodSet(in);
        }
        BonusAndMalusOnCost bonusAndMalusOnCost = new BonusAndMalusOnCost(actionType,firstDiscount,secondDiscount,alternative);
        return bonusAndMalusOnCost;
    }

    public static CardColor createColor(Scanner in){
        System.out.println("Insert color:");
        String color_in = in.nextLine();
        CardColor color = null;
        switch(color_in){
            case "g":
                color = CardColor.GREEN;
                break;
            case "b":
                color = CardColor.BLUE;
                break;
            case "y":
                color = CardColor.YELLOW;
                break;
            case "p":
                color = CardColor.PURPLE;
                break;
            default:
                color = createColor(in);
        }
        return color;
    }

    public static Era createEra(Scanner in){
        System.out.println("Insert era:");
        String era_in = in.nextLine();
        Era era = null;
        switch(era_in){
            case "1":
                era = Era.FIRST;
                break;
            case "2":
                era = Era.SECOND;
                break;
            case "3":
                era = Era.THIRD;
                break;
            default:
                era = createEra(in);
        }
        return era;
    }
}
