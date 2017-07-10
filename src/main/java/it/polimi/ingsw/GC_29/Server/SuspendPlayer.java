package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Controllers.Input.*;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Player;

import java.util.TimerTask;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by Lorenzotara on 30/06/17.
 *
 * SuspendPlayer extends TimerTask. Its run his called when the timer in the controller ends.
 */
public class SuspendPlayer extends TimerTask {

    private static final Logger LOGGER = Logger.getLogger(SuspendPlayer.class.getName());
    //private final long turnTime = 180000; // Three minutes to complete your turn
    private Controller controller;
    private Model model;
    private Player playerToSuspend;


    public SuspendPlayer(Controller controller, Model model, Player playerToSuspend) {
        this.controller = controller;
        this.model = model;
        this.playerToSuspend = playerToSuspend;
    }

    /**
     * run() set the player to suspended and switch his previous state in order
     * to complete in default what the player was doing.
     */
    @Override
    public void run() {

        PlayerState playerState = playerToSuspend.getPlayerState();

        playerToSuspend.setPlayerState(PlayerState.SUSPENDED);

        if (controller.minNumberOfPlayerReached()) {

            System.out.println("\n\nIN SUSPENDED: MIN NUMBER OF PLAYERS REACHED");

            try {
                controller.endGame();
            } catch (Exception e) {
                LOGGER.info((Supplier<String>) e);
            }
            return;
        }

        switch (playerState) {

            case THROWDICES:

                System.out.println("I AM SUSPENDING THE PLAYER DURING THROWDICES");

                new ThrowDices().perform(model, controller);

                break;

            case DOACTION:
            case CHOOSEACTION:
            case BONUSACTION:
            case ENDTURN:
            case CHOOSEWORKERS:
            case CHOOSE_EFFECT:
            case CHOOSE_COUNCIL_PRIVILEGE:
            case ACTIVATE_PAY_TO_OBTAIN_CARDS:

                endTurn();

                break;

            case PRAY:

                System.out.println("I AM SUSPENDING THE PLAYER DURING PRAY");


                new Pray(false, playerToSuspend.getPlayerColor()).perform(model, controller);

                break;


            case CHOOSECOST:

                System.out.println("I AM SUSPENDING THE PLAYER DURING CHOOSECOST");

                new PayCard(0).perform(model, controller);

                endTurn();

                break;


            case CHOOSE_BONUS_TILE:

                System.out.println("I AM SUSPENDING THE PLAYER DURING CHOOSE BONUS TILE");

                new BonusTileChosen().perform(model, controller);

                break;



            case SUSPENDED:
            case WAITING:
            default:
                break;

        }

    }

    private void endTurn() {


        System.out.println("STO LANCIANDO END TURN DA SUSPEND PLAYER, PLAYER STATE: " + playerToSuspend.getPlayerState());
        new EndTurn().perform(model, controller);

    }
}
