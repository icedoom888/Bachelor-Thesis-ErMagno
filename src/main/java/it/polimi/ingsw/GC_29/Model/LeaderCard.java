package it.polimi.ingsw.GC_29.Model;

import java.util.Map;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public class LeaderCard {
    private String leaderName;
    private String url;
    private Requirement requirement;
    private boolean permanent;
    private Effect effect;
    private BonusAndMalusOnAction bonusAndMalusOnAction;
    private BonusAndMalusOnCost bonusAndMalusOnCost;
    private BonusAndMalusOnGoods bonusAndMalusOnGoods;
    private boolean discarded;
    private boolean activated;


    /*
    public LeaderCard(String leaderName, String url, Requirement requirement, boolean permanent, Effect effect) {
        this.leaderName = leaderName;
        this.url = url;
        this.requirement = requirement;
        this.permanent = permanent;
        this.effect = effect;
    }

    public LeaderCard(String leaderName,String url, Requirement requirement, boolean permanent, Effect effect, BonusAndMalusOnCost bonusAndMalusOnCost) {
        this.leaderName = leaderName;
        this.url = url;
        this.requirement = requirement;
        this.permanent = permanent;
        this.effect = effect;
        this.bonusAndMalusOnCost = bonusAndMalusOnCost;
    }

    public LeaderCard(String leaderName,String url, Requirement requirement, boolean permanent, Effect effect, BonusAndMalusOnAction bonusAndMalusOnAction) {
        this.leaderName = leaderName;
        this.url = url;
        this.requirement = requirement;
        this.permanent = permanent;
        this.effect = effect;
        this.bonusAndMalusOnAction = bonusAndMalusOnAction;
    }

    public LeaderCard(String leaderName,String url, Requirement requirement, boolean permanent, Effect effect, BonusAndMalusOnGoods bonusAndMalusOnGoods) {
        this.leaderName = leaderName;
        this.url = url;
        this.requirement = requirement;
        this.permanent = permanent;
        this.effect = effect;
        this.bonusAndMalusOnGoods = bonusAndMalusOnGoods;
    }

    public LeaderCard(String leaderName, String url, Requirement requirement, boolean permanent, Effect effect, BonusAndMalusOnAction bonusAndMalusOnAction, BonusAndMalusOnCost bonusAndMalusOnCost, BonusAndMalusOnGoods bonusAndMalusOnGoods, boolean discarded, boolean activated) {
        this.leaderName = leaderName;
        this.url = url;
        this.requirement = requirement;
        this.permanent = permanent;
        this.effect = effect;
        this.bonusAndMalusOnAction = bonusAndMalusOnAction;
        this.bonusAndMalusOnCost = bonusAndMalusOnCost;
        this.bonusAndMalusOnGoods = bonusAndMalusOnGoods;
        this.discarded = discarded;
        this.activated = activated;
    }
    */

    public boolean isPossible(Player player) {

        Map<CardColor, Integer> playerCards = player.getCardsOwned();
        GoodSet playerGoodSet = player.getActualGoodSet();

        Boolean available;

        available = !discarded && !activated;

        return requirement.isPossible(playerCards, playerGoodSet) && available;

    }

    /**
     * When the player decides to activate the leader card, this method is called.
     * If there is an effect, it is executed; all the bonus and maluses are added to
     * the player's lists
     * @param player
     */
    public void execute(Player player) {

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

        activated = true;

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

    public String getUrl() {
        return url;
    }

    public void setDiscarded() {
        discarded = true;
    }

    public boolean isActivated() {
        return activated;
    }

    public boolean isDiscarded() {
        return discarded;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "LeaderCard{" +
                "leaderName = '" + leaderName + '\'' +
                ", requirement = " + requirement +
                ", permanent = " + permanent +
                effect +
                bonusAndMalusOnAction +
                bonusAndMalusOnCost +
                bonusAndMalusOnGoods +
                '}';
    }
}
