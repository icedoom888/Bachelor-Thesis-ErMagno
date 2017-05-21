package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Floor<T extends DevelopmentCard> {
    private T developmentCard;
    private ActionSpace actionSpace;

    public void setDevelopmentCard(T developmentCard) {
        this.developmentCard = developmentCard;
    }

    public T getDevelopmentCard() {
        return developmentCard;
    }

    public ActionSpace getActionSpace() {
        return actionSpace;
    }
}
