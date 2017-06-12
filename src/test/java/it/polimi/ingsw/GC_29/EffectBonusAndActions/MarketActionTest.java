package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.BonusTile;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.PersonalBoard;
import it.polimi.ingsw.GC_29.Components.ShopName;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 03/06/17.

public class MarketActionTest {
    @Test
    public void testExecute() throws Exception {

        BonusTile bonusTile = new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet()) );

        Player player1 = new Player("Lorenzo", PlayerColor.BLUE, new PersonalBoard(bonusTile, 6));

        Player player2 = new Player("Alberto", PlayerColor.RED, new PersonalBoard(bonusTile, 6));

        ArrayList<Player> players = new ArrayList<Player>();

        players.add(player1);

        players.add(player2);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

        MarketAction testMarketAction = new MarketAction(ShopName.MONEYSHOP);
        testMarketAction.setFamiliyPawn(player1.getFamilyPawns()[1]);
        testMarketAction.setPlayer(player1);

        Boolean possible = testMarketAction.isPossible();

        System.out.println(possible);

        testMarketAction.execute();
    }

}*/