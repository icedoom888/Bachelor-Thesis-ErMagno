package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public class LeaderCard {
    private String leaderName;
    private Requirement requirement;
    private boolean permanent;
    private Effect effect;


    public LeaderCard(String leaderName, Requirement requirement, boolean permanent, Effect effect) {
        this.leaderName = leaderName;
        this.requirement = requirement;
        this.permanent = permanent;
        this.effect = effect;
    }

    public boolean isPossible(Player player) {
        Map<CardColor, Integer> playerCards = player.getCardsOwned();
        GoodSet playerGoodSet = player.getActualGoodSet();

        return requirement.isPossible(playerCards, playerGoodSet);

    }

    public void executeEffect(Player player) throws Exception {

        effect.execute(player);
        if (permanent) {
            player.getPermanentLeaders().put(this, false);
        }
        else player.getOncePerRoundLeaders().put(this, false);

    }

    public String getLeaderName() {
        return leaderName;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public boolean isPermanent() {
        return permanent;
    }

}
