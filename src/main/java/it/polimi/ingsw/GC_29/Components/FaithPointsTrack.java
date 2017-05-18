package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class FaithPointsTrack implements Cleanable {
    private FaithPointsPawnSlot[] faithPointsTrack;
    private int numberOfFaithPoints;

    public FaithPointsTrack(int numberOfFaithPoints) {
        this.faithPointsTrack = new FaithPointsPawnSlot[numberOfFaithPoints];
    }

    public FaithPointsPawnSlot[] getFaithPointsTrack() {
        return faithPointsTrack;
    }

    public void movePawn(int position, Pawn pawn) {

        //TODO I'm not sure how to handle this behavior, discuss with Lorenzo and Alberto
    }

    @Override
    public void clean() {
        for (FaithPointsPawnSlot faithPointsPawnSlot : faithPointsTrack) {
            if(!(faithPointsPawnSlot.isFree())){
                faithPointsPawnSlot.clearSlot();
            }
        }
    }
}
