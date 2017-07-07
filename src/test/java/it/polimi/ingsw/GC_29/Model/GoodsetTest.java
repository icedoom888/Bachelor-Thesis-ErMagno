package it.polimi.ingsw.GC_29.Model;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by AlbertoPennino on 05/07/2017.
 */
public class GoodsetTest {
    @Test
    public void TestEnoughResources(){
        GoodSet goodSet1 = new GoodSet(3,3,3,3,3,3,3);
        GoodSet goodSet2 = new GoodSet(0,0,0,0,0,0,0);
        GoodSet goodSet3 = new GoodSet(1,0,0,0,0,0,0);


        GoodSet goodsetCompare1 = new GoodSet(1,1,1,1,1,1,1);
        GoodSet goodsetCompare2 = new GoodSet(1,0,0,0,0,0,0);

        assertTrue(goodSet1.enoughResources(goodsetCompare1));
        assertFalse(goodSet2.enoughResources(goodsetCompare1));
        assertTrue(goodSet3.enoughResources(goodsetCompare2));
    }
}
