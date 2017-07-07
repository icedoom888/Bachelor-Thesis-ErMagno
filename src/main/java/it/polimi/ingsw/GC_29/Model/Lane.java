package it.polimi.ingsw.GC_29.Model;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * Created by AlbertoPennino on 18/05/2017.
 */
public class  Lane implements Cleanable {
    protected DevelopmentCard[] cards;
    protected int firstFreeSlotIndex;

    public Lane(int laneDimension){
        cards = new DevelopmentCard[laneDimension];
        firstFreeSlotIndex = 0;
    }

    public int getFirstFreeSlotIndex() {
        return firstFreeSlotIndex;
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

    public String toTable() {
        AsciiTable laneTable = new AsciiTable();
        int i = 0;
        laneTable.addRule();
        laneTable.addRow("Index","Card");
        for (DevelopmentCard developmentCard : cards){
            if (developmentCard!= null) {
                laneTable.addRule();
                laneTable.addRow(i + ")","Card: \n" + developmentCard.toString());
            }
            else {
                laneTable.addRule();
                laneTable.addRow(i + ")","No card");
            }
            i++;
        }
        laneTable.setTextAlignment(TextAlignment.CENTER);
        laneTable.addRule();
        return laneTable.render() + "\n\n\n";
    }
}