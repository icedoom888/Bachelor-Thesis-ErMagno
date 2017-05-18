package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public class ObtainEffectTest {
    @Test
    public void testObtain() throws Exception {

        GoodSet goodSet = new GoodSet(1,2,3,4,5,6,7);

        PlayerStatus playerStatus = new PlayerStatus(new GoodSet(7,6,5,4,3,2,1));

        ObtainEffect obtainEffect = new ObtainEffect(goodSet);

        obtainEffect.execute(playerStatus);


    }
}