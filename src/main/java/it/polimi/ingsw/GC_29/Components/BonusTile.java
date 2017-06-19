package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

/**
 * Created by AlbertoPennino on 18/05/2017.
 */
public class BonusTile {
    private ObtainEffect productionEffect;
    private ObtainEffect harvestEffect;

    public BonusTile(ObtainEffect productionEffect, ObtainEffect harvestEffect) {
        this.productionEffect = productionEffect;
        this.harvestEffect = harvestEffect;
    }



    public ObtainEffect getProductionEffect() {
        return productionEffect;
    }

    public ObtainEffect getHarvestEffect() {
        return harvestEffect;
    }

    @Override
    public String toString() {
        return "BonusTile{" +
                "productionEffect = " + productionEffect + "\n"
                + ", harvestEffect = " + harvestEffect + "\n"
                + '}';
    }
}
