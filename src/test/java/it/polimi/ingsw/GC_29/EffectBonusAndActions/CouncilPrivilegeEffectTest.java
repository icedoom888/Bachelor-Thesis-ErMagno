package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import org.junit.jupiter.api.Test;


/**
 * Created by Lorenzotara on 20/05/17.
 */
public class CouncilPrivilegeEffectTest {

    @Test
    public void testCheckDifferentPrivileges() throws Exception {

        int numberOfCouncilPrivileges = 3;
        CouncilPrivilegeEffect effect = new CouncilPrivilegeEffect(numberOfCouncilPrivileges);

        effect.whichPrivileges();
        effect.checkDifferentPrivileges();


    }


}