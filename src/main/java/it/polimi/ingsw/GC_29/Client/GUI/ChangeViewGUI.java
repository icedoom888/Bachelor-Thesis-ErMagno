package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.Client.ClientSocket.CommonOutSocket;
import it.polimi.ingsw.GC_29.Client.ClientSocket.CommonView;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class ChangeViewGUI {

    private final ObjectInputStream socketIn;
    private CommonView commonView;
    private CommonOutSocket commonOutSocket;
    private static GameBoardController gameBoardController;
    private Stage gameboardStage;

    public ChangeViewGUI(ObjectInputStream socketIn, CommonView commonView) {
        this.socketIn = socketIn;
        this.commonView = commonView;
    }



    public void change(PlayerState currentPlayerState) {

        commonView.getInputChecker().setCurrentPlayerState(currentPlayerState);

        try {
            commonOutSocket.handlePlayerState(currentPlayerState);
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

                        //Application.launch(GameBoardGUI.class);

                        launchGameboard();

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

    private void launchGameboard() {

        /*

        Stage gameboardStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));

        AnchorPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Launching GameBoard: " + root);
        gameboardStage.setScene(new Scene(root));
        gameboardStage.setTitle("Login");
        gameboardStage.setHeight(400);
        gameboardStage.setWidth(500);
        gameboardStage.centerOnScreen();
        gameboardStage.show();

        gameBoardController = loader.getController();


*/
        /*

        Platform.setImplicitExit(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("Platform run later");


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        gameboardStage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));

                        AnchorPane root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        System.out.println("Launching GameBoard: " + root);
                        gameboardStage.setScene(new Scene(root));
                        gameboardStage.setTitle("Login");
                        gameboardStage.setHeight(400);
                        gameboardStage.setWidth(500);
                        gameboardStage.centerOnScreen();
                        gameboardStage.show();

                        gameBoardController = loader.getController();

                    }
                });

            }
        }).start();

        */

    }

    private void getBonusTilesGUI() {

        while (true);
        //Application.launch(BonusTileGUI.class);
    }


    public void setCommonOutSocket(CommonOutSocket commonOutSocket) {
        this.commonOutSocket = commonOutSocket;
    }


    public static void setGameBoardController(GameBoardController gameBoardController) {
        ChangeViewGUI.gameBoardController = gameBoardController;
    }

    public CommonView getCommonView() {
        return commonView;
    }

    public CommonOutSocket getCommonOutSocket() {
        return commonOutSocket;
    }

    public void setCommonView(CommonView commonView) {
        this.commonView = commonView;
    }
}
