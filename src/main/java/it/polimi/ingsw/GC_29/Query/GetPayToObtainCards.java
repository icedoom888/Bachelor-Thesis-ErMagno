package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.DevelopmentCard;
import it.polimi.ingsw.GC_29.Controllers.Model;
import it.polimi.ingsw.GC_29.Model.Effect;
import it.polimi.ingsw.GC_29.Model.PayToObtainEffect;
import it.polimi.ingsw.GC_29.Model.WorkAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class GetPayToObtainCards extends Query<Map<String, HashMap<Integer, String>>> {

    @Override
    public Map<String, HashMap<Integer, String>> perform(Model model) {

        WorkAction workAction = (WorkAction)model.getCurrentPlayer().getCurrentAction();

        Map<String, HashMap<Integer, String>> payToObtainCardMap = new HashMap<>();

        for(DevelopmentCard card : workAction.getPayToObtainCardsMap().values()){

            payToObtainCardMap.put(card.getSpecial(), new HashMap<>());

            //payToObtainCardMap.put(card.toString(), new HashMap<>());

            for(Effect effect : card.getPermanentEffect()){

                if(effect instanceof PayToObtainEffect){

                    PayToObtainEffect effect1 = (PayToObtainEffect) effect;

                    if(effect1.checkSufficientGoods(model.getCurrentPlayer())){

                        //System.out.println("LA CARTA AGGIIUNTA NELLA PAY TO OBTAIN MAP E' " + cardKey);
                        int effectIndex = card.getPermanentEffect().indexOf(effect);

                        payToObtainCardMap.get(card.getSpecial()).put(effectIndex, effect.toString());

                    }

                }

                //payToObtainCardMap.get(card.toString()).put(effectIndex, effect.toString());

            }
        }

        return payToObtainCardMap;
    }

}
