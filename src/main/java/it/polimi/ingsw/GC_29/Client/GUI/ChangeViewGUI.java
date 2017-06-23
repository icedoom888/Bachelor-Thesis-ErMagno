package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.Client.ClientSocket.CommonOut;
import it.polimi.ingsw.GC_29.Client.ClientSocket.CommonView;
import it.polimi.ingsw.GC_29.Client.GUI.BonusTile.BonusTileGUI;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardGUI;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import javafx.application.Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class ChangeViewGUI {

    private final ObjectInputStream socketIn;
    private final CommonView commonView;
    private CommonOut commonOut;
    private static GameBoardController gameBoardController;

    public ChangeViewGUI(ObjectInputStream socketIn, CommonView commonView) {
        this.socketIn = socketIn;
        this.commonView = commonView;
    }



    public void change(PlayerState currentPlayerState) {

        commonView.getInputChecker().setCurrentPlayerState(currentPlayerState);

        try {
            commonOut.handlePlayerState(currentPlayerState);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        switch (currentPlayerState){

            /*case DOACTION:

                try {
                    String input = (String)socketIn.readObject();

                    if (input.contentEquals("Get Family Pawns Availability")) {
                        getFamilyPawnsAvailability();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case BONUSACTION:
            case CHOOSEACTION:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Valid Actions")) {
                        validActions();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            //TODO: inserire gestione altri stati se necessario

            case CHOOSEWORKERS:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Get Cards For Workers")) {
                        getCardsForWorkers();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case ACTIVATE_PAY_TO_OBTAIN_CARDS:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Get Pay To Obtain Cards")) {
                        getPayToObtainCards();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case CHOOSECOST:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Get Possible Costs")) {
                        getPossibleCosts();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case CHOOSE_COUNCIL_PRIVILEGE:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Get Council Privileges")) {
                        getCouncilPrivileges();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break; */

            case CHOOSE_BONUS_TILE:

                try {
                    String input = (String)socketIn.readObject();

                    System.out.println(input);

                    if (input.contentEquals("Get Bonus Tile")) {
                        getBonusTilesGUI();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

        }
    }

    private void getBonusTilesGUI() {
        Application.launch(GameBoardGUI.class);
        Application.launch(BonusTileGUI.class);
    }


    public void setCommonOut(CommonOut commonOut) {
        this.commonOut = commonOut;
    }


    public static void setGameBoardController(GameBoardController gameBoardController) {
        ChangeViewGUI.gameBoardController = gameBoardController;
    }


}
