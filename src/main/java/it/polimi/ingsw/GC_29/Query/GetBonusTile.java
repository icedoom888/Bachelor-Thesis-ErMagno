package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Components.BonusTile;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christian on 19/06/2017.
 */
public class GetBonusTile extends Query<Map<Integer,String>> {

    @Override
    public Map<Integer, String> perform(GameStatus model) {

        Map<Integer, String> bonusTileMap = new HashMap<>();

        Map<Integer, BonusTile> modelBonusTileMap = model.getBonusTileMap();

        for (Integer index : modelBonusTileMap.keySet()) {

            BonusTile bonusTile = modelBonusTileMap.get(index);

            bonusTileMap.put(index, bonusTile.toString());

        }

        return bonusTileMap;
    }
}
