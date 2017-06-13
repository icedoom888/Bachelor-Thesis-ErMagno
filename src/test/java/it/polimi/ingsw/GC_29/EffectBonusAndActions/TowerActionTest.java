package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import org.testng.annotations.Test;
import it.polimi.ingsw.GC_29.Player.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lorenzotara on 22/05/17.

public class TowerActionTest {

    @Test
    public void testIsPossible1() throws Exception {

        BonusTile bonusTile = new BonusTile(new ObtainEffect(1,0,0,0,0,0,0),new ObtainEffect(0,1,0,0,0,0,0));


        PersonalBoard personalBoard1 = new PersonalBoard(bonusTile,6);

        Player player1 = new Player("Player1", PlayerColor.BLUE, personalBoard1, null);

        Player player2 = new Player("Player1", PlayerColor.RED, personalBoard1, null);

        ArrayList<Player> players = new ArrayList<Player>();

        players.add(player1);

        players.add(player2);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

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

        ExcommunicationTile tile_1 = new ExcommunicationTile(Era.FIRST,"sei",null,null, null,"777");
        ExcommunicationTile tile_2 = new ExcommunicationTile(Era.SECOND,"un",null,null,null,"su ogni");
        ExcommunicationTile tile_3 = new ExcommunicationTile(Era.THIRD,"bufu",null,null,null,"cosa");

        GameBoard gameBoard = GameStatus.getInstance().getGameBoard();

        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(3).setDevelopmentCard(blueCard);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile_1, tile_2, tile_3);

        player1.getActualGoodSet().addGoodSet(new GoodSet(10,10,10,10,10,10,10));
        TowerAction towerAction = new TowerAction(ZoneType.BLUETOWER, 3, gameBoard.getTower(ZoneType.BLUETOWER));

        towerAction.setFamiliyPawn(familyPawnSelected);
        towerAction.setPlayer(player1);
        System.out.println(towerAction.isPossible());

        System.out.println("The player has to pay: \n" +
                "cost of the tower: " + towerAction.getTowerCost() + "\n" +
                "cost of the card: " + towerAction.getCardSelected().getCardCost() + "\n\n" +
                "The player has in his actual goodset: " + towerAction.getPlayer().getActualGoodSet().toString() +"\n" +
                "The value of the action is: " + towerAction.getActionSpaceSelected().getActionCost() + "\n" +
                "The value of the pawnSelected is: " + towerAction.getTemporaryPawn().getActualValue() + "\n" +
                "The number of workers of the player is: " + player1.getActualGoodSet().getGoodAmount(GoodType.WORKERS));

        System.out.println("Workers to pay: " + towerAction.getWorkers());
        System.out.println("Value of the pawn after BM: " + towerAction.getTemporaryPawn().getActualValue());
        System.out.println(towerAction.getPossibleCardCosts());
        System.out.println("cost of the card: " + towerAction.getCardSelected().getCardCost());


