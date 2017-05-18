package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class ExcommunicationTile {
    private Era era;
    private String name;
    private BonusAndMalusOnAction malusOnAction;
    private BonusAndMalusOnGoodsObtained malusOnEffect;
    private String description;

    public Era getEra() {
        return era;
    }

    public String getName() {
        return name;
    }

    public BonusAndMalusOnAction getMalusOnAction() {
        return malusOnAction;
    }

    public BonusAndMalusOnGoodsObtained getMalusOnEffect() {
        return malusOnEffect;
    }

    public String getDescription() {
        return description;
    }
}
