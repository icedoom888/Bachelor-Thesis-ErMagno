package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Model.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 07/07/17.
 */
public class EndTurnTest {
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


        player1.setPlayerState(PlayerState.WAITING);
        player2.setPlayerState(PlayerState.WAITING);
        player3.setPlayerState(PlayerState.WAITING);
        player4.setPlayerState(PlayerState.WAITING);


        model.getTurnOrder().get(0).setPlayerState(PlayerState.DOACTION);
        model.setCurrentPlayer(model.getTurnOrder().get(0));


        for (int j = 0; j < 4; j++) {

            for (int i = 0; i < model.getTurnOrder().size(); i++) {


                controller.startTimer(model.getTurnOrder().get(i));
                new EndTurn().perform(model, controller);

                if (j == 3) {
                    System.out.println(j);
                }

                if (i!=model.getTurnOrder().size()-1) {
                    assertTrue(model.getTurnOrder().get(i).getPlayerState() == PlayerState.WAITING);
                    assertTrue(model.getTurnOrder().get(i+1).getPlayerState() == PlayerState.DOACTION);

                }
                else if (j != 3){
                    assertTrue(model.getTurnOrder().get(0).getPlayerState() == PlayerState.DOACTION);

                }

                else {
                    assertTrue(model.getTurnOrder().get(0).getPlayerState() == PlayerState.THROWDICES);

                }
            }
        }

        Player notSkippingPlayer = model.getTurnOrder().get(0);
        Player skippingPlayer1 = model.getTurnOrder().get(1);
        Player skippingPlayer2 = model.getTurnOrder().get(2);
        Player skippingPlayer3 = model.getTurnOrder().get(3);

        skippingPlayer1.getSpecialBonusAndMaluses().add(SpecialBonusAndMalus.SKIPFIRSTTURN);
        skippingPlayer2.getSpecialBonusAndMaluses().add(SpecialBonusAndMalus.SKIPFIRSTTURN);
        skippingPlayer3.getSpecialBonusAndMaluses().add(SpecialBonusAndMalus.SKIPFIRSTTURN);

        ArrayList<Player> skippingPlayers = new ArrayList<>();

        skippingPlayers.add(skippingPlayer1);
        skippingPlayers.add(skippingPlayer2);
        skippingPlayers.add(skippingPlayer3);

        model.setSkippedTurnPlayers(skippingPlayers);


        new ThrowDices().perform(model, controller);

        new EndTurn().perform(model, controller);

        assertTrue(notSkippingPlayer.getPlayerState() == PlayerState.DOACTION);
        assertTrue(skippingPlayer1.getPlayerState() == PlayerState.WAITING);
        assertTrue(skippingPlayer2.getPlayerState() == PlayerState.WAITING);
        assertTrue(skippingPlayer3.getPlayerState() == PlayerState.WAITING);

        /**
         * Concludo gli ultimi 3 turni "normali"
         */
        for (int j = 0; j < 3*4; j++) {

            new EndTurn().perform(model, controller);
        }


        /**
         * Ora giocano gli skippati
         */

        assertTrue(skippingPlayer1.getPlayerState() == PlayerState.DOACTION);
        new EndTurn().perform(model, controller);
        assertTrue(skippingPlayer2.getPlayerState() == PlayerState.DOACTION);
        new EndTurn().perform(model, controller);
        assertTrue(skippingPlayer3.getPlayerState() == PlayerState.DOACTION);
        new EndTurn().perform(model, controller);
        assertTrue(notSkippingPlayer.getPlayerState() == PlayerState.THROWDICES);

    }

}