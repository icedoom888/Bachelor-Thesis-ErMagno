package it.polimi.ingsw.GC_29.Components;

import de.vandermeer.asciitable.AsciiTable;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Market implements Cleanable {

    private EnumMap<ShopName, ActionSpace> houses;

    public Market(int numberOfPlayers){

        this.houses = new EnumMap<ShopName, ActionSpace>(ShopName.class);
        houses.put(ShopName.MONEYSHOP,new ActionSpace(new ObtainEffect(0,0,5,0,0,0,0),1,new PawnSlot(1,true),true,false));
        houses.put(ShopName.WORKERSHOP,new ActionSpace(new ObtainEffect(0,0,0,5,0,0,0),1,new PawnSlot(1,true),true,false));
        if (numberOfPlayers>=4){
            houses.put(ShopName.MILITARYANDCOINSSHOP,new ActionSpace(new ObtainEffect(0,0,2,0,0,3,0),1,new PawnSlot(1,true),true,false));
            houses.put(ShopName.PRIVILEGESHOP,new ActionSpace(new CouncilPrivilegeEffect(1),1,new PawnSlot(1,true),true,false));

        }
        else{
            houses.put(ShopName.MILITARYANDCOINSSHOP,null);
            houses.put(ShopName.PRIVILEGESHOP,null);
        }
    }

    public Map<ShopName, ActionSpace> getHouses() {
        return houses;
    }

    public ActionSpace getShop(ShopName shopName) {
        return houses.get(shopName);
    }

    @Override
    public void clean() {

        for (ActionSpace actionSpace : houses.values()) {
            actionSpace.clean();
        }
    }

    public String toTable() {
        AsciiTable marketTable = new AsciiTable();

        for (Map.Entry<ShopName, ActionSpace> shopNameActionSpaceEntry : houses.entrySet()) {
            marketTable.addRule();
            marketTable.addRow(shopNameActionSpaceEntry.getKey(), shopNameActionSpaceEntry.getValue().toString());
        }


        return "\n\n\n" + "MARKET \n\n" + marketTable.render();
    }
}
