package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.GC_29.Player.PlayerColor.*;
import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class TowerActionTest {
    @Test
    public void testExecute() throws Exception {

    }

    @Test
    public void testIsPossible() throws Exception {
        FamilyPawn familyPawnBlueOrange = new FamilyPawn(BLUE, FamilyPawnType.ORANGE, 3);
        FamilyPawn familyPawnRedBlack = new FamilyPawn(GREEN, FamilyPawnType.BLACK, 3);
        ActionType actionType = ActionType.BLUETOWER;
        int workersSelected = 1;
        boolean realAction = true;
        PlayerStatus playerStatus = new PlayerStatus(new ArrayList<BonusAndMalusOnAction>(), new ArrayList<BonusAndMalusOnGoods>(), new GoodSet(1,2,3,4,5,6,7), new HashMap<CardColor, Integer>(), true, true,true,true);
        Tower tower = new Tower(CardColor.BLUE);
        int floorIndex = 2;
        tower.getFloors()[floorIndex].setDevelopmentCard(new DevelopmentCard("a", "a", Era.FIRSTERA, new CardCost(false, true, new GoodSet(2,2,3,4,5,6,7), new GoodSet(), false, new GoodSet()), CardColor.BLUE, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0));
        TowerAction towerAction = new TowerAction(familyPawnBlueOrange, actionType, workersSelected, realAction, playerStatus, tower, floorIndex);

        tower.getFloor(floorIndex-1).getActionSpace().addPawn(familyPawnRedBlack);

        System.out.println(towerAction.isPossible());
    }

}