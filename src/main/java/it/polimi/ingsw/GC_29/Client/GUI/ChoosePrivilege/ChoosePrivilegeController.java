package it.polimi.ingsw.GC_29.Client.GUI.ChoosePrivilege;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import it.polimi.ingsw.GC_29.Client.GUI.GameBoard.GameBoardController;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilege;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect;
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

    private ChooseDistribution sender;

    private GameBoardController gameBoardController;

    private List<CouncilPrivilegeEffect> councilPrivilegeEffectList = new ArrayList<>();
    private List<Integer> councilPrivilegeEffectChosenList = new ArrayList<>();
    private CouncilPrivilege currentParchment;
    private CouncilPrivilegeEffect currentCouncilPrivilegeEffect;
    private List<CouncilPrivilege> currentParchmentList = new ArrayList<>();


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

        gameBoardController.getChoosePrivilegePane().setVisible(false);

        if (privilege1.isSelected()) {
            councilPrivilegeEffectChosenList.add(0);
        }

        else if (privilege2.isSelected()) {
            councilPrivilegeEffectChosenList.add(1);

        }

        else if (privilege3.isSelected()) {
            councilPrivilegeEffectChosenList.add(2);

        }

        else if (privilege4.isSelected()) {
            councilPrivilegeEffectChosenList.add(3);

        }

        else if (privilege5.isSelected()) {
            councilPrivilegeEffectChosenList.add(4);

        }

        else {

            error.setVisible(true);

            return;

        }

        if (nextParchment()) {
            askWhichPrivilege();
        }

        else if (nextPrivilegeEffect()) {
            askWhichPrivilege();
        }

        else {

            sender.sendInput(councilPrivilegeEffectChosenList);
        }



    }



    public void choosePrivilege(List<Integer> privileges) {

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
            currentParchmentList = currentCouncilPrivilegeEffect.getParchmentList();

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

        currentParchment = currentCouncilPrivilegeEffect.getParchmentList().remove(0);

        //TODO: impl con indici sempre uguali
        //TODO: qui va fatto il set degli effetti che possono essere attivi o meno

        gameBoardController.getChoosePrivilegePane().setVisible(true);

    }


    public void setSender(ChooseDistribution sender) {
        this.sender = sender;
    }

    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }


}
