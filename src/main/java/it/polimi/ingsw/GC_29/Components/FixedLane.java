package it.polimi.ingsw.GC_29.Components;

/**
 * Created by AlbertoPennino on 18/05/2017.
 */
public class FixedLane extends Lane {
    private SimpleSlot[] cards;

    public FixedLane(int numberOfSlots,String typeOfSlots){
        if(typeOfSlots.equals("Simple")) {
            cards = new SimpleSlot[numberOfSlots];
            numberOfCardsPresent = 0;
        }
        if(typeOfSlots.equals("Simple")){
            cards = new ComplexSlot[numberOfSlots];
            numberOfCardsPresent = 0;
        }
    }

    @Override
    public void addCard(DevelopmentCard newCard){
        this.cards[numberOfCardsPresent].addCard(newCard);
        this.numberOfCardsPresent++;
    }

    @Override
    public DevelopmentCard getCard(int position){
        return cards[position].getCard();
    }

    private void removeCard(int position){
        this.cards[position].removeCard();
    }

    @Override
    public SimpleSlot getSlot(int position){
        return cards[position];
    }
}
