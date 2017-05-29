package it.polimi.ingsw.GC_29.Components;

import java.util.EnumMap;

import static java.lang.Math.max;

/**
 * Created by Christian on 17/05/2017.
 */
public class GoodSet {

    private EnumMap<GoodType,Integer> goodSet;

    public GoodSet(int wood, int stone, int coins, int workers, int victoryPoints, int militaryPoints, int faithPoints) {

        this.goodSet = new EnumMap<>(GoodType.class);
        this.goodSet.put(GoodType.WOOD, wood);
        this.goodSet.put(GoodType.STONE, stone);
        this.goodSet.put(GoodType.COINS, coins);
        this.goodSet.put(GoodType.WORKERS, workers);
        this.goodSet.put(GoodType.VICTORYPOINTS, victoryPoints);
        this.goodSet.put(GoodType.MILITARYPOINTS, militaryPoints);
        this.goodSet.put(GoodType.FAITHPOINTS, faithPoints);
    }

    public GoodSet(EnumMap<GoodType,Integer> goodSet){
        this.goodSet=goodSet;
    }

    public GoodSet() {
        this(0,0,0,0,0,0,0);
    }

    public GoodSet(GoodSet oldset){
        this.goodSet = new EnumMap<>(oldset.goodSet);
    }

    public EnumMap<GoodType, Integer> getEnumMapGoodSet() {
        return goodSet;
    }

    public Good getGood(GoodType type){
        int amount = goodSet.get(type);
        Good newGood = new Good(type, amount);
        return newGood;
    }

    public void setGoodSet(EnumMap<GoodType, Integer> goodSet) {
        this.goodSet = goodSet;
    }

    public int getGoodAmount(GoodType type){
        return goodSet.get(type);
    }

    public void setGoodAmount(GoodType type, int newAmount){

        goodSet.put(type, newAmount);
    }

    public void addGoodSet(GoodSet goodSetToAdd){
        for (GoodType type: GoodType.values()){
            this.goodSet.put(type, this.goodSet.get(type)+ goodSetToAdd.getEnumMapGoodSet().get(type));
        }
    }

    public void subGoodSet(GoodSet goodSetToSub){
        for (GoodType type: GoodType.values()) {
            this.goodSet.put(type, this.goodSet.get(type) - goodSetToSub.getEnumMapGoodSet().get(type));
        }
    }

    /**
     * This method compare two goodSets in order to see if the one which calls
     * has equal or more resources than the one called
     * @param compareSet is the goodSet to pay
     * @return true if this goodSet has equal or more resources than compareSet, false otherwise
     */
    public boolean enoughResources (GoodSet compareSet) {
        boolean enough = true;
        for (GoodType type : GoodType.values()) {
            enough = enough && this.goodSet.get(type) >= compareSet.getEnumMapGoodSet().get(type);
        }
        return enough;
    }

    public void setNonNegative(){
        for(GoodType type : GoodType.values()){

            int goodAmount = max(0, this.getGoodAmount(type));

            this.setGoodAmount(type, goodAmount);
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
