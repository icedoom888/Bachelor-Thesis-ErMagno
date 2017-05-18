package it.polimi.ingsw.GC_29.Components;

import java.util.HashMap;

/**
 * Created by Christian on 17/05/2017.
 */
public class GoodSet {

    private HashMap<GoodType,Integer> goodSet;

    public GoodSet(int wood, int stone, int coins, int workers, int victoryPoints, int militaryPoints, int faithPoints) {
        this.goodSet.put(GoodType.WOOD, wood);
        this.goodSet.put(GoodType.STONE, stone);
        this.goodSet.put(GoodType.COINS, coins);
        this.goodSet.put(GoodType.WORKERS, workers);
        this.goodSet.put(GoodType.VICTORYPOINTS, victoryPoints);
        this.goodSet.put(GoodType.MMILITARYPOINTS, militaryPoints);
        this.goodSet.put(GoodType.FAITHPOINTS, faithPoints);
    }

    public GoodSet() {
        this(0,0,0,0,0,0,0);
    }

    public HashMap<GoodType, Integer> getHashMapGoodSet() {
        return goodSet;
    }

    public GoodSet(GoodSet oldset){

        this.goodSet = new HashMap<GoodType, Integer>(oldset.goodSet);
    }

    public Good getGood(GoodType type){
        int amount = goodSet.get(type);
        Good newGood = new Good(type, amount);
        return newGood;
    }

    public int getGoodAmount(GoodType type){
        return goodSet.get(type);
    }
}
