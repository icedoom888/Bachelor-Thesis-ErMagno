package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnCost;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnGoods;
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
    private BonusAndMalusOnAction bonusAndMalusOnAction;
    private BonusAndMalusOnCost bonusAndMalusOnCost;
    private BonusAndMalusOnGoods bonusAndMalusOnGoods;


    public LeaderCard(String leaderName, Requirement requirement, boolean permanent, Effect effect) {
        this.leaderName = leaderName;
        this.requirement = requirement;
        this.permanent = permanent;
        this.effect = effect;
    }

    public LeaderCard(String leaderName, Requirement requirement, boolean permanent, Effect effect, BonusAndMalusOnCost bonusAndMalusOnCost) {
        this.leaderName = leaderName;
        this.requirement = requirement;
        this.permanent = permanent;
        this.effect = effect;
        this.bonusAndMalusOnCost = bonusAndMalusOnCost;
    }

    public LeaderCard(String leaderName, Requirement requirement, boolean permanent, Effect effect, BonusAndMalusOnAction bonusAndMalusOnAction) {
        this.leaderName = leaderName;
        this.requirement = requirement;
        this.permanent = permanent;
        this.effect = effect;
        this.bonusAndMalusOnAction = bonusAndMalusOnAction;
    }

    public LeaderCard(String leaderName, Requirement requirement, boolean permanent, Effect effect, BonusAndMalusOnGoods bonusAndMalusOnGoods) {
        this.leaderName = leaderName;
        this.requirement = requirement;
        this.permanent = permanent;
        this.effect = effect;
        this.bonusAndMalusOnGoods = bonusAndMalusOnGoods;
    }

    public boolean isPossible(Player player) {

        Map<CardColor, Integer> playerCards = player.getCardsOwned();
        GoodSet playerGoodSet = player.getActualGoodSet();

        Boolean available;

        if (permanent) {
            available = player.getPermanentLeaders().get(this);
        }
        else {
            available = player.getOncePerRoundLeaders().get(this);
        }

        return requirement.isPossible(playerCards, playerGoodSet)
                && available;

    }

    public void executeEffect(Player player) throws Exception {

        if (effect != null) {
            effect.execute(player);
        }

        if (bonusAndMalusOnGoods != null) {
            player.getBonusAndMalusOnGoods().add(bonusAndMalusOnGoods);
        }

        if (bonusAndMalusOnAction != null) {
            player.getBonusAndMalusOnAction().add(bonusAndMalusOnAction);
        }

        if (bonusAndMalusOnCost != null) {
            player.getBonusAndMalusOnCost().add(bonusAndMalusOnCost);
        }

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
