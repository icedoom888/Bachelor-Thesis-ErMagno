package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.PersonalBoard;
import it.polimi.ingsw.GC_29.Model.Player;
import it.polimi.ingsw.GC_29.Model.PlayerColor;
import org.testng.annotations.Test;

import java.util.ArrayList;

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

    }

}