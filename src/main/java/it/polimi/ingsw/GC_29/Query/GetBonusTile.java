package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.BonusTile;
import it.polimi.ingsw.GC_29.Model.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christian on 19/06/2017.
 */
public class GetBonusTile extends Query<Map<Integer,String>> {

    @Override
    public Map<Integer, String> perform(Model model) {

        Map<Integer, String> bonusTileMap = new HashMap<>();

        Map<Integer, BonusTile> modelBonusTileMap = model.getBonusTileMap();

        for (Map.Entry<Integer, BonusTile> integerBonusTileEntry : modelBonusTileMap.entrySet()) {

            bonusTileMap.put(integerBonusTileEntry.getKey(), integerBonusTileEntry.getValue().toString());
        }

        return bonusTileMap;
    }
}