        return;
    }

    @Test
    public void testIsPossible2() throws Exception {


        BonusTile bonusTile = new BonusTile(new ObtainEffect(1,0,0,0,0,0,0),new ObtainEffect(0,1,0,0,0,0,0));


        PersonalBoard personalBoard1 = new PersonalBoard(bonusTile,6);

        Player player1 = new Player("Player1", PlayerColor.BLUE, personalBoard1, null);

        Player player2 = new Player("Player1", PlayerColor.RED, personalBoard1, null);

        ArrayList<Player> players = new ArrayList<Player>();

        players.add(player1);

        players.add(player2);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

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

        ExcommunicationTile tile_1 = new ExcommunicationTile(Era.FIRST,"sei",null,null, null,"777");
        ExcommunicationTile tile_2 = new ExcommunicationTile(Era.SECOND,"un",null,null,null,"su ogni");
        ExcommunicationTile tile_3 = new ExcommunicationTile(Era.THIRD,"bufu",null,null,null,"cosa");

        GameBoard gameBoard = GameStatus.getInstance().getGameBoard();

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile_1, tile_2, tile_3);

        player1.getActualGoodSet().addGoodSet(new GoodSet(10,10,10,10,10,10,10));
        TowerAction towerAction = new TowerAction(ZoneType.BLUETOWER, 3, gameBoard.getTower(ZoneType.BLUETOWER));
        towerAction.setFamiliyPawn(familyPawnSelected);
        towerAction.setPlayer(player1);

        System.out.println(towerAction.isPossible());

        return;
    }

    @Test
    public void testIsPossible3() throws Exception {

        BonusTile bonusTile = new BonusTile(new ObtainEffect(1,0,0,0,0,0,0),new ObtainEffect(0,1,0,0,0,0,0));


        PersonalBoard personalBoard1 = new PersonalBoard(bonusTile,6);

        Player player1 = new Player("Player1", PlayerColor.BLUE, personalBoard1, null);

        Player player2 = new Player("Player1", PlayerColor.RED, personalBoard1, null);

        ArrayList<Player> players = new ArrayList<Player>();

        players.add(player1);

        players.add(player2);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

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

        ExcommunicationTile tile_1 = new ExcommunicationTile(Era.FIRST,"sei",null,null, null,"777");
        ExcommunicationTile tile_2 = new ExcommunicationTile(Era.SECOND,"un",null,null,null,"su ogni");
        ExcommunicationTile tile_3 = new ExcommunicationTile(Era.THIRD,"bufu",null,null,null,"cosa");

        GameBoard gameBoard = GameStatus.getInstance().getGameBoard();

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile_1, tile_2, tile_3);
        player1.getActualGoodSet().addGoodSet(new GoodSet(10,10,10,0,10,10,10));

        immediateEffectsBlueCard.add(purpleSix);
        immediateEffectsBlueCard.add(councilPrivilegeEffect);
        immediateEffectsBlueCard.add(bonusEffect1);
        permanentEffectsBlueCard.add(bonusEffect2);

        player1.getBonusAndMalusOnCost().add(bonusAndMalusOnCost1);
        player1.getBonusAndMalusOnAction().add(bonusAndMalusOnAction1);

        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(3).setDevelopmentCard(blueCard);
        //gameBoard.getTower(ZoneType.BLUETOWER)

        TowerAction towerAction = new TowerAction(ZoneType.BLUETOWER, 3, gameBoard.getTower(ZoneType.BLUETOWER));
        towerAction.setFamiliyPawn(familyPawnSelected);
        towerAction.setPlayer(player1);

        System.out.println(towerAction.isPossible());

        return;
    }

    @Test
    public void testUpdate() throws Exception {

    }


    @Test
    public void testExecute() throws Exception {


        BonusTile bonusTile = new BonusTile(new ObtainEffect(1,0,0,0,0,0,0),new ObtainEffect(0,1,0,0,0,0,0));


        PersonalBoard personalBoard1 = new PersonalBoard(bonusTile,6);

        Player player1 = new Player("Player1", PlayerColor.BLUE, personalBoard1, null);

        Player player2 = new Player("Player1", PlayerColor.RED, personalBoard1, null);

        ArrayList<Player> players = new ArrayList<Player>();

        players.add(player1);

        players.add(player2);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

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

        ExcommunicationTile tile_1 = new ExcommunicationTile(Era.FIRST,"sei",null,null, null,"777");
        ExcommunicationTile tile_2 = new ExcommunicationTile(Era.SECOND,"un",null,null,null,"su ogni");
        ExcommunicationTile tile_3 = new ExcommunicationTile(Era.THIRD,"bufu",null,null,null,"cosa");

        GameBoard gameBoard = GameStatus.getInstance().getGameBoard();

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile_1, tile_2, tile_3);
        player1.getActualGoodSet().addGoodSet(new GoodSet(10,10,10,10,10,10,10));

        immediateEffectsBlueCard.add(purpleSix);
        immediateEffectsBlueCard.add(councilPrivilegeEffect);
        immediateEffectsBlueCard.add(bonusEffect1);
        permanentEffectsBlueCard.add(bonusEffect2);

        player1.getBonusAndMalusOnCost().add(bonusAndMalusOnCost1);
        player1.getBonusAndMalusOnAction().add(bonusAndMalusOnAction1);

        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(3).setDevelopmentCard(blueCard);
        //gameBoard.getTower(ZoneType.BLUETOWER)

        TowerAction towerAction = new TowerAction(ZoneType.BLUETOWER, 3, gameBoard.getTower(ZoneType.BLUETOWER));

        towerAction.setFamiliyPawn(familyPawnSelected);
        towerAction.setPlayer(player1);

        System.out.println(towerAction.isPossible());


        System.out.println("The player has to pay: \n" +
                "cost of the tower: " + towerAction.getTowerCost() + "\n" +
                "cost of the card: " + towerAction.getCardSelected().getCardCost() + "\n\n" +
                "The player has in his actual goodset: " + towerAction.getPlayer().getActualGoodSet().toString() +"\n" +
                "The value of the action is: " + towerAction.getActionSpaceSelected().getActionCost() + "\n" +
                "The value of the pawnSelected is: " + towerAction.getTemporaryPawn().getActualValue() + "\n" +
                "The number of workers of the player is: " + player1.getActualGoodSet().getGoodAmount(GoodType.WORKERS));

        System.out.println("Workers to pay: " + towerAction.getWorkers());
        System.out.println("Value of the pawn after BM: " + towerAction.getTemporaryPawn().getActualValue());
        System.out.println(towerAction.getPossibleCardCosts());

        GameStatus.getInstance().setCurrentAction(towerAction);

        towerAction.execute();
        towerAction.update();

    }

}*/