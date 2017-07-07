package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 05/07/2017.
 */
public class Closed extends Input {

    @Override
    public void perform(Model model, Controller controller) {
        controller.clientClosed();
    }
}
