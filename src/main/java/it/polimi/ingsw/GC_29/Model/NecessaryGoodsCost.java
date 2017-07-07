package it.polimi.ingsw.GC_29.Model;

/**
 * Created by Christian on 17/05/2017.
 */
public class NecessaryGoodsCost {

    private GoodType type;
    private int necessaryAmount;
    private int amountToPay;

    public NecessaryGoodsCost(GoodType type, int necessaryAmount, int amountToPay) {
        this.type = type;
        this.necessaryAmount = necessaryAmount;
        this.amountToPay = amountToPay;
    }

    public GoodType getType() {
        return type;
    }

    public int getNecessaryAmount() {
        return necessaryAmount;
    }

    public int getAmountToPay() {
        return amountToPay;
    }
}
