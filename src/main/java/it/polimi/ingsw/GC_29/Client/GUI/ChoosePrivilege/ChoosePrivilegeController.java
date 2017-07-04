package it.polimi.ingsw.GC_29.Client.GUI.ChoosePrivilege;


import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilege;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlbertoPennino on 26/06/2017.
 */
public class ChoosePrivilegeController {

    private InputInterfaceGUI sender;

    private GameBoardController gameBoardController;

    private List<CouncilPrivilegeEffect> councilPrivilegeEffectList = new ArrayList<>();
    private List<Integer> councilPrivilegeEffectChosenList = new ArrayList<>();
    private Map<Integer, ObtainEffect> currentParchment;
    private CouncilPrivilegeEffect currentCouncilPrivilegeEffect;
    private List<Map<Integer, ObtainEffect>> currentParchmentList = new ArrayList<>();


    private HashMap<Integer,RadioButton> buttons;

    @FXML
    private RadioButton privilege1;
    @FXML
    private RadioButton privilege2;
    @FXML
    private RadioButton privilege3;
    @FXML
    private RadioButton privilege4;
    @FXML
    private RadioButton privilege5;

    @FXML
    private Text error;

    @FXML
    private Button submit;

    //private HashMap<Integer, RadioButton> radioButtons = new HashMap<>();
    private HashMap<Integer, RadioButton> radioButtons = new HashMap<>();


    public void switchButtons(ActionEvent event){
        if (event.getSource()==privilege1){
            privilege2.setSelected(false);
            privilege3.setSelected(false);
            privilege4.setSelected(false);
            privilege5.setSelected(false);
        }
        else if (event.getSource()==privilege2){
            privilege1.setSelected(false);
            privilege3.setSelected(false);
            privilege4.setSelected(false);
            privilege5.setSelected(false);
        }
        else if (event.getSource()==privilege3){
            privilege1.setSelected(false);
            privilege2.setSelected(false);
            privilege4.setSelected(false);
            privilege5.setSelected(false);
        }
        else if (event.getSource()==privilege4){
            privilege1.setSelected(false);
            privilege3.setSelected(false);
            privilege2.setSelected(false);
            privilege5.setSelected(false);
        }
        else if (event.getSource()==privilege5){
            privilege1.setSelected(false);
            privilege3.setSelected(false);
            privilege4.setSelected(false);
            privilege2.setSelected(false);
        }
    }

    public void sendSubmit(ActionEvent event){

        privilege1.setDisable(true);
        privilege2.setDisable(true);
        privilege3.setDisable(true);
        privilege4.setDisable(true);
        privilege5.setDisable(true);


        gameBoardController.getChoosePrivilegePane().setVisible(false);

        int index;

        if (privilege1.isSelected()) {
            index = 0;
        }

        else if (privilege2.isSelected()) {
            index = 1;
        }

        else if (privilege3.isSelected()) {
            index = 2;
        }

        else if (privilege4.isSelected()) {
            index = 3;
        }

        else if (privilege5.isSelected()) {
            index = 4;
        }


        else {

            error.setVisible(true);

            return;

        }


        councilPrivilegeEffectChosenList.add(index);

        for (Map<Integer, ObtainEffect> councilEffectsMap : currentParchmentList) {

            councilEffectsMap.remove(index);

        }



        if (nextParchment()) {
            askWhichPrivilege();
        }

        else if (nextPrivilegeEffect()) {
            askWhichPrivilege();
        }

        else {

            if (gameBoardController.getPlayerState() == PlayerState.CHOOSE_COUNCIL_PRIVILEGE) {
                sender.sendInput(councilPrivilegeEffectChosenList, true);

            }

            /*else if (gameBoardController.getPlayerState() == PlayerState.DISCARDINGLEADER) {
                sender.sendInput(councilPrivilegeEffectChosenList, false);
            }*/
        }



    }



    public void choosePrivilege(List<Integer> privileges) {


        radioButtons.put(0, privilege1);
        radioButtons.put(1, privilege2);
        radioButtons.put(2, privilege3);
        radioButtons.put(3, privilege4);
        radioButtons.put(4, privilege5);


        privilege1.setDisable(false);
        privilege1.setSelected(false);
        privilege2.setDisable(false);
        privilege2.setSelected(false);
        privilege3.setDisable(false);
        privilege3.setSelected(false);
        privilege4.setDisable(false);
        privilege4.setSelected(false);
        privilege5.setDisable(false);
        privilege5.setSelected(false);

        /*
        for(int i = 0; i<5; i++){
            if (!privileges.contains(i)){
                switch (i){
                    case (0):
                        privilege1.setDisable(true);
                        break;
                    case (1):
                        privilege2.setDisable(true);
                        break;
                    case (2):
                        privilege3.setDisable(true);
                        break;
                    case (3):
                        privilege4.setDisable(true);
                        break;
                    case (4):
                        privilege5.setDisable(true);
                        break;
                }
            }
        }
        */

        setCouncilPrivilegeEffectList(privileges);

        nextPrivilegeEffect();

        askWhichPrivilege();


    }




    public void setCouncilPrivilegeEffectList(List<Integer> councilPrivilegeEffectList) {

        for (Integer integer : councilPrivilegeEffectList) {

            this.councilPrivilegeEffectList.add(new CouncilPrivilegeEffect(integer));
        }

        councilPrivilegeEffectChosenList.clear();
    }

    private boolean nextPrivilegeEffect() {

        if (!councilPrivilegeEffectList.isEmpty()) {

            currentCouncilPrivilegeEffect = councilPrivilegeEffectList.remove(0);

            //currentParchmentList = currentCouncilPrivilegeEffect.getParchmentList();

            for (CouncilPrivilege councilPrivilege : currentCouncilPrivilegeEffect.getParchmentList()) {

                Map<Integer, ObtainEffect> temporaryMap = new HashMap<>();

                for (int index = 0; index < councilPrivilege.getPossibleObtainEffect().size(); index++) {

                    ObtainEffect temporaryObtainEffect = councilPrivilege.getPossibleObtainEffect().get(index);

                    temporaryMap.put(index, temporaryObtainEffect);

                }

                currentParchmentList.add(temporaryMap);
            }

            return true;

        }
        else {

            return false;
        }
    }

    private boolean nextParchment() {

        return !currentParchmentList.isEmpty();
    }

    private void askWhichPrivilege() {

        currentParchment = currentParchmentList.remove(0);

        //TODO: impl con indici sempre uguali
        //TODO: qui va fatto il set degli effetti che possono essere attivi o meno

        for (Integer integer : currentParchment.keySet()) {
            radioButtons.get(integer).setDisable(false);
        }

        gameBoardController.getChoosePrivilegePane().setVisible(true);

    }


    public void setSender(InputInterfaceGUI sender) {
        this.sender = sender;
    }

    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }


}
