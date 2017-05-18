package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Controllers.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.Controllers.BonusAndMalusOnEffect;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class ExcommunicationTile {
    private Era era;
    private String name;
    private BonusAndMalusOnAction malusOnAction;
    private BonusAndMalusOnEffect malusOnEffect;
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

    public BonusAndMalusOnEffect getMalusOnEffect() {
        return malusOnEffect;
    }

    public String getDescription() {
        return description;
    }
}
