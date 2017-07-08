package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Model.*;
import jdk.nashorn.internal.runtime.WithObject;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 07/07/17.
 */
public class ActivateCardsTest {
    @Test
    public void testPerform() throws Exception {

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


        model.setCurrentPlayer(player1);

        ActionChecker actionChecker = controller.getActionChecker();

        actionChecker.setCurrentPlayer();

        List<Action> actions = actionChecker.getActionList();

        ArrayList<WorkAction> workActions = new ArrayList<>();

        for (Action action : actions) {
            if (action instanceof WorkAction) {
                if (action.getZoneType() == ZoneType.PRODUCTION) {
                    workActions.add((WorkAction) action);
                }
            }
        }

        WorkAction workAction = workActions.remove(0);

        model.setCurrentAction(workAction);

        System.out.println("Prima azione: " + workAction);

        ArrayList<DevelopmentCard> deck;

        FileReader cardFileReader = new FileReader("cards/yellowCards");

        deck = new ObjectDeserializer().getCardDeck(cardFileReader);

        PersonalBoard player1Board = player1.getPersonalBoard();

        for (DevelopmentCard developmentCard : deck) {

            if (developmentCard.getSpecial().contentEquals("Falegnameria")) /*Carpenter's Shop - 4 */ {
                player1Board.getBuildingLane().addCard(developmentCard);
            }

            if (developmentCard.getSpecial().contentEquals("Tagliapietre")) /*Stonemason's Shop - 3 */ {
                player1Board.getBuildingLane().addCard(developmentCard);
            }
        }

        /**
         * Per inizializzazione il player ha già 2 pietre e 2 legni (e 3 workers), quindi ha la possibilità di effettuare
         * entrambe le pay to obtain.
         *
         * Ora setto la pedina
         */

        for (FamilyPawnType familyPawnType : player1.getFamilyPawnAvailability().keySet()) {
            player1.getFamilyPawnAvailability().put(familyPawnType, true);
        }

        FamilyPawn playerPawn = player1.getFamilyPawn(FamilyPawnType.BLACK);

        /**
         * può fare l'azione, ma ha bisogno di workers, in particolare 2 per il tagliaPietre e 3 per Falegnameria
         */
        playerPawn.setActualValue(1);

        workAction.setFamiliyPawn(new FamilyPawn(playerPawn));

        boolean workActionPossible = workAction.isPossible();

        assertTrue(workActionPossible);

        player1.setCurrentAction(workAction);

        player1.getPersonalBoard().setBonusTile(new BonusTile(new ObtainEffect(1,1,1,0,0,0,0), new ObtainEffect(1,1,1,0,0,0,0)));

        /**
         * Per ora il player sceglie due workers
         */

        workAction.buildDifferentChoices();

        ActivateCards activateCards = new ActivateCards(2);

        activateCards.perform(model, controller);

        assertTrue(player1.getPlayerState() == PlayerState.ACTIVATE_PAY_TO_OBTAIN_CARDS);











    }

}