package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Controllers.BonusAndMalusOnGoodsObtained;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Christian on 18/05/2017.
 */
public class ObtainEffect implements Effect{

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
    public void execute(PlayerStatus status) {
        activateBonusMalusOnGoodsObtained(status);
    }


    private void update(PlayerStatus status, HashMap<GoodType, Integer> hashMapGoodSet) {
        for(GoodType type : GoodType.values()){
            HashMap<GoodType,Integer> playerHashMapGoodSet = status.getActualGoodSet().getHashMapGoodSet();
            int actualAmount = playerHashMapGoodSet.get(type);
            actualAmount = actualAmount + hashMapGoodSet.get(type);
            playerHashMapGoodSet.put(type, actualAmount);
        }
    }

    private void activateBonusMalusOnGoodsObtained(PlayerStatus status){
        ArrayList<BonusAndMalusOnGoodsObtained> currentPLayerBonusMalus = status.getBonudAndMalusOnGoodsObtainedList();
        HashMap<GoodType, Integer> hashMapGoodsObtained = goodsObtained.getHashMapGoodSet();
        HashMap<GoodType, Integer> temporaryHashMapGoodSet = new HashMap<GoodType, Integer>(hashMapGoodsObtained);
        for (BonusAndMalusOnGoodsObtained playerBonusMalus : currentPLayerBonusMalus) {
            for(GoodType type : GoodType.values()) {
                if(hashMapGoodsObtained.get(type)*(playerBonusMalus.getGoodSetBonusMalus().getHashMapGoodSet().get(type)) !=0) {
                    int temporaryAmount = hashMapGoodsObtained.get(type) + playerBonusMalus.getGoodSetBonusMalus().getHashMapGoodSet().get(type);
                    temporaryHashMapGoodSet.put(type, temporaryAmount);
                }
            }
        }
        update(status, temporaryHashMapGoodSet);
    }


}
