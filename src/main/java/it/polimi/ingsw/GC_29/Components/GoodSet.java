package it.polimi.ingsw.GC_29.Components;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.Math.max;

/**
 * Created by Christian on 17/05/2017.
 */
public class GoodSet {

    private EnumMap<GoodType,Integer> goodSetMap;

    public GoodSet(int wood, int stone, int coins, int workers, int victoryPoints, int militaryPoints, int faithPoints) {

        this.goodSetMap = new EnumMap<>(GoodType.class);
        this.goodSetMap.put(GoodType.WOOD, wood);
        this.goodSetMap.put(GoodType.STONE, stone);
        this.goodSetMap.put(GoodType.COINS, coins);
        this.goodSetMap.put(GoodType.WORKERS, workers);
        this.goodSetMap.put(GoodType.VICTORYPOINTS, victoryPoints);
        this.goodSetMap.put(GoodType.MILITARYPOINTS, militaryPoints);
        this.goodSetMap.put(GoodType.FAITHPOINTS, faithPoints);
    }

    public GoodSet(EnumMap<GoodType,Integer> goodSetMap){
        this.goodSetMap = goodSetMap;
    }

    public GoodSet() {
        this(0,0,0,0,0,0,0);
    }

    public GoodSet(GoodSet oldset){
        this.goodSetMap = new EnumMap<>(oldset.goodSetMap);
    }

    public Map<GoodType, Integer> getMapGoodSet() {
        return goodSetMap;
    }

    public Good getGood(GoodType type){
        int amount = goodSetMap.get(type);
        return new Good(type, amount);
    }

    public int getGoodAmount(GoodType type){
        return goodSetMap.get(type);
    }

    public void setGoodAmount(GoodType type, int newAmount){

        goodSetMap.put(type, newAmount);
    }

    public void addGoodSet(GoodSet goodSetToAdd){
        for (GoodType type: GoodType.values()){
            this.goodSetMap.put(type, this.goodSetMap.get(type)+ goodSetToAdd.getMapGoodSet().get(type));
        }
    }

    public void subGoodSet(GoodSet goodSetToSub){
        for (GoodType type: GoodType.values()) {
            this.goodSetMap.put(type, this.goodSetMap.get(type) - goodSetToSub.getMapGoodSet().get(type));
        }
    }

    /**
     * This method compare two goodSets in order to see if the one which calls
     * has equal or more resources than the one called
     * @param compareSet is the goodSetMap to pay
     * @return true if this goodSetMap has equal or more resources than compareSet, false otherwise
     */
    public boolean enoughResources (GoodSet compareSet) {
        boolean enough = true;
        for (GoodType type : GoodType.values()) {
            enough = enough && this.goodSetMap.get(type) >= compareSet.getMapGoodSet().get(type);
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
        StringBuilder result = new StringBuilder();
        for(GoodType type : GoodType.values()){
            result.append(type + ": " + getGoodAmount(type) + " ");
       }
       return result.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        GoodSet goodSet1 = (GoodSet) o;

        for(GoodType goodType : GoodType.values()){

            if(goodSetMap.get(goodType) != goodSet1.getGoodAmount(goodType)){

                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return goodSetMap.hashCode();
    }
}
