package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import org.testng.annotations.Test;

import java.util.Scanner;

import static it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeType.*;
import static org.testng.Assert.*;
import static org.testng.reporters.jq.BasePanel.C;

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