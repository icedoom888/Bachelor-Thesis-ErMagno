package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.BonusTile;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.PersonalBoard;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Christian on 03/06/2017.
 */
public class ActionCheckerTest {
    @Test
    public void testFakeAddValidActionMap() throws Exception {

        ActionChecker actionChecker =  ActionChecker.getInstance();

        PersonalBoard personalBoard = new PersonalBoard(new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet())), 6);

        Player currentPlayer = new Player("pippo", PlayerColor.RED, personalBoard);

        actionChecker.setCurrentPlayer(currentPlayer);

        actionChecker.fakeAddValidActionMap(currentPlayer.getFamilyPawn(FamilyPawnType.ORANGE));

        actionChecker.fakeAddValidActionMap(currentPlayer.getFamilyPawn(FamilyPawnType.BLACK));

        int i = 0;

    }

}