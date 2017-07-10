package it.polimi.ingsw.GC_29.Controllers.Change;

import it.polimi.ingsw.GC_29.Model.Era;

/**
 * Created by Lorenzotara on 10/07/17.
 */
public class Excommunicated extends Change {

    private Era excommunication;

    public Excommunicated(Era excommunication) {
        this.excommunication = excommunication;
    }

    public Era getExcommunication() {
        return excommunication;
    }
}
