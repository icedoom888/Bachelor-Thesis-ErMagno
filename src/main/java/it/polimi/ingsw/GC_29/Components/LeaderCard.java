package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public class LeaderCard {
    private String leader;
    private Requirement[] requirements;
    private boolean repeatable;
    private Effect effect;

    public void checkRequirements(Player player) {
    }

    public void handleEffect() {
    }

    public String getLeader() {
        return leader;
    }

    public Requirement[] getRequirements() {
        return requirements;
    }

    private boolean isRepeatable() {
        return repeatable;
    }

    public Effect getEffect() {
        return effect;
    }
}
