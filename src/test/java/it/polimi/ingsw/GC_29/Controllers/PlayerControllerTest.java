package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 30/05/2017.

public class PlayerControllerTest {

    @Test
    public void testInitCheck() throws Exception {

        ArrayList<Player> playerArrayList = new ArrayList<>();

        BonusTile bonusTile = new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet()) );

        Player player = new Player("Lorenzo", PlayerColor.BLUE, new PersonalBoard(bonusTile, 6), null);
        player.updateGoodSet(new GoodSet(100,100,100,100,100,100,100));

        Player player2 = new Player("Alberto", PlayerColor.BLUE, new PersonalBoard(bonusTile, 6), null);
        player.updateGoodSet(new GoodSet(100,100,100,100,100,100,100));

        playerArrayList.add(player);
        playerArrayList.add(player2);

        GameSetup gameSetup = new GameSetup(playerArrayList);

        gameSetup.init();

        ArrayList<Effect> immediateEffect = new ArrayList<>();

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        immediateEffect.add(new ActionEffect(ZoneType.ANYTOWER, 6));

        DevelopmentCard testCard = new DevelopmentCard("test", Era.FIRST, new CardCost(), CardColor.GREEN, immediateEffect, new ArrayList<Effect>() , false, 0);

        DevelopmentCard testCard1 = new DevelopmentCard("test2", Era.FIRST, new CardCost(), CardColor.GREEN, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0);

        DevelopmentCard testCard2 = new DevelopmentCard("test2", Era.FIRST, new CardCost(), CardColor.YELLOW, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0);

        DevelopmentCard testCard3 = new DevelopmentCard("test2", Era.FIRST, new CardCost(), CardColor.BLUE, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0);

        DevelopmentCard testCard4 = new DevelopmentCard("test2", Era.FIRST, new CardCost(), CardColor.PURPLE, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0);



        Model.getInstance().getGameBoard().getTower(ZoneType.GREENTOWER).getFloor(0).setDevelopmentCard(testCard);
        Model.getInstance().getGameBoard().getTower(ZoneType.GREENTOWER).getFloor(1).setDevelopmentCard(testCard1);
        Model.getInstance().getGameBoard().getTower(ZoneType.YELLOWTOWER).getFloor(1).setDevelopmentCard(testCard1);
        Model.getInstance().getGameBoard().getTower(ZoneType.BLUETOWER).getFloor(1).setDevelopmentCard(testCard1);
        Model.getInstance().getGameBoard().getTower(ZoneType.PURPLETOWER).getFloor(1).setDevelopmentCard(testCard1);


        Model.getInstance().setCurrentPlayer(player);

        PlayerController testController = new PlayerController();

        testController.init();

    }

  /*  @Test
    public void testInit() throws Exception {

        PersonalBoard testPersonalBoard = new PersonalBoard(new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet())), 6);

        Player currentPlayer = new Player(null, null, testPersonalBoard);

        currentPlayer.updateGoodSet(new GoodSet(100,100,100,100,100,100,100));

        DevelopmentCard testCard = new DevelopmentCard("test", Era.FIRST, new CardCost(), CardColor.GREEN, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0);

        Tower testTower = new Tower(ZoneType.GREENTOWER);

        testTower.getFloor(0).setDevelopmentCard(testCard);

        ExcommunicationTile tile1 = new ExcommunicationTile(Era.FIRST, "name1", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), null,"descriptiom1");
        ExcommunicationTile tile2 = new ExcommunicationTile(Era.FIRST, "name2", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), null,"descriptiom2");
        ExcommunicationTile tile3 = new ExcommunicationTile(Era.FIRST, "name3", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), null,"descriptiom3");

        GameBoard gameBoard = new GameBoard(4);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile1, tile2, tile3);

        Model.getInstance().setGameBoard(gameBoard);

        Model.getInstance().getGameBoard().getTowerMap().put(ZoneType.GREENTOWER, testTower);

        Model.getInstance().setCurrentPlayer(currentPlayer);

        PlayerController testController = new PlayerController();

        testController.init();

        //FactoryAction.resetFloor();

    }


    @Test
    public void testInit1() throws Exception {

        PersonalBoard testPersonalBoard = new PersonalBoard(new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet())), 6);

        Player currentPlayer = new Player(null, null, testPersonalBoard);

        currentPlayer.updateGoodSet(new GoodSet(100,100,100,100,100,100,100));

        currentPlayer.setPersonalBoard(testPersonalBoard);

        ArrayList<Effect> immediateEffect = new ArrayList<>();

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        DevelopmentCard testCard = new DevelopmentCard("test", Era.FIRST, new CardCost(), CardColor.GREEN, immediateEffect, new ArrayList<Effect>() , false, 0);

        DevelopmentCard testCard1 = new DevelopmentCard("test2", Era.FIRST, new CardCost(), CardColor.GREEN, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0);

        Tower testTower = new Tower(ZoneType.GREENTOWER);

        testTower.getFloor(0).setDevelopmentCard(testCard);
        testTower.getFloor(1).setDevelopmentCard(testCard1);


        ExcommunicationTile tile1 = new ExcommunicationTile(Era.FIRST, "name1", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), null,"descriptiom1");
        ExcommunicationTile tile2 = new ExcommunicationTile(Era.FIRST, "name2", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), null,"descriptiom2");
        ExcommunicationTile tile3 = new ExcommunicationTile(Era.FIRST, "name3", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), null,"descriptiom3");

        GameBoard gameBoard = new GameBoard(4);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile1, tile2, tile3);

        Model.getInstance().setGameBoard(gameBoard);

        Model.getInstance().getGameBoard().getTowerMap().put(ZoneType.GREENTOWER, testTower);

        Model.getInstance().setCurrentPlayer(currentPlayer);

        PlayerController testController = new PlayerController();

        testController.init();

       // FactoryAction.resetFloor();
    }

    @Test
    public void testInit2() throws Exception {

        PersonalBoard testPersonalBoard = new PersonalBoard(new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet())), 6);

        Player currentPlayer = new Player(null, null, testPersonalBoard);currentPlayer.updateGoodSet(new GoodSet(100,100,100,100,100,100,100));

        currentPlayer.setPersonalBoard(testPersonalBoard);

        ArrayList<Effect> immediateEffect = new ArrayList<>();

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        DevelopmentCard testCard = new DevelopmentCard("test", Era.FIRST, new CardCost(), CardColor.GREEN, immediateEffect, new ArrayList<Effect>() , false, 0);

        DevelopmentCard testCard1 = new DevelopmentCard("test2", Era.FIRST, new CardCost(), CardColor.GREEN, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0);

        Tower testTower = new Tower(ZoneType.GREENTOWER);

        testTower.getFloor(0).setDevelopmentCard(testCard);
        testTower.getFloor(1).setDevelopmentCard(testCard1);


        ExcommunicationTile tile1 = new ExcommunicationTile(Era.FIRST, "name1", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), null,"descriptiom1");
        ExcommunicationTile tile2 = new ExcommunicationTile(Era.FIRST, "name2", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), null,"descriptiom2");
        ExcommunicationTile tile3 = new ExcommunicationTile(Era.FIRST, "name3", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), null,"descriptiom3");

        GameBoard gameBoard = new GameBoard(4);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile1, tile2, tile3);

        Model.getInstance().setGameBoard(gameBoard);

        Model.getInstance().getGameBoard().getTowerMap().put(ZoneType.GREENTOWER, testTower);

        Model.getInstance().setCurrentPlayer(currentPlayer);

        PlayerController testController = new PlayerController();

        testController.init();

      //  FactoryAction.resetFloor();
    }

}*/