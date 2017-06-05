package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by Christian on 02/06/2017.
 */
public class GameSetupTest {
    @Test
    public void testInit() throws Exception {

        BonusTile bonusTile = new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet()) );

        Player player1 = new Player("Lorenzo", PlayerColor.BLUE, new PersonalBoard(bonusTile, 6));

        Player player2 = new Player("Alberto", PlayerColor.RED, new PersonalBoard(bonusTile, 6));

        ArrayList<Player> players = new ArrayList<Player>();

        players.add(player1);

        players.add(player2);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

        ArrayDeque<DevelopmentCard> yellowDeck = GameStatus.getInstance().getOrderedDecks().get(CardColor.YELLOW);

        Integer i = 1;

        while(!yellowDeck.isEmpty()){

            DevelopmentCard card = yellowDeck.pop();

            System.out.println(i.toString() + ": " + card);

            i++;

        }

    }

    @Test
    public void testCreateActions() throws Exception {

    for(int i = 2; i<5; i++){

        createActions(i);
    }

    }

    private void createActions(int numberOfPlayers) throws IOException {

        ArrayList<Player> players = createPlayers(numberOfPlayers);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

        for(Action action : ActionChecker.getInstance().getActionList()){

            System.out.println(action);
        }

    }
    private ArrayList<Player> createPlayers(int numberOfPlayers){

        ArrayList<Player> playerArrayList = new ArrayList<>();

        for(int i = 0; i < numberOfPlayers; i++){

            BonusTile bonusTile = new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet()) );

            Player player = new Player("Lorenzo", PlayerColor.BLUE, new PersonalBoard(bonusTile, 6));

            playerArrayList.add(player);
        }

        return playerArrayList;
    }

}