package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Controllers.ObjectDeserializer;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Created by Christian on 08/07/2017.
 */
public class WorkActionTest {

    /**
     *
     * This test verifies the right behaviour of the isPossible() method of the WorkAction class
     *
     * @throws Exception
     */
    @Test
    public void testIsPossible() throws Exception {


        // Variables needed for the test

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
         * WorkAction variable created ( production zone and second field of the production zone, the one with the
         * malus of three dice Decrement on the action
         */

        WorkAction workAction = new WorkAction(ZoneType.PRODUCTION, model, FieldType.SECOND);

        for (FamilyPawnType familyPawnType : player1.getFamilyPawnAvailability().keySet()) {
            player1.getFamilyPawnAvailability().put(familyPawnType, true);
        }

        // every pawn has an actual value of 3
        for (FamilyPawn familyPawn : player1.getFamilyPawns()) {
            if(familyPawn.getType() != FamilyPawnType.NEUTRAL){
                familyPawn.setActualValue(3);
            }

        }

        FamilyPawn pawn = player1.getFamilyPawn(FamilyPawnType.BLACK);

        workAction.setFamiliyPawn(pawn);

        workAction.setPlayer(player1);

        Boolean result = workAction.isPossible();

        /**
         *
         * tested the fact that if the first field is free, the pawn cannot be setted in the second field
         */
        assertFalse(result);

        /**
         *
         * a second variable workAction, production in first field
         */

        WorkAction workAction1 = new WorkAction(ZoneType.PRODUCTION, model, FieldType.FIRST);

        workAction1.setFamiliyPawn(pawn);

        workAction1.setPlayer(player1);

        /**
         *
         * the first field is free, the isPossible method is expected to return true
         */
        assertTrue(workAction1.isPossible());

        /**
         *
         * pawn added in the first field
         *
         */
        workAction1.addPawn();

        workAction.setFamiliyPawn(player1.getFamilyPawn(FamilyPawnType.ORANGE));

        /**
         *
         * the isPossible() method is expected to return false because in the first field there is
         * a coloured pawn of the same player (player 1), so you cannot add a pawn in the second field
         *
         */
        assertTrue(!workAction.isPossible());


        //using a neutral pawn for the second field
        workAction.setFamiliyPawn(player1.getFamilyPawn(FamilyPawnType.NEUTRAL));


        /**
         * the isPossible() is expected to return true because we used a NEUTRAL pawn of the same player
         *
         */
        assertTrue(workAction.isPossible());

        workAction1.setPlayer(player2);

        workAction1.setFamiliyPawn(player2.getFamilyPawn(FamilyPawnType.BLACK));

        /**
         * expected false because the action space of the first field is already occupied
         */
        assertTrue(!workAction1.isPossible());

        workAction.setFamiliyPawn(player2.getFamilyPawn(FamilyPawnType.BLACK));

        workAction.setPlayer(player2);


        /**
         * expected true because the second field is free, the first field is occupied and the pawn belongs
         * to another player
         */
        assertTrue(workAction.isPossible());

    }

    /**
     *
     * this test verifies the correct behaviour of buildDifferentChoices, a method that creates
     * a map with key the amount of workers needed in order to activate a set of cards owned by the player.
     *
     * @throws Exception
     */
    @Test
    public void testBuildDifferentChoices() throws Exception {

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

        FileReader greenCardFileReader = new FileReader("cards/greenCards");

        FileReader yellowCardFileReader = new FileReader("cards/yellowCards");

        ArrayList<DevelopmentCard> greenDeck = new ObjectDeserializer().getCardDeck(greenCardFileReader);

        ArrayList<DevelopmentCard> yellowDeck = new ObjectDeserializer().getCardDeck(yellowCardFileReader);

        DevelopmentCard yellowCard = null;

        for (DevelopmentCard developmentCard : yellowDeck) {

            if(developmentCard.getSpecial().contentEquals("Teatro")){

                yellowCard = developmentCard;
            }

        }

        DevelopmentCard greenCard = null;

        for (DevelopmentCard developmentCard : greenDeck) {

            if(developmentCard.getSpecial().contentEquals("Città")){

                greenCard = developmentCard;
            }
        }

        player1.updateGoodSet(new GoodSet(2,2,2,3,0,0,0));
        player2.updateGoodSet(new GoodSet(2,2,2,3,0,0,0));

        for (FamilyPawnType familyPawnType : player1.getFamilyPawnAvailability().keySet()) {
            player1.getFamilyPawnAvailability().put(familyPawnType, true);
        }

        // every pawn has an actual value of 3
        for (Player player : players) {
            for (FamilyPawn familyPawn : player.getFamilyPawns()) {
                if(familyPawn.getType() != FamilyPawnType.NEUTRAL){
                    familyPawn.setActualValue(3);
                }
            }
            
        }


        Map<Integer, BonusTile> bonusTiles = new ObjectDeserializer().getBonusTiles();

        player1.getPersonalBoard().setBonusTile(bonusTiles.get(0));
        player2.getPersonalBoard().setBonusTile(bonusTiles.get(1));

        if(greenCard != null){

            player1.getPersonalBoard().getTerritoryLane().addCard(greenCard);
            player2.getPersonalBoard().getTerritoryLane().addCard(greenCard);

        }

        else assertTrue(false);

        if(yellowCard != null){

            player2.getPersonalBoard().getBuildingLane().addCard(yellowCard);
            player1.getPersonalBoard().getBuildingLane().addCard(yellowCard);

        }

        else assertTrue(false);

        WorkAction workAction = new WorkAction(ZoneType.HARVEST, model, FieldType.FIRST);

        workAction.setPlayer(player1);

        workAction.setFamiliyPawn(player1.getFamilyPawn(FamilyPawnType.ORANGE));

        workAction.buildDifferentChoices();

        for (Map.Entry<Integer, ArrayList<DevelopmentCard>> entry : workAction.getCardsForWorkers().entrySet()) {

            System.out.println("workers amount " + entry.getKey() + " Cards: " + entry.getValue().toString());
        }

        assertTrue(workAction.getCardsForWorkers().containsKey(3));

        workAction.addPawn();

        WorkAction workAction1 = new WorkAction(ZoneType.HARVEST, model, FieldType.SECOND);

        workAction1.setFamiliyPawn(player2.getFamilyPawn(FamilyPawnType.ORANGE));
        workAction1.setPlayer(player2);

        player2.setCurrentAction(workAction1);

        System.out.println(player2.getFamilyPawn(FamilyPawnType.ORANGE));

        workAction1.buildDifferentChoices();

        assertTrue(!workAction1.getCardsForWorkers().containsKey(3));
        assertEquals(workAction1.getTemporaryPawn().getActualValue(), 3);

        for (Map.Entry<Integer, ArrayList<DevelopmentCard>> entry : workAction1.getCardsForWorkers().entrySet()) {

            System.out.println("workers amount " + entry.getKey() + " Cards: " + entry.getValue().toString());
        }

        WorkAction workAction2 = new WorkAction(ZoneType.PRODUCTION, model, FieldType.FIRST);

        workAction2.setPlayer(player1);

        workAction2.setFamiliyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK));

        workAction2.buildDifferentChoices();

        for (Map.Entry<Integer, ArrayList<DevelopmentCard>> entry : workAction2.getCardsForWorkers().entrySet()) {

            System.out.println("workers amount " + entry.getKey() + " Cards: " + entry.getValue().toString());
        }

        assertTrue(workAction2.getCardsForWorkers().containsKey(3));




    }


}