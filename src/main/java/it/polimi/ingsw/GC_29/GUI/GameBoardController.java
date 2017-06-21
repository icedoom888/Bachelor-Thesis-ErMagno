package it.polimi.ingsw.GC_29.GUI;

import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */
public class GameBoardController {
    //Pulsanti per scegliere le pedine
    @FXML private ImageView whitePawn;
    @FXML private ImageView orangePawn;
    @FXML private ImageView balckPawn;
    @FXML private ImageView neutralPawn;

    // Valore pedine
    @FXML private Text whiteValue;
    @FXML private Text orangeValue;
    @FXML private Text blackValue;
    @FXML private Text neutralValue;

    //Pulsanti per attivare i leader
    @FXML private Button activate1;
    @FXML private Button activate2;
    @FXML private Button activate3;
    @FXML private Button activate4;

    //Pulsanti per scartare una carta leader
    @FXML private Button burn1;
    @FXML private Button burn2;
    @FXML private Button burn3;
    @FXML private Button burn4;

    @FXML private Button throwDices;
    @FXML private Button skipAction;
    @FXML private Button endTurn;

    private HashMap<Integer, javafx.scene.control.Button> leaderActivateButtons = new HashMap<>();

    public void handleActivate(ActionEvent event){
        Object activateButton = event.getSource();
        ClientSocketView sender = new ClientSocketView();

        if(activateButton==activate1){
            sender.sendInput("activate leader card 0");
        }
        if(activateButton==activate2){
            sender.sendInput("activate leader card 1");
        }
        if(activateButton==activate3){
            sender.sendInput("activate leader card 2");
        }
        if(activateButton==activate4){
            sender.sendInput("activate leader card 3");
        }

    }

    public void handleBurn(ActionEvent event){
        ClientSocketView sender = new ClientSocketView();
        Object burnButton = event.getSource();
        if (burnButton==burn1){
            sender.sendInput("discard leader card 0");
        }
        if (burnButton==burn2){
            sender.sendInput("discard leader card 1");
        }
        if (burnButton==burn3){
            sender.sendInput("discard leader card 2");
        }
        if (burnButton==burn4){
            sender.sendInput("discard leader card 3");
        }
    }

    public void handleThrowDices(ActionEvent event){
        ClientSocketView sender = new ClientSocketView();
        sender.sendInput("throw dices");
    }

    public void handleSkipAction(ActionEvent event){
        ClientSocketView sender = new ClientSocketView();
        sender.sendInput("skip action");
    }

    public void handleEndTurn(ActionEvent event){
        ClientSocketView sender = new ClientSocketView();
        sender.sendInput("end turn");
    }

    public void handlePawnPicked(MouseEvent event) {
        ClientSocketView sender = new ClientSocketView();
        if (event.getSource() == balckPawn) {
            sender.sendInput("use family pawn black");
        }
        if (event.getSource() == whitePawn) {
            sender.sendInput("use family pawn white");
        }
        if (event.getSource() == orangePawn) {
            sender.sendInput("use family pawn orange");
        }
        if (event.getSource() == neutralPawn) {
            sender.sendInput("use family pawn neutral");
        }

    }

/*
    public void activateButtons(PlayerState currentPlayerState){
        switch (currentPlayerState){
            case ENDTURN:
                deactivateLeaderButtons();
                skipAction.setEnabled(false);
                throwDices.setEnabled(false);
                endTurn.setEnabled(true);

            case THROWDICES:
                deactivateLeaderButtons();
                skipAction.setEnabled(false);
                endTurn.setEnabled(false);
                throwDices.setEnabled(true);

            case CHOOSEACTION:
                activateLeaderButtons();
                skipAction.setEnabled(false);
                endTurn.setEnabled(false);
                throwDices.setEnabled(false);

            default:
                deactivateLeaderButtons();
                skipAction.setEnabled(false);
                throwDices.setEnabled(false);
                endTurn.setEnabled(false);

        }
    }

    public void activateLeaderButtons(Map<LeaderCard,Boolean> availability){
        int i=0;
        while (i<4){
            for (LeaderCard leaderCard:availability.keySet()) {

            }
        }
    }*/
    public void activatePawns(Map<FamilyPawn,Boolean> availability){
        for (FamilyPawn pawn: availability.keySet()) {
            if (pawn.getType()==FamilyPawnType.WHITE){
                if (availability.get(pawn)) {
                    whitePawn.setDisable(false);
                    Integer value = pawn.getActualValue();
                    whiteValue.setText(value.toString());
                }
                else{
                    whitePawn.setDisable(true);
                    whiteValue.setText("");
                }
            }
            if (pawn.getType()==FamilyPawnType.ORANGE){
                if (availability.get(pawn)) {
                    orangePawn.setDisable(false);
                    Integer value = pawn.getActualValue();
                    orangeValue.setText(value.toString());
                }
                else {
                    orangePawn.setDisable(true);
                    orangeValue.setText("");
                }
            }
            if (pawn.getType()==FamilyPawnType.BLACK){
                if (availability.get(pawn)) {
                    blackValue.setDisable(false);
                    Integer value = pawn.getActualValue();
                    blackValue.setText(value.toString());
                } else {
                    balckPawn.setDisable(true);
                    blackValue.setText("");
                }
            }
            if (pawn.getType()==FamilyPawnType.NEUTRAL){
                if (availability.get(pawn)) {
                    neutralPawn.setDisable(false);
                    Integer value = pawn.getActualValue();
                    neutralValue.setText(value.toString());
                } else {
                    neutralPawn.setDisable(true);
                    neutralValue.setText("");
                }
            }
        }
    }
}
