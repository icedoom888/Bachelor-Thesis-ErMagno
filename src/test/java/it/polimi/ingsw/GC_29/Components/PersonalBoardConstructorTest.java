package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import org.testng.annotations.Test;

/**
 * Created by AlbertoPennino on 21/05/2017.
 */
public class PersonalBoardConstructorTest {
    @Test
    public void testPersonalBoard() throws Exception{
        BonusTile bonusTile = new BonusTile(new ObtainEffect(1,0,0,0,0,0,0),new ObtainEffect(0,1,0,0,0,0,0));
        PersonalBoard personalBoard_1 = new PersonalBoard(bonusTile);
        System.out.println("personalBoard_1 successfully created");
    }
}
