package it.polimi.ingsw.GC_29.Model;


/**
 * Created by Lorenzotara on 17/05/17.
 */

public class ExcommunicationTile {


    private String url;
    private Era era;
    private SpecialBonusAndMalus special;
    private BonusAndMalusOnAction malusOnAction;
    private BonusAndMalusOnGoods malusOnGoods;
    private BonusAndMalusOnCost malusOnCost;
    private Effect effect;

    public ExcommunicationTile(String url,
                               Era era,
                               SpecialBonusAndMalus special,
                               BonusAndMalusOnAction malusOnAction,
                               BonusAndMalusOnGoods malusOnGoods,
                               BonusAndMalusOnCost malusOnCost,
                               Effect effect) {
        this.url = url;
        this.era = era;
        this.special = special;
        this.malusOnAction = malusOnAction;
        this.malusOnGoods = malusOnGoods;
        this.malusOnCost = malusOnCost;
        this.effect = effect;
    }


    public Era getEra() {
        return era;
    }

    public SpecialBonusAndMalus getSpecial() {
        return special;
    }

    public BonusAndMalusOnAction getMalusOnAction() {
        return malusOnAction;
    }

    public BonusAndMalusOnGoods getMalusOnGoods() {
        return malusOnGoods;
    }

    public BonusAndMalusOnCost getMalusOnCost() {
        return malusOnCost;
    }

    public Effect getEffect() {
        return effect;
    }

    public String getUrl() {
        return url;
    }

    /**
     * when called this method adds the bonus and malus of the respective tile to the player
     * that got the excommunicated
     * @param player
     */
    public void execute(Player player) {
        if (malusOnAction != null) player.getBonusAndMalusOnAction().add(this.getMalusOnAction());
        if (malusOnGoods != null) player.getBonusAndMalusOnGoods().add(this.getMalusOnGoods());
        if (malusOnCost != null) player.getBonusAndMalusOnCost().add(this.getMalusOnCost());

        player.getExcommunicationTiles().add(this);

    }

    @Override
    public String toString() {
        return "ExcommunicationTile{" + "era=" + era + ", special='" + special + '\'' + ", malusOnAction=" + malusOnAction + ", malusOnGoods=" + malusOnGoods + ", malusOnCost=" + malusOnCost +  '\'' + '}';
    }
}
