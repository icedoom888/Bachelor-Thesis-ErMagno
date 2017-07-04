package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.TimerTask;

/**
 * Created by Lorenzotara on 30/06/17.
 */
public class SuspendPlayer extends TimerTask {

    //private final long turnTime = 180000; // Three minutes to complete your turn
    private Controller controller;
    private GameStatus model;
    private Player playerToSuspend;


    public SuspendPlayer(Controller controller, GameStatus model, Player playerToSuspend) {
        this.controller = controller;
        this.model = model;
        this.playerToSuspend = playerToSuspend;
    }

    @Override
    public void run() {

        PlayerState playerState = playerToSuspend.getPlayerState();

        playerToSuspend.setPlayerState(PlayerState.SUSPENDED);

        switch (playerState) {

            case THROWDICES:

                try {
                    new ThrowDices().perform(model, controller);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //endTurn();

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

                try {
                    new Pray(false, playerToSuspend.getPlayerColor()).perform(model, controller);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;

            case WAITING:
                break;

            case CHOOSECOST:

                try {
                    new PayCard(0).perform(model, controller);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                endTurn();

                break;


            case CHOOSE_BONUS_TILE:

                try {
                    new BonusTileChosen().perform(model, controller);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;


            case SUSPENDED:
                break;

        }






        // viene chiamata con timer.schedule(task, 1000)
        // il numero di secondi indica quanto deve aspettare prima di partire
        // timer.cancel()



    }

    private void endTurn() {

        try {
            System.out.println("STO LANCIANDO END TURN DA SUSPEND PLAYER, PLAYER STATE: " + playerToSuspend.getPlayerState());
            new EndTurn().perform(model, controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
