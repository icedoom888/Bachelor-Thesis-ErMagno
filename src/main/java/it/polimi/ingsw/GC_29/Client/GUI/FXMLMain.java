package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMI;
import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.ClientSocket.*;
import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.EnumInterface;
import it.polimi.ingsw.GC_29.Client.GUI.BonusTile.BonusTileController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseCost.ChooseCostController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect.ChooseEffectController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect.PayToObtainController;
import it.polimi.ingsw.GC_29.Client.GUI.ChoosePrivilege.ChoosePrivilegeController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseWorkers.WorkersController;
import it.polimi.ingsw.GC_29.Client.GUI.DisconectedPlayer.DisconnectedPlayerController;
import it.polimi.ingsw.GC_29.Client.GUI.EndGame.EndGameController;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginChange;
import it.polimi.ingsw.GC_29.Client.GUI.Login.LoginController;
import it.polimi.ingsw.GC_29.Client.GUI.Pray.PrayController;
import it.polimi.ingsw.GC_29.Client.GUI.ReconnectedPlayers.ReconnectedPlayersController;
import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import it.polimi.ingsw.GC_29.Client.GUI.Suspended.SuspendedController;
import it.polimi.ingsw.GC_29.Controllers.Change.*;
import it.polimi.ingsw.GC_29.Model.CardColor;
import it.polimi.ingsw.GC_29.Model.FamilyPawn;
import it.polimi.ingsw.GC_29.Model.GoodSet;
import it.polimi.ingsw.GC_29.Model.GoodType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Model.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;

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

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());


    private InputInterfaceGUI interfaceGUI;


    private ClientRMI clientRMI;

    @Override
    public void update(LoginChange o) {

        Boolean connected = o.getConnected();
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
            LOGGER.info((Supplier<String>) e);

        }

    }

    private void connectRMI() throws RemoteException, NotBoundException {

        clientRMI = new ClientRMI(EnumInterface.GUI);

        clientRMI.connectServerRMI();

        logged = clientRMI.loginGUI(username, password);

        if(!logged){

            loginController.setConnected(false);
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
                    try {
                        connectRMI();
                    } catch (RemoteException e) {
                        LOGGER.info((Supplier<String>) e);
                    } catch (NotBoundException e) {
                        LOGGER.info((Supplier<String>) e);
                    }
                    break;

            }


            if (!logged) {

                loginController.showError();

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

                    //this.chooseDistribution = new ChooseDistribution(Distribution.SOCKET);

                    clientSocketGUI.getClientInHandlerGUI().addListener(new GuiListener());

                    interfaceGUI = clientSocketGUI.getClientInHandlerGUI().getCommonOutSocket();

                    break;

                case RMI:

                    //this.chooseDistribution = new ChooseDistribution(Distribution.RMI);

                    clientRMI.getGameRMI().getClientRMIViewGUI().addListener(new GuiListener());

                    interfaceGUI = clientRMI.getGameRMI();

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
            LOGGER.info((Supplier<String>) e);
        }

        loginStage.setScene(new Scene(loginRoot));
        loginStage.setTitle("Login");
        loginStage.setHeight(400);
        loginStage.setWidth(600);
        loginStage.centerOnScreen();
        loginStage.show();

        loginStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                // consume event
                event.consume();
                //Platform.exit();

            }
        });



        loginController = loader.getController();


        logged = false;

        loginController.setConnected(false);
        loginController.registerObserver(this);

    }

    /**
     * Lancia la schermata principale del gioco,
     * settando tutti i controller interni e le varie schermate ausiliarie
     */

    private void setGameboard() {

        System.out.println("Gameboard Started");


        //chooseDistribution.setCommonOutSocket(clientSocketGUI.getClientOutHandlerGUI().getCommonOutSocket());

        gameboardStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));
        AnchorPane gameboardRoot = null;
        gameboardStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                // consume event
                event.consume();
                //TODO: gestire qui la disconnessione e la terminazione del processo client
                //Platform.exit();

            }
        });


        try {

            gameboardRoot = loader.load();
            gameBoardController = loader.getController();
            gameBoardController.setChooseDistribution(interfaceGUI);

            //aggiunta bonusTile
            FXMLLoader loaderBonus = new FXMLLoader(getClass().getResource("/FXML/BonusTile.fxml"));
            AnchorPane childBonus = loaderBonus.load();
            gameboardRoot.getChildren().add(childBonus);
            AnchorPane.setBottomAnchor(childBonus,200.0);
            AnchorPane.setLeftAnchor(childBonus,200.0);
            childBonus.setVisible(false);
            BonusTileController bonusTileController = loaderBonus.getController();
            bonusTileController.setSender(interfaceGUI);
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
            chooseCostController.setSender(interfaceGUI);
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
            payToObtainController.setSender(interfaceGUI);
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
            chooseEffectController.setSender(interfaceGUI);
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
            workersController.setSender(interfaceGUI);
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
            choosePrivilegeController.setSender(interfaceGUI);
            choosePrivilegeController.setGameBoardController(gameBoardController);
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
            prayController.setSender(interfaceGUI);
            gameBoardController.setPrayController(prayController);
            gameBoardController.setPrayPane(childPray);

            //Aggiunta YourTurn
            FXMLLoader loaderTurn = new FXMLLoader(getClass().getResource("/FXML/YourTurn.fxml"));
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

            //Adding BonusAction
            FXMLLoader loaderBonusAction = new FXMLLoader(getClass().getResource("/FXML/BonusAction.fxml"));
            AnchorPane childBonusAction = loaderBonusAction.load();
            gameboardRoot.getChildren().add(childBonusAction);
            AnchorPane.setBottomAnchor(childBonusAction,200.0);
            AnchorPane.setLeftAnchor(childBonusAction,200.0);
            childBonusAction.setVisible(false);
            gameBoardController.setBonusActionPane(childBonusAction);

            //Adding Suspended
            FXMLLoader loaderSuspended = new FXMLLoader(getClass().getResource("/FXML/Suspended.fxml"));
            AnchorPane childSuspended = loaderSuspended.load();
            gameboardRoot.getChildren().add(childSuspended);
            AnchorPane.setBottomAnchor(childSuspended,200.0);
            AnchorPane.setLeftAnchor(childSuspended,200.0);
            SuspendedController suspendedController = loaderSuspended.getController();
            suspendedController.setSender(interfaceGUI);
            childSuspended.setVisible(false);
            gameBoardController.setSuspendedPane(childSuspended);
            gameBoardController.setSuspendedController(suspendedController);

            //Adding YouHaveBeenExcommunicated
            FXMLLoader loaderExcommunicated = new FXMLLoader(getClass().getResource("/FXML/YouHaveBeenExcommunicated.fxml"));
            AnchorPane childExcommunicated = loaderExcommunicated.load();
            gameboardRoot.getChildren().add(childExcommunicated);
            AnchorPane.setBottomAnchor(childExcommunicated,200.0);
            AnchorPane.setLeftAnchor(childExcommunicated,200.0);
            childExcommunicated.setVisible(false);
            gameBoardController.setYouHaveBeenExcommunicatedPane(childExcommunicated);

            //Adding ReconnectedPlayers
            FXMLLoader loaderReconnected = new FXMLLoader(getClass().getResource("/FXML/ReconnectedPlayers.fxml"));
            AnchorPane childReconnected = loaderReconnected.load();
            gameboardRoot.getChildren().add(childReconnected);
            AnchorPane.setBottomAnchor(childReconnected,200.0);
            AnchorPane.setLeftAnchor(childReconnected,200.0);
            ReconnectedPlayersController reconnectedPlayersController = loaderReconnected.getController();
            childReconnected.setVisible(false);
            gameBoardController.setReconnectedPlayersPane(childReconnected);
            gameBoardController.setReconnectedPlayersController(reconnectedPlayersController);

            //Adding DisconnectedPlayer
            FXMLLoader loaderDisconnected = new FXMLLoader(getClass().getResource("/FXML/DisconnectedPlayer.fxml"));
            AnchorPane childDisconnected = loaderDisconnected.load();
            gameboardRoot.getChildren().add( childDisconnected);
            AnchorPane.setBottomAnchor(childDisconnected,200.0);
            AnchorPane.setLeftAnchor(childDisconnected,200.0);
            DisconnectedPlayerController disconnectedPlayerController = loaderDisconnected.getController();
            childDisconnected.setVisible(false);
            gameBoardController.setDisconnectedPlayerPane(childDisconnected);
            gameBoardController.setDisconnectedPlayerController(disconnectedPlayerController);

            //Adding EndGame
            FXMLLoader loaderEndGame = new FXMLLoader(getClass().getResource("/FXML/EndGame.fxml"));
            AnchorPane childEndGame = loaderEndGame.load();
            gameboardRoot.getChildren().add(childEndGame);
            AnchorPane.setBottomAnchor(childEndGame,200.0);
            AnchorPane.setLeftAnchor(childEndGame,200.0);
            EndGameController endGameController = loaderEndGame.getController();
            childEndGame.setVisible(false);
            gameBoardController.setEndGamePane(childEndGame);
            gameBoardController.setEndGameController(endGameController);




        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        }

        gameboardStage.setScene(new Scene(gameboardRoot));
        gameboardStage.setHeight(800);
        gameboardStage.setWidth(1100);
        gameboardStage.centerOnScreen();
        gameboardStage.setTitle("Lorenzo il Magnifico");
        gameBoardController.noButtonsAble();
        gameboardStage.show();


    }




    private void removeAllPawns() {

        gameBoardController.removeAllPawns();

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

    private void playerPraying(String excommunicationUrl) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                gameBoardController.pray(excommunicationUrl);


            }
        });

    }


    private void leaderOnGui(ArrayList<String> leaderUrls) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                gameBoardController.updateLeaderCards(leaderUrls);


            }
        });

    }


    private void setExcommunicationTiles(ArrayList<String> excommunicationTiles) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                gameBoardController.updateExcomunicationTiles(excommunicationTiles);


            }
        });

    }

    private void availableLeaders(Map<Integer, Boolean> leadersAvailable) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                gameBoardController.setPossibleLeaders(leadersAvailable);


            }
        });

    }

    private void updateBonusTileDisc(Integer bonusTile) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                gameBoardController.setBonusTile(bonusTile);


            }
        });
    }

    private void trackAfterDisconnection(PlayerColor playerColor, GoodType goodType, Integer numberOfPoints) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                gameBoardController.resetTrack(playerColor, goodType, numberOfPoints);


            }
        });
    }

    private void endGameGui(String winner) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                gameBoardController.endGame(winner);


            }
        });

    }

    private void reconnectedPlayers(List<String> reconnectedPlayerUsernames) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                gameBoardController.reconnectPlayers(reconnectedPlayerUsernames);


            }
        });
    }

    private void disconnectedPlayers(List<String> username) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                gameBoardController.disconnectedPlayer(username);


            }
        });
    }

    private void initializeTracks(Map<PlayerColor, String> playerNames) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                gameBoardController.initializeNames(playerNames);


            }
        });
    }




    class GuiListener implements GuiChangeListener{

        @Override
        public void onReadingChange(GoodSetChange goodSetChange) {
            updateGoodSet(goodSetChange.getGoodSet());
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

            removeAllPawns();

        }

        @Override
        public void onReadingChange(ExcommunicationChange excommunicationChange) {
            setExcommunicationTiles(excommunicationChange.getExcommunicationTiles());
        }


        @Override
        public void pray(String excommunicationUrl) {

            playerPraying(excommunicationUrl);

        }

        @Override
        public void changeState(PlayerState currentPlayerState) {

            setGuiOnState(currentPlayerState);

        }

        @Override
        public void validActions(Map<Integer, String> validActionList) {

            updateValidActions(validActionList);

        }

        @Override
        public void updatePawns(Map<FamilyPawn, Boolean> familyPawns) {

            updateFamilyPawns(familyPawns);

        }

        @Override
        public void cardsForWorkers(Map<Integer, ArrayList<String>> cardsForWorkers) {

            chooseWorkers(cardsForWorkers);

        }

        @Override
        public void payToObtainCard(Map<String, HashMap<Integer, String>> payToObtainCard) {

            choosePayToObtainCards(payToObtainCard);

        }

        @Override
        public void possibleCosts(Map<Integer, String> possibleCosts) {

            chooseCost(possibleCosts);

        }

        @Override
        public void councilPrivilege(List<Integer> councilPrivileges) {

            chooseCouncilPrivilege(councilPrivileges);

        }

        @Override
        public void bonusTile(Map<Integer, String> bonusTiles) {

            chooseBonusTile(bonusTiles);

        }

        @Override
        public void setLeaderCards(ArrayList<String> leaderUrls) {

            leaderOnGui(leaderUrls);
        }

        @Override
        public void sendLeaderCards(Map<Integer, Boolean> leadersAvailable) {

            availableLeaders(leadersAvailable);
        }

        @Override
        public void updateBonusTileFromDisconnection(Integer bonusTile) {
            updateBonusTileDisc(bonusTile);
        }

        @Override
        public void resetTrack(PlayerColor playerColor, GoodType goodType, Integer numberOfPoints) {
            trackAfterDisconnection(playerColor, goodType, numberOfPoints);
        }

        @Override
        public void endGame(String winner) {

            endGameGui(winner);
        }

        @Override
        public void showDisconnectedPlayer(List<String> username) {
            System.out.println("\n\n PLAYER DISCONNESSO: SONO IN SHOW DISCONNECTED ");
            disconnectedPlayers(username);
        }

        @Override
        public void showReconnectedPlayers(List<String> reconnectedPlayerUsernames) {
            reconnectedPlayers(reconnectedPlayerUsernames);

        }

        @Override
        public void sendPlayerNames(Map<PlayerColor, String> playerNames) {
            initializeTracks(playerNames);
        }


    }




}
