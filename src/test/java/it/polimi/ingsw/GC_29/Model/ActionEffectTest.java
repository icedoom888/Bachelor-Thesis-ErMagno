package it.polimi.ingsw.GC_29.Model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 09/07/17.
 */
public class ActionEffectTest {
    @Test
    public void testGetType() throws Exception {

        ActionEffect actionEffect = new ActionEffect(ZoneType.GREENTOWER, 4,
                new BonusAndMalusOnCost(ZoneType.YELLOWTOWER, new GoodSet(), new GoodSet(), true));

        assertTrue(actionEffect.getType() == ZoneType.GREENTOWER);
    }

    @Test
    public void testGetActionValue() throws Exception {

        ActionEffect actionEffect = new ActionEffect(ZoneType.GREENTOWER, 4,
                new BonusAndMalusOnCost(ZoneType.YELLOWTOWER, new GoodSet(), new GoodSet(), true));

        assertTrue(actionEffect.getActionValue() == 4);
    }

    @Test
    public void testGetOnlyWorkers() throws Exception {

        ActionEffect actionEffect = new ActionEffect(ZoneType.GREENTOWER, 4,
                new BonusAndMalusOnCost(ZoneType.YELLOWTOWER, new GoodSet(), new GoodSet(), true), false);

        assertTrue(actionEffect.getOnlyWorkers() == false);
    }

    @Test
    public void testExecute() throws Exception {

        ActionEffect actionEffect = new ActionEffect(ZoneType.GREENTOWER, 4,
                new BonusAndMalusOnCost(ZoneType.YELLOWTOWER, new GoodSet(), new GoodSet(), true));

        Player player = new Player("lorenzo", PlayerColor.BLUE, new PersonalBoard(6));
        actionEffect.execute(player);
        assertTrue(player.getCurrentBonusActionList().size() == 1);
        assertTrue(player.getCurrentBonusActionList().contains(actionEffect));

    }

}