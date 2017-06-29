package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import it.polimi.ingsw.GC_29.Client.ClientSocket.*;
import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.GUI.BonusTile.BonusTileController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseCost.ChooseCostController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect.ChooseEffectController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect.PayToObtainController;
import it.polimi.ingsw.GC_29.Client.GUI.ChoosePrivilege.ChoosePrivilegeController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseWorkers.WorkersController;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginChange;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginController;
import it.polimi.ingsw.GC_29.Client.GUI.Pray.PrayController;
import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
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
public class FXMLMain extends Application implements Observer<LoginChange> {

    private Distribution connection;
    private String username;
    private String password;
    private boolean logged;
    private LoginController loginController;
    private ClientSocketGUI clientSocketGUI;
    private Stage stage;
    private Stage loginStage;
    private Stage gameboardStage;
    private PlayerState currentPlayerState;

    private GameBoardController gameBoardController;

    @Override
    public void update(LoginChange o) throws Exception {

        Boolean connected = ((LoginChange) o).getConnected();
        if (connected) {

            signUp();


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
                clientSocketGUI.getSocket().close();
            }



        } catch (Exception e) {

            System.out.println("Exception: " + e);
            e.printStackTrace();

        }

    }

    public void signUp() {

        if (!logged) {


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


            if (!logged) {

                loginController.showError();
                //loginController.connectionStable();
            }

        }


        if (logged) {

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

                        /*@Override
                        public void onReadingChange(CardsChange cardsChange) {

                            //TODO: sbagliato

                            switch (cardsChange.getType()) {

                                case "development":
                                    updatePersonalCards(cardsChange.getDevelopmentCards());
                                    break;

                                case "tower":
                                    updateCardsOnTower(cardsChange.getDevelopmentCards());
                                    break;
                            }
                        }*/

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

                        @Override
                        public void onReadingChange(PlayerStateChange playerStateChange) {
                            setGuiOnState(playerStateChange.getNewPlayerState());
                        }

                        @Override
                        public void onReadingChange(TowerCardsChange towerCardsChange) {
                            updateTower(towerCardsChange.getCards(), towerCardsChange.getCardColor());
                        }

                        @Override
                        public void onReadingChange(PersonalCardChange personalCardChange) {
                            updatePersonalCards(personalCardChange.getCardName(), personalCardChange.getCardColor());
                        }

                        @Override
                        public void onReadingChange(TrackChange trackChange) {
                            updateTrack(trackChange.getPlayerColor(), trackChange.getGoodType(), trackChange.getNumberOfPoints());
                        }

                        @Override
                        public void onReadingChange(AddPawnChange addPawnChange) {
                            updatePawn(addPawnChange.getFamilyPawn(), addPawnChange.getActionIndex());
                        }

                        @Override
                        public void onReadingChange(ClearPawns clearPawns) {
                            gameBoardController.removeAllPawns();
                        }


                    });
                    break;

                case RMI:
                    break;
            }


            /////GAMEBOARD SETTING///////

            setGameboard();
        }


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

        loginStage.setScene(new Scene(loginRoot));
        loginStage.setTitle("Login");
        loginStage.setHeight(400);
        loginStage.setWidth(600);
        loginStage.centerOnScreen();
        loginStage.show();


        loginController = loader.getController();


        logged = false;

        loginController.setConnected(false);
        loginController.registerObserver(this);

    }


    private void setGameboard() {

        System.out.println("Gameboard Started");
        ChooseDistribution chooseDistribution = new ChooseDistribution(Distribution.SOCKET);
        chooseDistribution.setCommonOutSocket(clientSocketGUI.getClientOutHandlerGUI().getCommonOutSocket());

        gameboardStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));
        AnchorPane gameboardRoot = null;


        try {

            gameboardRoot = loader.load();
            gameBoardController = loader.getController();
            gameBoardController.setChooseDistribution(chooseDistribution);

            //aggiunta bonusTile
            FXMLLoader loaderBonus = new FXMLLoader(getClass().getResource("/FXML/BonusTile.fxml"));
            AnchorPane childBonus = loaderBonus.load();
            gameboardRoot.getChildren().add(childBonus);
            AnchorPane.setBottomAnchor(childBonus,200.0);
            AnchorPane.setLeftAnchor(childBonus,200.0);
            childBonus.setVisible(false);
            BonusTileController bonusTileController = loaderBonus.getController();
            bonusTileController.setSender(chooseDistribution);
            bonusTileController.setGameBoardController(gameBoardController);
            gameBoardController.setBonusTileController(bonusTileController);
            gameBoardController.setBonusTilePane(childBonus);

            //aggiunta ChooseCost
            FXMLLoader loaderCost = new FXMLLoader(getClass().getResource("/FXML/ChooseCost.fxml"));
            AnchorPane childCost = loaderCost.load();
            gameboardRoot.getChildren().add(childCost);
            AnchorPane.setBottomAnchor(childCost,200.0);
            AnchorPane.setLeftAnchor(childCost,200.0);
            childCost.setVisible(false);
            ChooseCostController chooseCostController = loaderCost.getController();
            chooseCostController.setSender(chooseDistribution);
            gameBoardController.setChooseCostController(chooseCostController);
            gameBoardController.setChooseCostPane(childCost);

            //aggiunta carte payToObtain
            FXMLLoader loaderPayToObtain = new FXMLLoader(getClass().getResource("/FXML/PayToObtainCards.fxml"));
            AnchorPane childPay = loaderPayToObtain.load();
            gameboardRoot.getChildren().add(childPay);
            AnchorPane.setBottomAnchor(childPay,200.0);
            AnchorPane.setLeftAnchor(childPay,200.0);
            childPay.setVisible(false);
            PayToObtainController payToObtainController = loaderPayToObtain.getController();
            payToObtainController.setSender(chooseDistribution);
            payToObtainController.setGameBoardController(gameBoardController);
            gameBoardController.setPayToObtainController(payToObtainController);
            gameBoardController.setPayToObtainPane(childPay);

            //aggiunta ChooseEffect effetti
            FXMLLoader loaderEffects = new FXMLLoader(getClass().getResource("/FXML/ChooseEffect.fxml"));
            AnchorPane childEffect = loaderEffects.load();
            gameboardRoot.getChildren().add(childEffect);
            AnchorPane.setBottomAnchor(childEffect,200.0);
            AnchorPane.setLeftAnchor(childEffect,200.0);
            childEffect.setVisible(false);
            ChooseEffectController chooseEffectController = loaderEffects.getController();
            chooseEffectController.setSender(chooseDistribution);
            chooseEffectController.setGameBoardController(gameBoardController);
            chooseEffectController.setPayToObtainController(payToObtainController);
            gameBoardController.setChooseEffectController(chooseEffectController);
            gameBoardController.setChooseEffectPane(childEffect);

            payToObtainController.setChooseEffectController(chooseEffectController);


            //Aggiunta ChooseWorkers
            FXMLLoader loaderWorkers = new FXMLLoader(getClass().getResource("/FXML/ChooseWorkers.fxml"));
            AnchorPane childWorkers = loaderWorkers.load();
            gameboardRoot.getChildren().add(childWorkers);
            AnchorPane.setBottomAnchor(childWorkers,200.0);
            AnchorPane.setLeftAnchor(childWorkers,200.0);
            childWorkers.setVisible(false);
            WorkersController workersController = loaderWorkers.getController();
            workersController.setSender(chooseDistribution);
            gameBoardController.setWorkersController(workersController);
            gameBoardController.setChooseWorkersPane(childWorkers);

            //Aggiunta ChoosePrivilege
            FXMLLoader loaderPrivileges = new FXMLLoader(getClass().getResource("/FXML/ChoosePrivilege.fxml"));
            AnchorPane childPrivilege = loaderPrivileges.load();
            gameboardRoot.getChildren().add(childPrivilege);
            AnchorPane.setBottomAnchor(childPrivilege,200.0);
            AnchorPane.setLeftAnchor(childPrivilege,200.0);
            childPrivilege.setVisible(false);
            ChoosePrivilegeController choosePrivilegeController = loaderPrivileges.getController();
            choosePrivilegeController.setSender(chooseDistribution);
            gameBoardController.setChoosePrivilegeController(choosePrivilegeController);
            gameBoardController.setChoosePrivilegePane(childPrivilege);


            //Aggiunta Pray
            FXMLLoader loaderPray = new FXMLLoader(getClass().getResource("/FXML/Pray.fxml"));
            AnchorPane childPray = loaderPray.load();
            gameboardRoot.getChildren().add(childPray);
            AnchorPane.setBottomAnchor(childPray,200.0);
            AnchorPane.setLeftAnchor(childPray,200.0);
            childPray.setVisible(false);
            PrayController prayController = loaderPray.getController();
            prayController.setSender(chooseDistribution);
            gameBoardController.setPrayController(prayController);
            gameBoardController.setPrayPane(childPray);

            //Aggiunta YourTurn
            FXMLLoader loaderTurn = new FXMLLoader(getClass().getResource("/FXML/Pray.fxml"));
            AnchorPane childTurn = loaderTurn.load();
            gameboardRoot.getChildren().add(childTurn);
            AnchorPane.setBottomAnchor(childTurn,200.0);
            AnchorPane.setLeftAnchor(childTurn,200.0);
            childTurn.setVisible(false);
            gameBoardController.setYourTurnPane(childTurn);

            //Adding ThrowDices
            FXMLLoader loaderThrowDices = new FXMLLoader(getClass().getResource("/FXML/ThrowDices.fxml"));
            AnchorPane childDices = loaderThrowDices.load();
            gameboardRoot.getChildren().add(childDices);
            AnchorPane.setBottomAnchor(childDices,200.0);
            AnchorPane.setLeftAnchor(childDices,200.0);
            childDices.setVisible(false);
            gameBoardController.setThrowDicesPane(childDices);


        } catch (IOException e) {
            e.printStackTrace();
        }

        gameboardStage.setScene(new Scene(gameboardRoot));
        gameboardStage.setHeight(800);
        gameboardStage.setWidth(1100);
        gameboardStage.centerOnScreen();
        gameboardStage.setTitle("Lorenzo il Magnifico");
        gameBoardController.noButtonsAble();
        gameboardStage.show();



    }















    private void updatePawn(FamilyPawn familyPawn, int actionIndex) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.updatePawn(familyPawn, actionIndex);


            }
        });

    }

    private void updateTrack(PlayerColor playerColor, GoodType goodType, int numberOfPoints) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.updateTrack(playerColor, goodType, numberOfPoints);


            }
        });

    }

    private void updateTower(ArrayList<String> cards, CardColor cardColor) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.updateTower(cards, cardColor);


            }
        });

    }


    private void setGuiOnState(PlayerState newPlayerState) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.setState(newPlayerState);


            }
        });

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

    private void choosePayToObtainCards(Map<String, HashMap<Integer, String>> payToObtainCard) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.choosePayToObtainCards(payToObtainCard);


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

    private void updatePersonalCards(String cardName, CardColor cardColor) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.updateCardsPersonalBoard(cardName, cardColor);


            }
        });

    }



    private void updateGoodSet(GoodSet goodSet) {


        Platform.runLater(new Runnable() {


            //private GoodSet goodSetRun = goodSet;


            @Override
            public void run() {



                gameBoardController.updatePersonalGoodSet(goodSet);


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
