package it.polimi.ingsw.GC_29.Client.GUI.Login;

import it.polimi.ingsw.GC_29.Client.ClientSocket.ClientSocket;
import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.EnumInterface;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


/**
 * Created by AlbertoPennino on 21/06/2017.
 */
public class LoginController {

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private RadioButton rmi;
    @FXML private RadioButton socket;
    @FXML private Button submit;
    @FXML private Text errorBlankFields;
    @FXML private Text errorUserPsw;
    private static Distribution connection;
    private final int PORT = 29999;
    private final String IP = "127.0.0.1";
    private boolean firstTime = true;
    private ClientSocket clientSocket;


    public void sendSubmit(ActionEvent event){

        if(!(username.getText().isEmpty()
                && password.getText().isEmpty())
                && ((rmi.isSelected()
                    || socket.isSelected()))) {

            if (rmi.isSelected()) {
                setConnection(Distribution.RMI);
                executeRMI(event);
            }

            else if (socket.isSelected()) {
                setConnection(Distribution.SOCKET);
                executeSocket(event);
            }



            /*ClientOutHandlerGUI sender = new ClientOutHandlerGUI();
            sender.sendInput("");

            Node source = (Node) event.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();*/
        }
        else {
            errorBlankFields.setVisible(true);
        }
    }

    private void executeSocket(ActionEvent event) {
        try {

            if (this.clientSocket == null) {
                this.clientSocket = new ClientSocket(EnumInterface.GUI);
            }

            clientSocket.startClientGUI();

            if (clientSocket.loginGUI(username.getText(), password.getText())) {
                Node source = (Node) event.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
                //Application.launch(WaitingNewGame.class);

                clientSocket.playNewGameGUI();
            }

            else {

                errorUserPsw.setVisible(true);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeRMI(ActionEvent event) {

    }

    public void switchButtons(ActionEvent event){
        if (event.getSource()==rmi){
            socket.setSelected(false);
        }
        else if (event.getSource()==socket){
            rmi.setSelected(false);
        }
    }


    public void setConnection(Distribution connection) {
        this.connection = connection;
    }
}
