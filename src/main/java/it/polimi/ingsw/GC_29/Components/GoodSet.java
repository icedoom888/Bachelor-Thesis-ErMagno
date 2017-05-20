package it.polimi.ingsw.GC_29.Components;

import java.util.HashMap;

/**
 * Created by Christian on 17/05/2017.
 */
public class GoodSet {

    private HashMap<GoodType,Integer> goodSet;

    public GoodSet(int wood, int stone, int coins, int workers, int victoryPoints, int militaryPoints, int faithPoints) {
        this.goodSet = new HashMap<GoodType,Integer>();
        this.goodSet.put(GoodType.WOOD, wood);
        this.goodSet.put(GoodType.STONE, stone);
        this.goodSet.put(GoodType.COINS, coins);
        this.goodSet.put(GoodType.WORKERS, workers);
        this.goodSet.put(GoodType.VICTORYPOINTS, victoryPoints);
        this.goodSet.put(GoodType.MMILITARYPOINTS, militaryPoints);
        this.goodSet.put(GoodType.FAITHPOINTS, faithPoints);
    }

    public GoodSet(HashMap<GoodType,Integer> goodSet){
        this.goodSet=goodSet;
    }
    public GoodSet() {
        this(0,0,0,0,0,0,0);
    }
    public GoodSet(GoodSet oldset){
        this.goodSet = new HashMap<GoodType, Integer>(oldset.goodSet);
    }

    public HashMap<GoodType, Integer> getHashMapGoodSet() {
        return goodSet;
    }

    public Good getGood(GoodType type){
        int amount = goodSet.get(type);
        Good newGood = new Good(type, amount);
        return newGood;
    }

    public void setGoodSet(HashMap<GoodType, Integer> goodSet) {
        this.goodSet = goodSet;
    }

    public int getGoodAmount(GoodType type){
        return goodSet.get(type);
    }

    public void addGoodSet(GoodSet goodSetToAdd){
        for (GoodType type: GoodType.values()){
            this.goodSet.put(type, this.goodSet.get(type)+ goodSetToAdd.getHashMapGoodSet().get(type));
        }
    }
    public void printGoodSet(){
        for(GoodType type : GoodType.values()){
            System.out.print(type+": "+getGoodAmount(type)+"/n");
        }
    }

    @Override
    public String toString() {
        String result = new String();
        for(GoodType type : GoodType.values()){
            result = result + type + ": " + getGoodAmount(type) + " ";
       }
       return result;
    }
}
