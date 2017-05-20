package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Christian on 18/05/2017.
 * Upgraded by Icedoom on 19/05/2017.
 */
public class ObtainEffect implements Effect {

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

    @Override
    public void execute(Player player) {
        GoodSet newGoodsObtained = activateBonusMalusOnGoods(player.getStatus().getBonusAndMalusOnGoodsObtainedList(),goodsObtained);
        update(player.getStatus(), newGoodsObtained);
    }


    protected void update(PlayerStatus status, GoodSet goodSet) {
       status.updateGoodSet(goodSet);
    }

    protected GoodSet activateBonusMalusOnGoods(ArrayList<BonusAndMalusOnGoods> currentPLayerBonusMalus,GoodSet goodset){
        HashMap<GoodType, Integer> hashMapGoods = goodset.getHashMapGoodSet();
        HashMap<GoodType, Integer> temporaryHashMapGoodSet = new HashMap<GoodType, Integer>(hashMapGoods);
        for (BonusAndMalusOnGoods playerBonusMalus : currentPLayerBonusMalus) {
            for(GoodType type : GoodType.values()) {
                if(hashMapGoods.get(type)*(playerBonusMalus.getGoodSetBonusMalus().getHashMapGoodSet().get(type)) !=0) {
                    int temporaryAmount = hashMapGoods.get(type) + playerBonusMalus.getGoodSetBonusMalus().getHashMapGoodSet().get(type);
                    temporaryHashMapGoodSet.put(type, temporaryAmount);
                }
            }
        }
        GoodSet goodSet = new GoodSet(temporaryHashMapGoodSet);
        return goodSet;
    }
}
