package it.polimi.ingsw.GC_29.Components;
/**
 * Created by AlbertoPennino on 18/05/2017.
 */
public abstract class  Lane implements Cleanable {
    protected int numberOfCardsPresent;

    public void addCard(DevelopmentCard newCard){}
    public DevelopmentCard getCard(int position){
        return null;
    }
    private void removeCard(int position){}
    public SimpleSlot getSlot(int position){return null;}

    @Override
    public void clean(){
        for(int i=0;i<numberOfCardsPresent;i++){
            removeCard(i);
        }
        numberOfCardsPresent=0;
    }
}
