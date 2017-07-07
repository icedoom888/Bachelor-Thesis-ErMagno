package it.polimi.ingsw.GC_29.Model;


import java.util.logging.Logger;


/**
 * Created by Christian on 18/05/2017.
 */

public class ObtainEffect implements Effect {

    private transient static final Logger LOGGER = Logger.getLogger(ObtainEffect.class.getName());

    protected GoodSet goodsObtained;


    public ObtainEffect(GoodSet goodSetObtained) {

        this.goodsObtained = goodSetObtained;
    }

    public ObtainEffect(int wood, int stone, int coins, int workers, int victoryPoints, int militaryPoints, int faithPoints) {

        this.goodsObtained = new GoodSet(wood, stone, coins, workers, victoryPoints, militaryPoints, faithPoints);
    }

    public ObtainEffect(){

        this.goodsObtained = new GoodSet();
    }

    public GoodSet getGoodsObtained() {
        return new GoodSet(goodsObtained);
    }


    @Override
    public void execute(Player status) {

        Filter.apply(status, goodsObtained);

        status.updateGoodSet(goodsObtained);
    }

    @Override
    public String toString() {

        return "ObtainEffect { " + "\n"
                + "goodsObtained = " + goodsObtained + "\n" + '}';
    }

    public void doubleResources() {
        goodsObtained.setGoodAmount(GoodType.WOOD, goodsObtained.getGoodAmount(GoodType.WOOD)*2);
        goodsObtained.setGoodAmount(GoodType.STONE, goodsObtained.getGoodAmount(GoodType.STONE)*2);
        goodsObtained.setGoodAmount(GoodType.COINS, goodsObtained.getGoodAmount(GoodType.COINS)*2);
        goodsObtained.setGoodAmount(GoodType.WORKERS, goodsObtained.getGoodAmount(GoodType.WORKERS)*2);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObtainEffect that = (ObtainEffect) o;

        return getGoodsObtained() != null ? getGoodsObtained().equals(that.getGoodsObtained()) : that.getGoodsObtained() == null;
    }

    @Override
    public int hashCode() {
        return getGoodsObtained() != null ? getGoodsObtained().hashCode() : 0;
    }
}
