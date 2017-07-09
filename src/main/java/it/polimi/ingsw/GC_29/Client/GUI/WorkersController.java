package it.polimi.ingsw.GC_29.Client.GUI;


import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by AlbertoPennino on 26/06/2017.
 */
public class WorkersController {

    private InputInterfaceGUI sender;
    private ArrayList<Integer> choices;

    @FXML
    private TextArea shownWorkers;

    @FXML
    private TextField chosenWorkers;

    @FXML
    private Button submit;

    @FXML
    private Text error;

    /**
     * Questa funzione è lanciata quando il giocatore invia la scelta dei workers indicati cliccando sul pulsante submit,
     * la funzione esegue un controllo sul contenuto dell'area di testo in cui il giocatore ha indicato la sua scelta,
     * se il contenuto è vuoto oppure non contiene un valore accettabile
     * viene mostrata una scritta di errore ed il giocatore deve riempire nuovamente il campo e premere submit ancora,
     * se il contenuto è presente esso viene inviato tramite l'InputInterfaceGUI
     * @param event
     */
    public void sendSubmit(ActionEvent event){
        String choiceMade = chosenWorkers.getText();
        if(!choiceMade.isEmpty()){
            for (Integer choicePossible : choices){
                if (choicePossible.toString().equals(choiceMade)){
                    sender.sendInput("use workers " + choiceMade);
                    System.out.println("use workers " + choiceMade);
                }
                else {
                    error.setVisible(true);
                }
            }

        }
        else {
            error.setVisible(true);
        }
    }

    /**
     * Questa funzione è chiamata per mostrare all'interno della Text Area principale,
     * l'elenco dei workers tra cui il player può scegliere.
     * @param newWorkers
     */
    public void updateShownCosts(String newWorkers){
        shownWorkers.setText(newWorkers);
        error.setVisible(false);
        chosenWorkers.setText("");
    }

    public void setSender(InputInterfaceGUI sender) {
        this.sender = sender;
    }

    public void setChoices(ArrayList<Integer> choices) {
        this.choices = choices;
    }
}
