package it.polimi.ingsw.GC_29.ProveGSon;

        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
        import it.polimi.ingsw.GC_29.Components.*;
        import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

        import java.io.IOException;
        import java.io.FileWriter;
        import java.util.ArrayList;
        import java.util.Scanner;



public class CardCreator {
   /* public static void main(String[] args) throws IOException {
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
                ArrayList<Effect> immediateEffect = createEffectList(in);

                System.out.println("Creation permanentEffect list..");
                ArrayList<Effect> permanentEffect = createEffectList(in);

                System.out.println("Insert withActionValue boolean:");
                String withActionValue_in = in.nextLine();
                Boolean withActionValue = Boolean.parseBoolean(withActionValue_in);

                int actionValue = 0;
                if (withActionValue){
                    System.out.println("Insert actionValue int:");
                    actionValue = in.nextInt();
                }


                DevelopmentCard cardCreated = new DevelopmentCard(name,era,cardCost,color,immediateEffect,permanentEffect,withActionValue,actionValue);
                System.out.println("Do you want to add the card?");
                System.out.println(cardCreated);
                String answer3 = in.nextLine();
                if (answer3.equals("y")) {
                    cards.add(cardCreated);
                }
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

        GoodSet mainCost = null;
        if (withPrice) {
            System.out.println("Creation mainCost GoodSet..");
            mainCost = createGoodSet(in);
        }

        GoodSet alternativeCost = null;
        if (alternative) {
            System.out.println("Creation alternativeCost GoodSet..");
            alternativeCost = createGoodSet(in);
        }

        System.out.println("Insert necessaryGoodSetForMainCost boolean:");
        String necessaryGoodSetForMainCost_in = in.nextLine();
        Boolean necessaryGoodSetForMainCost = Boolean.parseBoolean(necessaryGoodSetForMainCost_in);

        GoodSet necessaryGoodset = null;
        if (necessaryGoodSetForMainCost) {
            System.out.println("Creation necessaryGoodSet GoodSet..");
            necessaryGoodset = createGoodSet(in);
        }

        CardCost cardCost = new CardCost(alternative, withPrice, mainCost, alternativeCost, necessaryGoodSetForMainCost, necessaryGoodset);
        return cardCost;
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

    public static ArrayList<Effect> createEffectList(Scanner in){
        ArrayList<Effect> effectlist = new ArrayList<Effect>();
        while (true) {
            System.out.println("Do you want to add an effect to the list?");
            String answer = in.nextLine();
            if (answer.equals("y")){
                System.out.println("Insert effect type:");
                String effectType = in.nextLine();
                switch (effectType){
                    case "ObtainEffect":
                        System.out.println("Creation goodsObtained GoodSet..");
                        ObtainEffect obtainEffect = new ObtainEffect(createGoodSet(in));
                        effectlist.add(obtainEffect);
                        break;
                    case "PayToObtainEffect":
                        System.out.println("Creation cost goodset..");
                        GoodSet cost = createGoodSet(in);
                        System.out.println("Creation goodsObtained GoodSet..");
                        GoodSet goodsObtained = createGoodSet(in);
                        PayToObtainEffect payToObtainEffect = new PayToObtainEffect(cost,goodsObtained);
                        effectlist.add(payToObtainEffect);
                        break;
                    case "ObtainOnConditionEffect":
                        System.out.println("Creation goodsForEachCondition GoodSet..");
                        GoodSet goodsForEachCondition = createGoodSet(in);
                        System.out.println("Which kind of condition?");
                        String condition = in.nextLine();
                        CardColor color = null;
                        switch(condition){
                            case "card":
                                System.out.println("Insert color:");
                                String color_in = in.nextLine();
                                switch (color_in) {
                                    case "green":
                                        color = CardColor.GREEN;
                                        break;
                                    case "blue":
                                        color = CardColor.BLUE;
                                        break;
                                    case "yellow":
                                        color = CardColor.YELLOW;
                                        break;
                                    case "purple":
                                        color = CardColor.PURPLE;
                                        break;
                                }
                                ObtainOnConditionEffect obtainOnConditionEffect1 = new ObtainOnConditionEffect(goodsForEachCondition,color);
                                effectlist.add(obtainOnConditionEffect1);
                                break;
                            case "goods":
                                System.out.println("Creation goodsCondition GoodSet..");
                                GoodSet goodsOnCondition = createGoodSet(in);
                                ObtainOnConditionEffect obtainOnConditionEffect2 = new ObtainOnConditionEffect(goodsForEachCondition,goodsOnCondition);
                                effectlist.add(obtainOnConditionEffect2);
                                break;
                        }
                        break;
                    case "BonusEffect":
                        System.out.println("Creation BonusAndMalusOnAction..");
                        System.out.println("Insert actionType:");
                        String actionType_in = in.nextLine();
                        ActionType actionType = null;
                        switch (actionType_in){
                            case "greenTower":
                                actionType = ActionType.GREENTOWER;
                                break;
                            case "yellowTower":
                                actionType = ActionType.YELLOWTOWER;
                                break;
                            case "blueTower":
                                actionType = ActionType.BLUETOWER;
                                break;
                            case "purpleTower":
                                actionType = ActionType.PURPLETOWER;
                                break;
                            case "market":
                                actionType = ActionType.MARKET;
                                break;
                            case "councilPalace":
                                actionType = ActionType.COUNCILPALACE;
                                break;
                            case "harvest":
                                actionType = ActionType.HARVEST;
                                break;
                            case "production":
                                actionType = ActionType.PRODUCTION;
                                break;
                        }

                        System.out.println("Insert diceIncrementOrReduction:");
                        int diceIncrementOrReduction = in.nextInt();

                        System.out.println("esiste un goodSetDiscountOrIncrement?");
                        String existDiscount_in = in.nextLine();
                        Boolean existDiscount = Boolean.parseBoolean(existDiscount_in);

                        GoodSet goodSetDiscountOrIncrement = null;
                        if (existDiscount) {
                            System.out.println("Creation goodSetDiscountOrIncrement GoodSet..");
                            goodSetDiscountOrIncrement = createGoodSet(in);
                        }

                        System.out.println("Insert actionAllowed bool:");
                        String actionAllawed_in = in.nextLine();
                        Boolean actionAllawed = Boolean.parseBoolean(actionAllawed_in);

                        BonusAndMalusOnAction bonus = null;
                        if (actionAllawed) {
                            if(existDiscount){
                                bonus = new BonusAndMalusOnAction(actionType,diceIncrementOrReduction,goodSetDiscountOrIncrement);
                            }
                            else{
                                bonus = new BonusAndMalusOnAction(actionType,diceIncrementOrReduction);
                            }
                        }
                        else {
                            bonus = new BonusAndMalusOnAction(actionType,actionAllawed);
                        }

                        BonusEffect bonusEffect = new BonusEffect(bonus);
                        effectlist.add(bonusEffect);
                        break;
                    case "ActionEffect":
                        System.out.println("Insert actionType:");
                        String actionType_in2 = in.nextLine();
                        ActionType actionType2 = null;
                        switch (actionType_in2){
                            case "greenTower":
                                actionType2 = ActionType.GREENTOWER;
                                break;
                            case "yellowTower":
                                actionType2 = ActionType.YELLOWTOWER;
                                break;
                            case "blueTower":
                                actionType2 = ActionType.BLUETOWER;
                                break;
                            case "purpleTower":
                                actionType2 = ActionType.PURPLETOWER;
                                break;
                            case "market":
                                actionType2 = ActionType.MARKET;
                                break;
                            case "councilPalace":
                                actionType2 = ActionType.COUNCILPALACE;
                                break;
                            case "harvest":
                                actionType2 = ActionType.HARVEST;
                                break;
                            case "production":
                                actionType2 = ActionType.PRODUCTION;
                                break;
                        }

                        System.out.println("Insert actionValue int:");
                        int actionValue = in.nextInt();

                        System.out.println("Creation Discount ..");
                        System.out.println("Creation firstDiscount ..");
                        GoodSet firstDiscount = createGoodSet(in);

                        System.out.println("Insert alternativeDiscount bool:");
                        Boolean alternativeDiscount = in.nextBoolean();
                        Discount discount = null;
                        if(alternativeDiscount){
                            System.out.println("Creation secondDiscount ..");
                            GoodSet secondDiscount = createGoodSet(in);
                            discount = new Discount(firstDiscount,secondDiscount,alternativeDiscount);
                        }
                        else{
                            discount = new Discount(firstDiscount,null,alternativeDiscount);
                        }

                        ActionEffect actionEffect = new ActionEffect(actionType2,actionValue,discount);
                        effectlist.add(actionEffect);
                        break;
                }
            }
            if(answer.equals("n")){
                return effectlist;
            }
        }

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
    }*/
}
