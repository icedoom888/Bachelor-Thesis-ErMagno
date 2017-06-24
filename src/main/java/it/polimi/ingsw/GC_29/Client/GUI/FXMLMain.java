package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import it.polimi.ingsw.GC_29.Client.ClientSocket.*;
import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginChange;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginController;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Server.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lorenzotara on 23/06/17.
 */
public class FXMLMain extends Application implements Observer<GUIChange> {

    private Distribution connection;
    private String username;
    private String password;
    private boolean logged;
    private LoginController loginController;
    private ClientSocketGUI clientSocketGUI;
    private Stage stage;
    private Stage loginStage;
    private Stage gameboardStage;
    private GameBoardController gameBoardController;

    @Override
    public void update(GUIChange o) throws Exception {

        if (o instanceof LoginChange) {
            Boolean connected = ((LoginChange) o).getConnected();
            if (connected) {

                switch (connection){

                    case SOCKET:
                        signUpSocket();
                        break;

                    case RMI:
                        break;
                }
            }
        }

    }



    @Override
    public void update() {

    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        ///////LOGIN///////

        System.out.println("Sono dentro a FXMLMain");

        //////LOGIN SETTING/////


        setLogin();


        ////////////////////////////////////////////////////////


    }

    private void connectSocket() {

        try {

            clientSocketGUI = new ClientSocketGUI();
            clientSocketGUI.startClientGUI();
            logged = clientSocketGUI.loginGUI(username, password);

            if (!logged) {
                loginController.setConnected(false);
            }



        } catch (Exception e) {

            System.out.println("Exception: " + e);
            e.printStackTrace();

        }

    }

    public void signUpSocket() {

        while (!logged) {


            this.connection = loginController.getConnection();


            this.username = loginController.getUsername();
            this.password = loginController.getPassword();

            switch (connection) {

                case SOCKET:

                    connectSocket();

                    break;

                case RMI:
                    break;
            }
        }

        // Login Successful

        loginStage.close();

        ///////WAITING STAGE//////////
        //TODO:
        ///////WAITING STAGE//////////




        switch (connection) {

            case SOCKET:

                clientSocketGUI.playNewGameGUI();

                clientSocketGUI.getClientInHandlerGUI().addListener(new GuiChangeListener() {

                    @Override
                    public void onReadingChange(StartGameChange startGameChange) {
                        //TODO: da fare
                        updateView();
                    }

                    @Override
                    public void onReadingChange(ValidActionsChange validActionsChange) {
                        updateValidActions(validActionsChange.getValidActionList());

                    }

                    @Override
                    public void onReadingChange(GoodSetChange goodSetChange) {
                        updateGoodSet(goodSetChange.getGoodSet());
                    }

                    @Override
                    public void onReadingChange(CardsChange cardsChange) {

                        switch (cardsChange.getType()) {

                            case "development":
                                updatePersonalCards(cardsChange.getDevelopmentCards());
                                break;

                            case "tower":
                                updateCardsOnTower(cardsChange.getDevelopmentCards());
                                break;
                        }
                    }

                    @Override
                    public void onReadingChange(FamilyPawnChange familyPawnChange) {
                        updateFamilyPawns(familyPawnChange.getFamilyPawns());
                    }

                    @Override
                    public void onReadingChange(CardsForWorkersChange cardsForWorkersChange) {
                        chooseWorkers(cardsForWorkersChange.getCardsForWorkers());
                    }

                    @Override
                    public void onReadingChange(PayToObtainCardsChange payToObtainCardsChange) {
                        choosePayToObtainCards(payToObtainCardsChange.getPayToObtainCards());
                    }

                    @Override
                    public void onReadingChange(PossibleCostsChange possibleCostsChange) {
                        chooseCost(possibleCostsChange.getPossibleCosts());
                    }

                    @Override
                    public void onReadingChange(CouncilPrivilegeChange councilPrivilegeChange) {
                        chooseCouncilPrivilege(councilPrivilegeChange.getCouncilPrivileges());
                    }

                    @Override
                    public void onReadingChange(BonusTileChange bonusTileChange) {
                        chooseBonusTile(bonusTileChange.getBonusTiles());
                    }


                });
                break;

            case RMI:
                break;
        }


        /////GAMEBOARD SETTING///////

        setGameboard();



    }


    private void setLogin() {

        loginStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        SplitPane loginRoot = null;

        try {
            loginRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ROOT: " + loginRoot);

        loginStage.setScene(new Scene(loginRoot));
        loginStage.setTitle("Login");
        loginStage.setHeight(400);
        loginStage.setWidth(500);
        loginStage.centerOnScreen();
        loginStage.show();


        loginController = loader.getController();


        logged = false;

        loginController.setConnected(false);

        loginController.registerObserver(this);

    }


    private void setGameboard() {

        System.out.println("Gameboard Started");
        gameboardStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));
        AnchorPane gameboardRoot = null;

        try {
            gameboardRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameboardStage.setScene(new Scene(gameboardRoot));
        gameboardStage.setHeight(700);
        gameboardStage.setWidth(1100);
        gameboardStage.centerOnScreen();
        gameboardStage.setTitle("Lorenzo il Magnifico");
        gameboardStage.show();

        gameBoardController = loader.getController();
        ChooseDistribution chooseDistribution = new ChooseDistribution(Distribution.SOCKET);
        chooseDistribution.setCommonOutSocket(clientSocketGUI.getClientOutHandlerGUI().getCommonOutSocket());
        gameBoardController.setChooseDistribution(chooseDistribution);

    }


















    private void chooseBonusTile(Map<Integer, String> bonusTiles) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.chooseBonusTile(bonusTiles);


            }
        });

    }

    private void chooseCouncilPrivilege(List<Integer> councilPrivileges) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.choosePrivileges(councilPrivileges);


            }
        });

    }

    private void chooseCost(Map<Integer, String> possibleCosts) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.chooseCost(possibleCosts);


            }
        });

    }

    private void choosePayToObtainCards(Map<String, HashMap<Integer, String>> payToObtainCards) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.choosePayToObtainCards(payToObtainCards);


            }
        });
    }

    private void chooseWorkers(Map<Integer, ArrayList<String>> cardsForWorkers) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.chooseWorkers(cardsForWorkers);


            }
        });

    }

    private void updateFamilyPawns(Map<FamilyPawn, Boolean> familyPawns) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.activatePawns(familyPawns);


            }
        });

    }


    private void updatePersonalCards(List<String> developmentCards) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                //TODO: impl controller, bisogna passare il nome delle carte non il toString

                //gameBoardController.updateCardsPersonalBoard(developmentCards, cardColor);


            }
        });

    }

    private void updateCardsOnTower(List<String> developmentCards) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                //TODO: impl controller, bisogna passare il nome delle carte non il toString

                //gameBoardController.updateTower(developmentCards, cardColor);


            }
        });

    }

    private void updateGoodSet(GoodSet goodSet) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.updateGoodSetPersonalBoard(goodSet);


            }
        });

    }

    private void updateValidActions(Map<Integer, String> validActions) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.updatePossibleActions(validActions);


            }
        });

    }

    private void updateView() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                //TODO: impl

                //loginController.showError();


            }
        });
    }





}
