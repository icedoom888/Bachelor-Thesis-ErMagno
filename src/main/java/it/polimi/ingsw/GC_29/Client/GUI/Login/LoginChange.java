package it.polimi.ingsw.GC_29.Client.GUI.Login;

import it.polimi.ingsw.GC_29.Controllers.GUIChange;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public class LoginChange extends GUIChange {

    private static final long serialVersionUID = 5551223529797237865L;

    private Boolean connected;

    public LoginChange(Boolean connected){
        this.connected = connected;
    }

    public Boolean getConnected() {
        return connected;
    }

    /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
    @Override
    public String toString() {
        return "LoginChange [newChange=" + connected + "]";
    }
}
