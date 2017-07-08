package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Controllers.ObjectDeserializer;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 08/07/17.
 */
public class TowerActionTest {

    @Test
    public void testIsPossible() throws Exception {

        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("l", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("e", PlayerColor.GREEN, new PersonalBoard(6));
        Player player3 = new Player("d", PlayerColor.RED, new PersonalBoard(6));
        Player player4 = new Player("x", PlayerColor.YELLOW, new PersonalBoard(6));

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        GameSetup gameSetup = new GameSetup(players);
        gameSetup.init();
        gameSetup.setExcommunicationTiles();
        gameSetup.setLeaderCards();
        gameSetup.setGoodsForPlayers();
        Model model = gameSetup.getModel();
        Controller controller = null;
        try {
            controller = new Controller(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        controller.setCardsOnTowers();


        /**
         * Getting the tower
         */

        Tower greenTower = model.getGameBoard().getTower(ZoneType.GREENTOWER);

        /**
         * Setting the player ready to execute the action
         */

        for (FamilyPawnType familyPawnType : player1.getFamilyPawnAvailability().keySet()) {
            player1.getFamilyPawnAvailability().put(familyPawnType, true);
        }

        // every pawn has an actual value of 3
        for (FamilyPawn familyPawn : player1.getFamilyPawns()) {
            familyPawn.setActualValue(3);
        }


        ArrayList<DevelopmentCard> deck;

        FileReader cardFileReader = new FileReader("cards/greenCards");

        deck = new ObjectDeserializer().getCardDeck(cardFileReader);

        PersonalBoard player1Board = player1.getPersonalBoard();

        for (DevelopmentCard developmentCard : deck) {

            if (developmentCard.getSpecial().contentEquals("Foresta")) /*Forest - 5 */ {
                player1Board.getBuildingLane().addCard(developmentCard);
            }

            if (developmentCard.getSpecial().contentEquals("Monastero")) /*Stonemason's Shop - 6 */ {
                player1Board.getBuildingLane().addCard(developmentCard);

                //Monastero al terzo piano
                greenTower.getFloor(3).setDevelopmentCard(developmentCard);
            }
        }

        /**
         * Player1 has 3 workers, 5 coins, 2 woods and 2 stones. He already has two cards in his personalBoard,
         * the action will not be possible without at least 3 militaryPoints
         */

        /**
         * isPossible of TowerAction
         *
         * public boolean isPossible() {

         return super.isPossible()
         && !checkFamilyPresence()
         && isTowerAccessPossible()
         && laneAvailable()
         && checkSufficientGoodsForCard();
         }


         *
         * super.isPossible:

         return  enable
         && !isActionDenied()
         && checkActionSpaceAvailability()
         && checkSufficientActionValue()
         && checkFamilyPawn();

         */

        TowerAction towerActionGreen = new TowerAction(ZoneType.GREENTOWER, model, 3);

        towerActionGreen.setPlayer(player1);
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        /**
         * Testing action denied:
         */

        player1.getSpecialBonusAndMaluses().add(SpecialBonusAndMalus.NOGREENTOWER);
        assertFalse(towerActionGreen.isPossible());

        /**
         * reset
         */

        player1.getSpecialBonusAndMaluses().clear();
        towerActionGreen.resetExceptPlayer();
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));


        /**
         * Testing checkActionSpaceAvailability
         */
        greenTower.getFloor(3).getActionSpace().setOccupied(true);
        towerActionGreen = new TowerAction(ZoneType.GREENTOWER, model, 3);
        towerActionGreen.setPlayer(player1);
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        assertFalse(towerActionGreen.isPossible());

        /**
         * reset
         */
        greenTower.getFloor(3).getActionSpace().setOccupied(false);

        /**
         * checkSufficientActionValue
         * workers at 0, action value required 7, pawn value = 3
         */

        towerActionGreen = new TowerAction(ZoneType.GREENTOWER, model, 3);
        towerActionGreen.setPlayer(player1);
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        player1.updateGoodSet(new GoodSet(0,0,0, -player1.getActualGoodSet().getGoodAmount(GoodType.WORKERS),0,0,0));
        assertFalse(towerActionGreen.isPossible());

        /**
         * reset and setting enough workers to execute the action (10 workers to the player)
         */
        player1.updateGoodSet(new GoodSet(0,0,0,10,0,0,0));
        towerActionGreen.resetExceptPlayer();
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        /**
         * checkFamilyPawn
         */

        player1.getFamilyPawnAvailability().put(FamilyPawnType.BLACK, false);
        assertFalse(towerActionGreen.isPossible());

        /**
         * reset
         */
        player1.getFamilyPawnAvailability().put(FamilyPawnType.BLACK, true);
        towerActionGreen.resetExceptPlayer();
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        /**
         * checkFamilyPresence
         */
        greenTower.getFloor(1).getActionSpace().addPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.ORANGE)));

        towerActionGreen = new TowerAction(ZoneType.GREENTOWER, model, 3);
        towerActionGreen.setPlayer(player1);
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        assertFalse(towerActionGreen.isPossible());

        /**
         * reset
         */
        greenTower.getFloor(1).getActionSpace().removePawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.ORANGE)));


        /**
         * isTowerAccessPossible
         */
        greenTower.setOccupied(true);

        towerActionGreen = new TowerAction(ZoneType.GREENTOWER, model, 3);
        towerActionGreen.setPlayer(player1);
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        //No coins for the player to pay the tower
        player1.updateGoodSet(new GoodSet(0,0,-player1.getActualGoodSet().getGoodAmount(GoodType.COINS), 0,0,0,0));

        assertFalse(towerActionGreen.isPossible());

        /**
         * reset and setting enough coins to pay the tower
         */
        player1.updateGoodSet(new GoodSet(0,0,10,0,0,0,0));


        /**
         * laneAvailable, the player already has 2 green cards in his personal board
         * but he does not have any military point. Setting more military to make him possible to execute the action
         */
        player1.updateGoodSet(new GoodSet(0,0,0,0,0,10,0));

        /**
         * checkSufficientGoodsForCard
         */

        // the player need to have every resource with an amount of 3
        greenTower.getFloor(3).getDevelopmentCard().setCardCost
                (new CardCost(false, true, new Cost(new GoodSet(3,3,3,3,3,3,3), new GoodSet()), new Cost()));

        towerActionGreen = new TowerAction(ZoneType.GREENTOWER, model, 3);
        towerActionGreen.setPlayer(player1);
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        assertFalse(towerActionGreen.isPossible());

        towerActionGreen.resetExceptPlayer();
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        // now he has it
        player1.updateGoodSet(new GoodSet(3,3,3,3,3,3,3));

        greenTower.getFloor(3).getDevelopmentCard().setCardCost
                (new CardCost(false, true,
                        new Cost(new GoodSet(3,3,3,3,3,3,3),
                                new GoodSet(0,0,0,0,0,100,0)), new Cost()));

        towerActionGreen = new TowerAction(ZoneType.GREENTOWER, model, 3);
        towerActionGreen.setPlayer(player1);
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        // but now he needs 100 military points to take the card
        assertFalse(towerActionGreen.isPossible());

        towerActionGreen.resetExceptPlayer();
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        player1.updateGoodSet(new GoodSet(0,0,0,0,0,100,0));

        /**
         * finally
         */

        assertTrue(towerActionGreen.isPossible());
        assertTrue(towerActionGreen.getWorkers() == 4);

    }

    @Test
    public void testExecute() throws Exception {

        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("l", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("e", PlayerColor.GREEN, new PersonalBoard(6));
        Player player3 = new Player("d", PlayerColor.RED, new PersonalBoard(6));
        Player player4 = new Player("x", PlayerColor.YELLOW, new PersonalBoard(6));

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        GameSetup gameSetup = new GameSetup(players);
        gameSetup.init();
        gameSetup.setExcommunicationTiles();
        gameSetup.setLeaderCards();
        gameSetup.setGoodsForPlayers();
        Model model = gameSetup.getModel();
        Controller controller = null;
        try {
            controller = new Controller(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        controller.setCardsOnTowers();


        /**
         * Getting the tower
         */

        Tower greenTower = model.getGameBoard().getTower(ZoneType.GREENTOWER);

        /**
         * Setting the player ready to execute the action
         */

        for (FamilyPawnType familyPawnType : player1.getFamilyPawnAvailability().keySet()) {
            player1.getFamilyPawnAvailability().put(familyPawnType, true);
        }

        // every pawn has an actual value of 3
        for (FamilyPawn familyPawn : player1.getFamilyPawns()) {
            familyPawn.setActualValue(3);
        }


        ArrayList<DevelopmentCard> deck;

        FileReader cardFileReader = new FileReader("cards/greenCards");

        deck = new ObjectDeserializer().getCardDeck(cardFileReader);

        PersonalBoard player1Board = player1.getPersonalBoard();

        for (DevelopmentCard developmentCard : deck) {

            if (developmentCard.getSpecial().contentEquals("Foresta")) /*Forest - 5 */ {
                player1Board.getBuildingLane().addCard(developmentCard);
            }

            if (developmentCard.getSpecial().contentEquals("Monastero")) /*Stonemason's Shop - 6 */ {
                player1Board.getBuildingLane().addCard(developmentCard);

                //Monastero al terzo piano
                greenTower.getFloor(3).setDevelopmentCard(developmentCard);
            }
        }


        TowerAction towerActionGreen = new TowerAction(ZoneType.GREENTOWER, model, 3);

        player1.updateGoodSet(new GoodSet(0,0,0, -player1.getActualGoodSet().getGoodAmount(GoodType.WORKERS),0,0,0));
        player1.updateGoodSet(new GoodSet(0,0,0,10,0,0,0));
        greenTower.setOccupied(true);
        player1.updateGoodSet(new GoodSet(0,0,-player1.getActualGoodSet().getGoodAmount(GoodType.COINS), 0,0,0,0));
        player1.updateGoodSet(new GoodSet(0,0,10,0,0,0,0));
        player1.updateGoodSet(new GoodSet(0,0,0,0,0,10,0));
        greenTower.getFloor(3).getDevelopmentCard().setCardCost
                (new CardCost(false, true, new Cost(new GoodSet(3,3,3,3,3,3,3), new GoodSet()), new Cost()));
        player1.updateGoodSet(new GoodSet(3,3,3,3,3,3,3));
        greenTower.getFloor(3).getDevelopmentCard().setCardCost
                (new CardCost(false, true,
                        new Cost(new GoodSet(3,3,3,3,3,3,3),
                                new GoodSet(0,0,0,0,0,100,0)), new Cost()));
        player1.updateGoodSet(new GoodSet(0,0,0,0,0,100,0));


        towerActionGreen.setPlayer(player1);
        towerActionGreen.setFamiliyPawn(new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        towerActionGreen.isPossible();

        towerActionGreen.setCostChosen(0);

        GoodSet playerGoodSet = new GoodSet(player1.getActualGoodSet());

        assertTrue(towerActionGreen.getWorkers() == 4);

        towerActionGreen.execute();

        assertTrue(player1.getActualGoodSet().getGoodAmount(GoodType.WOOD)
                == playerGoodSet.getGoodAmount(GoodType.WOOD) - 3 + 2);

        assertTrue(player1.getActualGoodSet().getGoodAmount(GoodType.STONE)
                == playerGoodSet.getGoodAmount(GoodType.STONE) - 3);


        assertTrue(player1.getActualGoodSet().getGoodAmount(GoodType.WORKERS)
                == playerGoodSet.getGoodAmount(GoodType.WORKERS) -3 -4 +1);

        assertTrue(player1.getActualGoodSet().getGoodAmount(GoodType.COINS)
                == playerGoodSet.getGoodAmount(GoodType.COINS) - 3 - 3);

        assertTrue(player1.getActualGoodSet().getGoodAmount(GoodType.VICTORYPOINTS)
                == playerGoodSet.getGoodAmount(GoodType.VICTORYPOINTS) - 3);

        assertTrue(player1.getActualGoodSet().getGoodAmount(GoodType.MILITARYPOINTS)
                == playerGoodSet.getGoodAmount(GoodType.MILITARYPOINTS) - 3 + 2);

        assertTrue(player1.getActualGoodSet().getGoodAmount(GoodType.FAITHPOINTS)
                == playerGoodSet.getGoodAmount(GoodType.FAITHPOINTS) - 3);

        assertTrue(greenTower.isOccupied());
        assertTrue(greenTower.getFloor(3).getActionSpace().isOccupied());
        assertTrue(greenTower.getFloor(3).getActionSpace().getPawnPlaced().searchFamiliar(player1.getFamilyPawn(FamilyPawnType.BLACK)));

        System.out.println(towerActionGreen);

        Tower blueTower = model.getGameBoard().getTower(CardColor.BLUE);

        ArrayList<Effect> immediateEffects = new ArrayList<>();

        BonusEffect bonusEffect = new BonusEffect(new BonusAndMalusOnAction(ZoneType.GREENTOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), new BonusAndMalusOnCost(ZoneType.GREENTOWER, new GoodSet(), new GoodSet(), false));
        ActionEffect actionEffect = new ActionEffect(ZoneType.GREENTOWER, 4, new BonusAndMalusOnCost(ZoneType.GREENTOWER, new GoodSet(), new GoodSet(), false));
        Special special = new Special(SpecialBonusAndMalus.FIVEVICTORYPOINTSIFPRAY);

        immediateEffects.add(bonusEffect);
        immediateEffects.add(actionEffect);
        immediateEffects.add(special);

        ArrayList<Effect> permanentEffects = new ArrayList<>();

        BonusEffect bonusEffect1 = new BonusEffect(new BonusAndMalusOnAction(ZoneType.GREENTOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), new BonusAndMalusOnCost(ZoneType.GREENTOWER, new GoodSet(), new GoodSet(), false));
        permanentEffects.add(bonusEffect1);

        blueTower.getFloor(1).setDevelopmentCard(new DevelopmentCard("ciao", Era.FIRST, new CardCost(), CardColor.YELLOW, immediateEffects, permanentEffects, false, 0));


        TowerAction towerAction = new TowerAction(ZoneType.BLUETOWER, model, 1);

        towerAction.setPlayer(player1);
        towerAction.setFamiliyPawn(player1.getFamilyPawn(FamilyPawnType.ORANGE));

        System.out.println(towerAction);

        assertTrue(towerAction.isPossible());

        player1.setCurrentAction(towerAction);
        towerAction.execute();

        assertTrue(player1.getBonusAndMalusOnAction().size() == 1);
    }


}