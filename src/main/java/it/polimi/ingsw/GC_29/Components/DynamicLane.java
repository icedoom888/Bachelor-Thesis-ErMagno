package it.polimi.ingsw.GC_29.Components;
import java.util.ArrayList;
/**
 * Created by AlbertoPennino on 18/05/2017.
 */
public class DynamicLane extends Lane{
    private ArrayList<SimpleSlot> cards;

    public DynamicLane(){
        cards = new ArrayList<SimpleSlot>();
        numberOfCardsPresent=0;
    }

    @Override
    public void addCard(DevelopmentCard newCard){
        cards.add(numberOfCardsPresent,new SimpleSlot());
        cards.get(numberOfCardsPresent).addCard(newCard);
        numberOfCardsPresent++;
    }

    @Override
    public DevelopmentCard getCard(int position){
        return cards.get(position).getCard();
    }

    private void removeCard(int position){
        cards.remove(position);
    }

    @Override
    public SimpleSlot getSlot(int position){
        return cards.get(position);
    }

}
