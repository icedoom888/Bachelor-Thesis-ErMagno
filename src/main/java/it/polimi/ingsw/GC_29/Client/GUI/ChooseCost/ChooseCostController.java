package it.polimi.ingsw.GC_29.Client.GUI.ChooseCost;


import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by AlbertoPennino on 25/06/2017.
 */
public class ChooseCostController {

    private InputInterfaceGUI sender;

    @FXML
    private TextArea shownCosts;

    @FXML
    private TextField chosenCost;

    @FXML
    private Button submit;

    @FXML
    private Text error;

    /**
     * Questa funzione è lanciata quando il giocatore invia la scelta del costo cliccando sul pulsante submit,
     * la funzione esegue un controllo sul contenuto dell'area di testo in cui il giocatore ha indicato la sua scelta,
     * se il contenuto è vuoto viene mostrata una scritta di errore ed il giocatore deve riempire il campo e premere submit ancora,
     * se il contenuto è presente esso viene inviato tramite l'InputInterfaceGUI
     * @param event
     */
    public void sendSubmit(ActionEvent event){
        if(!chosenCost.getText().isEmpty()){
            sender.sendInput("cost " + chosenCost.getText());
        }
        else {
            error.setVisible(true);
        }
    }

    /**
     * Questa funzione è chiamata per mostrare all'interno della Text Area principale,
     * l'elenco dei costi tra cui il player può scegliere.
     * @param newCosts
     */
    public void updateShownCosts(String newCosts){
        shownCosts.setText(newCosts);
    }

    public void setSender(InputInterfaceGUI sender) {
        this.sender = sender;
    }
}
