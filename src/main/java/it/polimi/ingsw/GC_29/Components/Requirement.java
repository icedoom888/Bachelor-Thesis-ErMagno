package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public class Requirement {
    private GoodSet goodSetRequired;
    private HashMap<CardColor, Integer> cardsRequired;

    public Requirement(GoodSet goodSetRequired, HashMap<CardColor, Integer> cardsRequired) {

        this.goodSetRequired = goodSetRequired;
        this.cardsRequired = cardsRequired;
    }

    public GoodSet getGoodSetRequired() {
        return goodSetRequired;
    }

    public HashMap<CardColor, Integer> getCardsRequired() {
        return cardsRequired;
    }

    public boolean isPossible(Map<CardColor, Integer> playerCards, GoodSet playerGoodSet) {

        boolean isPossible = true;

        if (cardsRequired != null) {

            for (Map.Entry<CardColor, Integer> cardsRequiredEntry : cardsRequired.entrySet()) {

                CardColor cardColor = cardsRequiredEntry.getKey();

                if (cardColor != CardColor.ANY) {

                    isPossible = isPossible && (cardsRequiredEntry.getValue() <= playerCards.get(cardColor));
                }

                else {

                    isPossible = isPossible && anyColor(playerCards);
                }
            }

        }

        if (goodSetRequired != null) {
            for (GoodType goodType : GoodType.values()) {
                isPossible = isPossible && (getGoodSetRequired().getGoodAmount(goodType) <= playerGoodSet.getGoodAmount(goodType));
            }
        }

        return isPossible;
    }

    private boolean anyColor(Map<CardColor, Integer> playerCards) {

        for (CardColor cardColor : CardColor.values()) {

            if (cardColor != CardColor.ANY) {

                if (cardsRequired.get(CardColor.ANY) <= playerCards.get(cardColor)) {
                    return true;
                }

            }

        }

        return false;

    }
}
