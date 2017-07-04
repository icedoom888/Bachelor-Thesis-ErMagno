package it.polimi.ingsw.GC_29.Client.GUI.Pray;


import it.polimi.ingsw.GC_29.Client.InputInterfaceGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Created by AlbertoPennino on 25/06/2017.
 */
public class PrayController {

    private InputInterfaceGUI sender;

    @FXML
    private ImageView excommunicationDetails;

    @FXML
    private RadioButton pray;

    @FXML
    private RadioButton getExcommunicated;

    @FXML
    private Text error;

    @FXML
    private Button submit;


    /**
     * Questa funzione è lanciata quando il giocatore invia la scelta sull'essere scomunicato oppure no premendo sul pulsante submit,
     * la funzione esegue un controllo verificando che il giocatore abbia effettivamente eseguito una scelta,
     * se la scelta non è stata fatta viene mostrato un errore,
     * se la scelta è stata fatta essa viene inviata tramite l'InputInterfaceGUI
     * @param event
     */
    public void sendSubmit(ActionEvent event){
        if(pray.isSelected()){
            sender.sendInput("pray");
        }
        else if (getExcommunicated.isSelected()){
            sender.sendInput("do not pray");
        }
        else {
            error.setVisible(true);
        }
    }

    /**
     * Questa funzione aggiorna l'immagine della scheda scomunica da mostrare al giocatore
     * @param newExcommunication
     */
    public void updatePray(String newExcommunication){
        excommunicationDetails.setImage(new Image(newExcommunication));
    }


    /**
     * Questa funzione non permette ad entrambi i pulsanti pray e don't pray di essere selezionati contemporaneamente,
     * se uno è selezionato e il giocatore clicca l'altro, automaticamente il primo sarà deselezionato
     * @param event
     */
    public void switchButtons(ActionEvent event){
        if (event.getSource()==pray){
            getExcommunicated.setSelected(false);
        }
        else if (event.getSource()==getExcommunicated){
            pray.setSelected(false);
        }
    }

    public void setSender(InputInterfaceGUI sender) {
        this.sender = sender;
    }
}
