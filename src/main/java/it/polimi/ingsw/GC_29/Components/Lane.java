package it.polimi.ingsw.GC_29.Components;
/**
 * Created by AlbertoPennino on 18/05/2017.
 */
public class  Lane implements Cleanable {
    protected DevelopmentCard[] cards;
    protected int firstFreeSlotIndex;
    protected CardColor color;

    public Lane(CardColor color, int laneDimension){
        cards = new DevelopmentCard[laneDimension];
        firstFreeSlotIndex =0;
        this.color= color;
    }

    public int getFirstFreeSlotIndex() {
        return firstFreeSlotIndex;
    }

    public CardColor getColor() {
        return color;
    }

    public void addCard(DevelopmentCard newCard) {
        if (firstFreeSlotIndex < cards.length) {
            this.cards[firstFreeSlotIndex] = newCard;
            firstFreeSlotIndex++;
        } else {

            System.out.println("There has been an error, you can't add a card");
        }
    }

    public DevelopmentCard getCard(int position) {
        return cards[position];
    }

    public DevelopmentCard[] getCards() {
        return cards;
    }

    private void removeCard(int position) {
        this.cards[position] = null;
    }

    public void clean() {
        for (int i = 0; i < firstFreeSlotIndex; i++) {
            removeCard(i);
        }
        firstFreeSlotIndex = 0;
    }

    public boolean isFree() {
        return firstFreeSlotIndex < cards.length;
    }
}