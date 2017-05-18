package it.polimi.ingsw.GC_29.Components;

/**
 * Created by AlbertoPennino on 18/05/2017.
 */
public class SimpleSlot {
    protected DevelopmentCard card;

    public SimpleSlot() {
        this.card = null;
    }

    public void addCard(DevelopmentCard card) {
        this.card = card;
    }

    public DevelopmentCard getCard(){
        return this.card;
    }

    public void removeCard(){
        this.card = null;
    }
}

