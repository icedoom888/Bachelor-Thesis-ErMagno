package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class CouncilPrivilegeEffect implements Effect {

    private int numberOfCouncilPrivileges;

    private List<CouncilPrivilege> parchmentList;

    private Boolean modified = false; //TODO: da rivedere, orribile

    public CouncilPrivilegeEffect(int numberOfCouncilPrivileges) {

        this.numberOfCouncilPrivileges = numberOfCouncilPrivileges;

        this.parchmentList = new ArrayList<>(numberOfCouncilPrivileges);
        for(int i = 0; i < numberOfCouncilPrivileges; i++){

            parchmentList.add(new CouncilPrivilege());

        }
    }

    public int getNumberOfCouncilPrivileges() {
        return numberOfCouncilPrivileges;
    }

    public List<CouncilPrivilege> getParchmentList() {
        return parchmentList;
    }

    @Override
    /**
     * A player can choose between three different effects in CouncilPrivilege.
     * After player's choice the collectRewards method creates a temporary goodSet in which
     * sums all the bonuses.
     * Then it updates the player's goodSet.
     */
    public void execute(Player status) throws Exception {

        status.getCouncilPrivilegeEffectList().add(this);

       /* System.out.println("You received a Council Privilege Effect!");

        boolean decisionDone = false;

        while (!decisionDone) {

            whichPrivileges();

            decisionDone = checkDifferentPrivileges();
        }

        ObtainEffect[] effects = collectRewards();

        System.out.println("You received the following effects: ");

        for (ObtainEffect effect : effects) {

            if (modified) effect.doubleResources();

            effect.execute(status);

            System.out.println(effect.toString());
        }*/

    }


    /*public void whichPrivileges() {

        System.out.println("Choose " + numberOfCouncilPrivileges + " different priviliges between the following: ");

        // Da ora in poi per testing
        for (int i = 0; i < numberOfCouncilPrivileges ; i++) {

            System.out.println("Quale pergamena vuoi?\n");

            if (i != 0 ) System.out.println("Ricorda di non sceglierne una uguale ad una già scelta!");

            //System.out.println(Arrays.asList(CouncilPrivilege.values()));

            int k=1;

            for (CouncilPrivilege councilPrivilege : CouncilPrivilege.values()) {

                System.out.println("Se vuoi ottenere: " + councilPrivilege + ", scrivi " + k);

                k++;

            }

            // Scanner scanner = new Scanner(System.in);

            // String answer = scanner.nextLine();

            //TODO: adapter
            int answer1 = i+1;
            String answer = Integer.toString(answer1);

            switch (answer) {

                case "1":
                    CouncilPrivilege realAnswer = ONEWOOD_ONESTONE;
                    parchmentList[i]=realAnswer;
                    break;

                case "2":
                    realAnswer = TWOWORKERS;
                    parchmentList[i]=realAnswer;
                    break;

                case "3":
                    realAnswer = TWOGOLDS;
                    parchmentList[i]=realAnswer;
                    break;

                case "4":
                    realAnswer = TWOMILITARYPOINTS;
                    parchmentList[i]=realAnswer;
                    break;

                case "5":
                    realAnswer = ONEFAITHPOINT;
                    parchmentList[i]=realAnswer;
                    break;

                default:
                    System.out.println("Error Input");
                    break;
            }
        }
    }

    /**
     * This method checks if the player chose three different priviliges and returns true
     * if everything went well
     */
   /* public boolean checkDifferentPrivileges() {

        boolean duplicate = false;

        for (int i = 0; i < numberOfCouncilPrivileges; i++) {

            for (int k = i+1; k < numberOfCouncilPrivileges; k++) {

                if (k != i && parchmentList[k] == parchmentList[i]) duplicate = true;
            }
        }

        if (duplicate == true) {

            System.out.println("You made wrong choices, try again");

        }

        return !duplicate;
    }

    /**
     * collectRewards method adds to a temporaryGoodSet all the rewards of the effect
     */
   /* public ObtainEffect[] collectRewards() { // TODO: create effects

        ObtainEffect[] effects = new ObtainEffect[numberOfCouncilPrivileges];

        for (int i = 0; i < numberOfCouncilPrivileges; i++) {

            effects[i] = new ObtainEffect(parchmentList[i].getGoodSet());

        }

        return effects;
    }

    @Override
    public String toString() {
          return "CouncilPrivilegeEffect { "
                + "numberOfCouncilPrivileges = " + numberOfCouncilPrivileges + "\n"
                + '}';
    }*/

    public void doubleResources() {
        this.modified = true;
    }
}
