package it.polimi.ingsw.GC_29.EffectBonusAndActions;

/**
 * Created by Lorenzotara on 22/05/17.

public class TowerActionTest {

    @Test
    public void testIsPossible1() throws Exception {

        // Creation of the gameboard

        int numberOfPlayers1 = 4;
        ExcommunicationTile tile_1 = new ExcommunicationTile(Era.FIRST,"sei",null,null, null,"777");
        ExcommunicationTile tile_2 = new ExcommunicationTile(Era.SECOND,"un",null,null,null,"su ogni");
        ExcommunicationTile tile_3 = new ExcommunicationTile(Era.THIRD,"bufu",null,null,null,"cosa");
        GameBoard gameBoard = new GameBoard(numberOfPlayers1);


        // Creation of personalBoards

        BonusTile bonusTile = new BonusTile(new ObtainEffect(1,0,0,0,0,0,0),new ObtainEffect(0,1,0,0,0,0,0));
        PersonalBoard personalBoard1 = new PersonalBoard(bonusTile,6);
        PersonalBoard personalBoard2 = new PersonalBoard(bonusTile, 6);



        // Creation of playerStatuses

        Player playerStatus1 = new Player(PlayerColor.BLUE, personalBoard1);
        Player player1 = new Player("Player1", PlayerColor.BLUE, gameBoard, personalBoard1, playerStatus1);

        Player playerStatus2 = new Player(PlayerColor.GREEN, personalBoard2);
        Player player2 = new Player("Player2", PlayerColor.GREEN, gameBoard, personalBoard2, playerStatus2);



        // Creation of the components

        FamilyPawn familyPawnSelected = new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 3);

        ArrayList<Effect> immediateEffectsBlueCard = new ArrayList<>();
        ActionEffect purpleSix = new ActionEffect(ZoneType.PURPLETOWER, 6);
        CouncilPrivilegeEffect councilPrivilegeEffect = new CouncilPrivilegeEffect(1);

        BonusAndMalusOnAction bonusAndMalusOnAction1 = new BonusAndMalusOnAction(ZoneType.BLUETOWER, 2);
        BonusAndMalusOnCost bonusAndMalusOnCost1 = new BonusAndMalusOnCost(ZoneType.BLUETOWER, new GoodSet(1,1,1,1,1,1,1), new GoodSet(), false);
        BonusAndMalusOnGoods bonusAndMalusOnGoods1 = new BonusAndMalusOnGoods(new GoodSet(1,2,3,4,5,6,7));

        BonusEffect bonusEffect1 = new BonusEffect(bonusAndMalusOnAction1, bonusAndMalusOnGoods1, bonusAndMalusOnCost1);



        ArrayList<Effect> permanentEffectsBlueCard = new ArrayList<>();
        BonusAndMalusOnAction bonusAndMalusOnAction2 = new BonusAndMalusOnAction(ZoneType.BLUETOWER, 2);
        BonusAndMalusOnCost bonusAndMalusOnCost2 = new BonusAndMalusOnCost(ZoneType.BLUETOWER, new GoodSet(1,0,0,0,0,0,0), new GoodSet(0,1,0,0,0,0,0), true);
        BonusEffect bonusEffect2 = new BonusEffect(bonusAndMalusOnAction2, null, bonusAndMalusOnCost2);


        DevelopmentCard blueCard = new DevelopmentCard("Costruttore Fake",
                Era.FIRST,
                new CardCost(true, true,
                        new Cost(new GoodSet(4,0,0,0,0,0,0), new GoodSet(1,1,1,1,1,1,1)),
                        new Cost(new GoodSet(0,0,4,0,0,0,0), new GoodSet(1,1,1,1,1,1,1))),
                CardColor.BLUE,
                immediateEffectsBlueCard,
                permanentEffectsBlueCard,
                false,
                0);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile_1, tile_2, tile_3);
        playerStatus1.getActualGoodSet().addGoodSet(new GoodSet(10,10,10,10,10,10,10));

        immediateEffectsBlueCard.add(purpleSix);
        immediateEffectsBlueCard.add(councilPrivilegeEffect);
        immediateEffectsBlueCard.add(bonusEffect1);
        permanentEffectsBlueCard.add(bonusEffect2);

        playerStatus1.getBonusAndMalusOnCost().add(bonusAndMalusOnCost1);
        playerStatus1.getBonusAndMalusOnAction().add(bonusAndMalusOnAction1);

        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(3).setDevelopmentCard(blueCard);
        //gameBoard.getTower(ZoneType.BLUETOWER)

        TowerAction towerAction = new TowerAction(familyPawnSelected, ZoneType.BLUETOWER, playerStatus1, 3, gameBoard.getTower(ZoneType.BLUETOWER));

        System.out.println("The player has to pay: \n" +
                "cost of the tower: " + towerAction.getTowerCost() + "\n" +
                "cost of the card: " + towerAction.getCardSelected().getCardCost() + "\n\n" +
                "The player has in his actual goodset: " + towerAction.getPlayer().getActualGoodSet().toString() +"\n" +
                "The value of the action is: " + towerAction.getActionSpaceSelected().getActionCost() + "\n" +
                "The value of the pawnSelected is: " + towerAction.getTemporaryPawn().getActualValue() + "\n" +
                "The number of workers of the player is: " + playerStatus1.getActualGoodSet().getGoodAmount(GoodType.WORKERS));

        System.out.println(towerAction.isPossible());
        System.out.println("Workers to pay: " + towerAction.getWorkers());
        System.out.println("Value of the pawn after BM: " + towerAction.getTemporaryPawn().getActualValue());
        System.out.println(towerAction.getPossibleCardCosts());
        System.out.println("cost of the card: " + towerAction.getCardSelected().getCardCost());


        return;
    }

    @Test
    public void testIsPossible2() throws Exception {

        // Creation of the gameboard

        int numberOfPlayers1 = 4;
        ExcommunicationTile tile_1 = new ExcommunicationTile(Era.FIRST,"sei",null,null, null,"777");
        ExcommunicationTile tile_2 = new ExcommunicationTile(Era.SECOND,"un",null,null,null,"su ogni");
        ExcommunicationTile tile_3 = new ExcommunicationTile(Era.THIRD,"bufu",null,null,null,"cosa");
        GameBoard gameBoard = new GameBoard(numberOfPlayers1);


        // Creation of personalBoards

        BonusTile bonusTile = new BonusTile(new ObtainEffect(1,0,0,0,0,0,0),new ObtainEffect(0,1,0,0,0,0,0));
        PersonalBoard personalBoard1 = new PersonalBoard(bonusTile,6);
        PersonalBoard personalBoard2 = new PersonalBoard(bonusTile, 6);



        // Creation of playerStatuses

        Player playerStatus1 = new Player(PlayerColor.BLUE, personalBoard1);
        Player player1 = new Player("Player1", PlayerColor.BLUE, gameBoard, personalBoard1, playerStatus1);

        Player playerStatus2 = new Player(PlayerColor.GREEN, personalBoard2);
        Player player2 = new Player("Player2", PlayerColor.GREEN, gameBoard, personalBoard2, playerStatus2);



        // Creation of the components

        FamilyPawn familyPawnSelected = new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 0);

        ArrayList<Effect> immediateEffectsBlueCard = new ArrayList<>();
        ActionEffect purpleSix = new ActionEffect(ZoneType.PURPLETOWER, 6);
        CouncilPrivilegeEffect councilPrivilegeEffect = new CouncilPrivilegeEffect(1);

        BonusAndMalusOnAction bonusAndMalusOnAction1 = new BonusAndMalusOnAction(ZoneType.BLUETOWER, 2);
        BonusAndMalusOnCost bonusAndMalusOnCost1 = new BonusAndMalusOnCost(ZoneType.BLUETOWER, new GoodSet(1,1,1,1,1,1,1), new GoodSet(), false);
        BonusAndMalusOnGoods bonusAndMalusOnGoods1 = new BonusAndMalusOnGoods(new GoodSet(1,2,3,4,5,6,7));

        BonusEffect bonusEffect1 = new BonusEffect(bonusAndMalusOnAction1, bonusAndMalusOnGoods1, bonusAndMalusOnCost1);



        ArrayList<Effect> permanentEffectsBlueCard = new ArrayList<>();
        BonusAndMalusOnAction bonusAndMalusOnAction2 = new BonusAndMalusOnAction(ZoneType.BLUETOWER, 2);
        BonusAndMalusOnCost bonusAndMalusOnCost2 = new BonusAndMalusOnCost(ZoneType.BLUETOWER, new GoodSet(1,0,0,0,0,0,0), new GoodSet(0,1,0,0,0,0,0), true);
        BonusEffect bonusEffect2 = new BonusEffect(bonusAndMalusOnAction2, null, bonusAndMalusOnCost2);


        DevelopmentCard blueCard = new DevelopmentCard("Costruttore Fake",
                Era.FIRST,
                new CardCost(true, true,
                        new Cost(new GoodSet(4,0,0,0,0,0,0), new GoodSet(1,1,1,1,1,1,1)),
                        new Cost(new GoodSet(0,0,4,0,0,0,0), new GoodSet(1,1,1,1,1,1,1))),
                CardColor.BLUE,
                immediateEffectsBlueCard,
                permanentEffectsBlueCard,
                false,
                0);

        immediateEffectsBlueCard.add(purpleSix);
        immediateEffectsBlueCard.add(councilPrivilegeEffect);
        immediateEffectsBlueCard.add(bonusEffect1);
        permanentEffectsBlueCard.add(bonusEffect2);

        playerStatus1.getBonusAndMalusOnCost().add(bonusAndMalusOnCost1);
        playerStatus1.getBonusAndMalusOnAction().add(bonusAndMalusOnAction1);
        playerStatus1.getActualGoodSet().addGoodSet(new GoodSet(10,10,10,10,10,10,10));


        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(3).setDevelopmentCard(blueCard);

        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(1).getActionSpace().addPawn(new FamilyPawn(PlayerColor.GREEN, FamilyPawnType.BLACK, 6));
        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(2).getActionSpace().addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 6));


        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(3).setDevelopmentCard(blueCard);
        //gameBoard.getTower(ZoneType.BLUETOWER)

        TowerAction towerAction = new TowerAction(familyPawnSelected, ZoneType.BLUETOWER, playerStatus1, 3, gameBoard.getTower(ZoneType.BLUETOWER));

        System.out.println("The player has to pay: \n" +
                "cost of the tower: " + towerAction.getTowerCost() + "\n" +
                "cost of the card: " + towerAction.getCardSelected().getCardCost() + "\n\n" +
                "The player has in his actual goodset: " + towerAction.getPlayer().getActualGoodSet().toString() +"\n" +
                "The value of the action is: " + towerAction.getActionSpaceSelected().getActionCost() + "\n" +
                "The value of the pawnSelected is: " + towerAction.getTemporaryPawn().getActualValue() + "\n" +
                "The number of workers of the player is: " + playerStatus1.getActualGoodSet().getGoodAmount(GoodType.WORKERS));

        System.out.println(towerAction.isPossible());
        System.out.println("Workers to pay: " + towerAction.getWorkers());
        System.out.println("Value of the pawn after BM: " + towerAction.getTemporaryPawn().getActualValue());
        System.out.println(towerAction.getPossibleCardCosts());

        return;
    }

    @Test
    public void testIsPossible3() {
        // Creation of the gameboard

        int numberOfPlayers1 = 4;
        ExcommunicationTile tile_1 = new ExcommunicationTile(Era.FIRST,"sei",null,null, null,"777");
        ExcommunicationTile tile_2 = new ExcommunicationTile(Era.SECOND,"un",null,null,null,"su ogni");
        ExcommunicationTile tile_3 = new ExcommunicationTile(Era.THIRD,"bufu",null,null,null,"cosa");
        GameBoard gameBoard = new GameBoard(numberOfPlayers1);


        // Creation of personalBoards

        BonusTile bonusTile = new BonusTile(new ObtainEffect(1,0,0,0,0,0,0),new ObtainEffect(0,1,0,0,0,0,0));
        PersonalBoard personalBoard1 = new PersonalBoard(bonusTile,6);
        PersonalBoard personalBoard2 = new PersonalBoard(bonusTile, 6);



        // Creation of playerStatuses

        Player playerStatus1 = new Player(PlayerColor.BLUE, personalBoard1);
        Player player1 = new Player("Player1", PlayerColor.BLUE, gameBoard, personalBoard1, playerStatus1);

        Player playerStatus2 = new Player(PlayerColor.GREEN, personalBoard2);
        Player player2 = new Player("Player2", PlayerColor.GREEN, gameBoard, personalBoard2, playerStatus2);



        // Creation of the components

        FamilyPawn familyPawnSelected = new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 3);

        ArrayList<Effect> immediateEffectsBlueCard = new ArrayList<>();
        ActionEffect purpleSix = new ActionEffect(ZoneType.PURPLETOWER, 6);
        CouncilPrivilegeEffect councilPrivilegeEffect = new CouncilPrivilegeEffect(1);

        BonusAndMalusOnAction bonusAndMalusOnAction1 = new BonusAndMalusOnAction(ZoneType.BLUETOWER, 2);
        BonusAndMalusOnCost bonusAndMalusOnCost1 = new BonusAndMalusOnCost(ZoneType.BLUETOWER, new GoodSet(1,1,1,1,1,1,1), new GoodSet(), false);
        BonusAndMalusOnGoods bonusAndMalusOnGoods1 = new BonusAndMalusOnGoods(new GoodSet(1,2,3,4,5,6,7));

        BonusEffect bonusEffect1 = new BonusEffect(bonusAndMalusOnAction1, bonusAndMalusOnGoods1, bonusAndMalusOnCost1);



        ArrayList<Effect> permanentEffectsBlueCard = new ArrayList<>();
        BonusAndMalusOnAction bonusAndMalusOnAction2 = new BonusAndMalusOnAction(ZoneType.BLUETOWER, 2);
        BonusAndMalusOnCost bonusAndMalusOnCost2 = new BonusAndMalusOnCost(ZoneType.BLUETOWER, new GoodSet(1,0,0,0,0,0,0), new GoodSet(0,1,0,0,0,0,0), true);
        BonusEffect bonusEffect2 = new BonusEffect(bonusAndMalusOnAction2, null, bonusAndMalusOnCost2);


        DevelopmentCard blueCard = new DevelopmentCard("Costruttore Fake",
                Era.FIRST,
                new CardCost(true, true,
                        new Cost(new GoodSet(4,0,0,0,0,0,0), new GoodSet(1,1,1,1,1,1,1)),
                        new Cost(new GoodSet(0,0,4,0,0,0,0), new GoodSet(1,1,1,1,1,1,1))),
                CardColor.BLUE,
                immediateEffectsBlueCard,
                permanentEffectsBlueCard,
                false,
                0);

        immediateEffectsBlueCard.add(purpleSix);
        immediateEffectsBlueCard.add(councilPrivilegeEffect);
        immediateEffectsBlueCard.add(bonusEffect1);
        permanentEffectsBlueCard.add(bonusEffect2);

        playerStatus1.getBonusAndMalusOnCost().add(bonusAndMalusOnCost1);
        playerStatus1.getBonusAndMalusOnAction().add(bonusAndMalusOnAction1);
        playerStatus1.getActualGoodSet().addGoodSet(new GoodSet(10,10,10,0,10,10,10));


        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(3).setDevelopmentCard(blueCard);

        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(1).getActionSpace().addPawn(new FamilyPawn(PlayerColor.GREEN, FamilyPawnType.BLACK, 6));
        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(2).getActionSpace().addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 6));

        immediateEffectsBlueCard.add(purpleSix);
        immediateEffectsBlueCard.add(councilPrivilegeEffect);
        immediateEffectsBlueCard.add(bonusEffect1);
        permanentEffectsBlueCard.add(bonusEffect2);

        playerStatus1.getBonusAndMalusOnCost().add(bonusAndMalusOnCost1);
        playerStatus1.getBonusAndMalusOnAction().add(bonusAndMalusOnAction1);

        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(3).setDevelopmentCard(blueCard);
        //gameBoard.getTower(ZoneType.BLUETOWER)

        TowerAction towerAction = new TowerAction(familyPawnSelected, ZoneType.BLUETOWER, playerStatus1, 3, gameBoard.getTower(ZoneType.BLUETOWER));

        System.out.println("The player has to pay: \n" +
                "cost of the tower: " + towerAction.getTowerCost() + "\n" +
                "cost of the card: " + towerAction.getCardSelected().getCardCost() + "\n\n" +
                "The player has in his actual goodset: " + towerAction.getPlayer().getActualGoodSet().toString() +"\n" +
                "The value of the action is: " + towerAction.getActionSpaceSelected().getActionCost() + "\n" +
                "The value of the pawnSelected is: " + towerAction.getTemporaryPawn().getActualValue() + "\n" +
                "The number of workers of the player is: " + playerStatus1.getActualGoodSet().getGoodAmount(GoodType.WORKERS));

        System.out.println(towerAction.isPossible());
        System.out.println("Workers to pay: " + towerAction.getWorkers());
        System.out.println("Value of the pawn after BM: " + towerAction.getTemporaryPawn().getActualValue());
        System.out.println(towerAction.getPossibleCardCosts());

        return;
    }

    @Test
    public void testUpdate() throws Exception {

    }

    /*@Test
    public void testExecute() throws Exception {

        // Creation of the gameboard

        int numberOfPlayers1 = 4;
        ExcommunicationTile tile_1 = new ExcommunicationTile(Era.FIRST,"sei",null,null,"777");
        ExcommunicationTile tile_2 = new ExcommunicationTile(Era.SECOND,"un",null,null,"su ogni");
        ExcommunicationTile tile_3 = new ExcommunicationTile(Era.THIRD,"bufu",null,null,"cosa");
        GameBoard gameBoard = new GameBoard(numberOfPlayers1);


        // Creation of personalBoards

        BonusTile bonusTile = new BonusTile(new ObtainEffect(1,0,0,0,0,0,0),new ObtainEffect(0,1,0,0,0,0,0));
        PersonalBoard personalBoard1 = new PersonalBoard(bonusTile,6);
        PersonalBoard personalBoard2 = new PersonalBoard(bonusTile, 6);



        // Creation of playerStatuses

        Player playerStatus1 = new Player(PlayerColor.BLUE, personalBoard1);
        Player player1 = new Player("Player1", PlayerColor.BLUE, gameBoard, personalBoard1, playerStatus1);

        Player playerStatus2 = new Player(PlayerColor.GREEN, personalBoard2);
        Player player2 = new Player("Player2", PlayerColor.GREEN, gameBoard, personalBoard2, playerStatus2);



        // Creation of the components

        FamilyPawn familyPawnSelected = new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 3);

        ArrayList<Effect> immediateEffectsBlueCard = new ArrayList<Effect>();
        ActionEffect purpleSix = new ActionEffect(ZoneType.PURPLETOWER, 6);
        CouncilPrivilegeEffect councilPrivilegeEffect = new CouncilPrivilegeEffect(1);

        BonusAndMalusOnAction bonusAndMalusOnAction1 = new BonusAndMalusOnAction(ZoneType.BLUETOWER, 2);
        BonusAndMalusOnCost bonusAndMalusOnCost1 = new BonusAndMalusOnCost(ZoneType.BLUETOWER, new GoodSet(1,1,1,1,1,1,1), new GoodSet(), false);
        BonusAndMalusOnGoods bonusAndMalusOnGoods1 = new BonusAndMalusOnGoods(new GoodSet(1,2,3,4,5,6,7));

        BonusEffect bonusEffect1 = new BonusEffect(bonusAndMalusOnAction1, bonusAndMalusOnGoods1, bonusAndMalusOnCost1);



        ArrayList<Effect> permanentEffectsBlueCard = new ArrayList<Effect>();
        BonusAndMalusOnAction bonusAndMalusOnAction2 = new BonusAndMalusOnAction(ZoneType.BLUETOWER, 2);
        BonusAndMalusOnCost bonusAndMalusOnCost2 = new BonusAndMalusOnCost(ZoneType.BLUETOWER, new GoodSet(1,0,0,0,0,0,0), new GoodSet(0,1,0,0,0,0,0), true);
        BonusEffect bonusEffect2 = new BonusEffect(bonusAndMalusOnAction2, null, bonusAndMalusOnCost2);


        DevelopmentCard blueCard = new DevelopmentCard("Costruttore Fake",
                Era.FIRST,
                new CardCost(true, true,
                        new Cost(new GoodSet(4,0,0,0,0,0,0), new GoodSet(1,1,1,1,1,1,1)),
                        new Cost(new GoodSet(0,0,4,0,0,0,0), new GoodSet(1,1,1,1,1,1,1))),
                CardColor.BLUE,
                immediateEffectsBlueCard,
                permanentEffectsBlueCard,
                false,
                0);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile_1, tile_2, tile_3);
        playerStatus1.getActualGoodSet().addGoodSet(new GoodSet(10,10,10,10,10,10,10));

        immediateEffectsBlueCard.add(purpleSix);
        immediateEffectsBlueCard.add(councilPrivilegeEffect);
        immediateEffectsBlueCard.add(bonusEffect1);
        permanentEffectsBlueCard.add(bonusEffect2);

        playerStatus1.getBonusAndMalusOnCost().add(bonusAndMalusOnCost1);
        playerStatus1.getBonusAndMalusOnAction().add(bonusAndMalusOnAction1);

        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(3).setDevelopmentCard(blueCard);
        //gameBoard.getTower(ZoneType.BLUETOWER)

        TowerAction towerAction = new TowerAction(familyPawnSelected, ZoneType.BLUETOWER, playerStatus1, 3, gameBoard.getTower(ZoneType.BLUETOWER));

        System.out.println("The player has to pay: \n" +
                "cost of the tower: " + towerAction.getTowerCost() + "\n" +
                "cost of the card: " + towerAction.getCardSelected().getCardCost() + "\n\n" +
                "The player has in his actual goodset: " + towerAction.getPlayer().getActualGoodSet().toString() +"\n" +
                "The value of the action is: " + towerAction.getActionSpaceSelected().getActionCost() + "\n" +
                "The value of the pawnSelected is: " + towerAction.getTemporaryPawn().getActualValue() + "\n" +
                "The number of workers of the player is: " + playerStatus1.getActualGoodSet().getGoodAmount(GoodType.WORKERS));

        System.out.println(towerAction.isPossible());
        System.out.println("Workers to pay: " + towerAction.getWorkers());
        System.out.println("Value of the pawn after BM: " + towerAction.getTemporaryPawn().getActualValue());
        System.out.println(towerAction.getPossibleCardCosts());

        towerAction.execute();
        towerAction.update();


    }

}*/