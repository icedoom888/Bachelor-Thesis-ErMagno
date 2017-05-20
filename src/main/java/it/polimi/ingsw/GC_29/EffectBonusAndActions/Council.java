package it.polimi.ingsw.GC_29.EffectBonusAndActions;

/**
 * Created by Lorenzotara on 20/05/17.
 */
class CouncilTest {

    public static void main(String[] args) throws Exception {

        int numberOfCouncilPrivileges = 3;
        CouncilPrivilegeEffect effect = new CouncilPrivilegeEffect(numberOfCouncilPrivileges);

        effect.whichPrivileges();
        System.out.println(effect.checkDifferentPrivileges());

    }
}
