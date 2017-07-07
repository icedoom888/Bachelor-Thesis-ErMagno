package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

/**
 * Created by AlbertoPennino on 05/07/2017.
 */
public class BonusTileTest {
    @org.testng.annotations.Test
    /**
     * Test on the table representation of the bonus tile
     */
    public void testToTable() throws Exception {
        ObtainEffect testEffect = new ObtainEffect(4,2,0,0,5,0,1);
        BonusTile bonusTile = new BonusTile(testEffect,testEffect);
        System.out.print(bonusTile.toTable());
    }

}
