package it.polimi.ingsw.GC_29.Client.GUI;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public class PayToObtainCardsChange {

    private Map<String, HashMap<Integer, String>> payToObtainCards;

    public PayToObtainCardsChange(Map<String, HashMap<Integer, String>> payToObtainCards) {

        this.payToObtainCards = payToObtainCards;

    }

    public Map<String, HashMap<Integer, String>> getPayToObtainCards() {
        return payToObtainCards;
    }
}
