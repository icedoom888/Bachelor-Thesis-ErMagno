package it.polimi.ingsw.GC_29.Components;
/**
 * Created by AlbertoPennino on 18/05/2017.
 */
public class  Lane implements Cleanable {
    protected DevelopmentCard[] cards;
    protected int numberOfCardsPresent;
    protected CardColor color;

    public Lane(CardColor color){
        cards = new DevelopmentCard[6];
        numberOfCardsPresent=0;
        this.color= color;
    }

    public int getNumberOfCardsPresent() {
        return numberOfCardsPresent;
    }

    public CardColor getColor() {
        return color;
    }

    public void addCard(DevelopmentCard newCard) {
        this.cards[numberOfCardsPresent] = newCard;
        numberOfCardsPresent++;
    }

    public DevelopmentCard getCard(int position) {
        return cards[position];
    }

    private void removeCard(int position) {
        this.cards[position] = null;
    }

    public void clean() {
        for (int i = 0; i < numberOfCardsPresent; i++) {
            removeCard(i);
        }
        numberOfCardsPresent = 0;
    }
}