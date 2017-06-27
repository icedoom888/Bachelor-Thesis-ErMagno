package it.polimi.ingsw.GC_29.Client.GUI.ChoosePrivilege;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlbertoPennino on 26/06/2017.
 */
public class ChoosePrivilegeController {

    private ChooseDistribution sender;

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
        if (privilege1.isSelected()){
            sender.sendInput("privilege 0");
        }
        else if (privilege2.isSelected()){
            sender.sendInput("privilege 1");
        }
        else if (privilege3.isSelected()){
            sender.sendInput("privilege 2");
        }
        else if (privilege4.isSelected()){
            sender.sendInput("privilege 3");
        }
        else if (privilege5.isSelected()){
            sender.sendInput("privilege 4");
        }
        else {
            error.setVisible(true);
        }

    }

    public void updatePrivilege(List<Integer> privileges){
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
    }


    public void setSender(ChooseDistribution sender) {
        this.sender = sender;
    }
}
