package it.polimi.ingsw.GC_29.Server.Query;

import it.polimi.ingsw.GC_29.Components.DevelopmentCard;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.WorkAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class GetPayToObtainCards extends Query<Map<String, HashMap<Integer, String>>> {

    @Override
    public Map<String, HashMap<Integer, String>> perform(GameStatus model) {

        WorkAction workAction = (WorkAction)model.getCurrentPlayer().getCurrentAction();

        Map<String, HashMap<Integer, String>> payToObtainCardMap = new HashMap<>();

        for(DevelopmentCard card : workAction.getPayToObtainCardsMap().values()){

            payToObtainCardMap.put(card.getSpecial(), new HashMap<>());

            //payToObtainCardMap.put(card.toString(), new HashMap<>());

            for(Effect effect : card.getPermanentEffect()){

                int effectIndex = card.getPermanentEffect().indexOf(effect);

                payToObtainCardMap.get(card.getSpecial()).put(effectIndex, effect.toString());

                //payToObtainCardMap.get(card.toString()).put(effectIndex, effect.toString());

            }
        }

        return payToObtainCardMap;
    }
}
