package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Workspace implements Cleanable {
    private ActionSpace mainField;
    private ActionSpace secondaryField;
    private WorkspaceType type;

    @Override
    public void clean() {

    }

    public ActionSpace getMainField() {
        return mainField;
    }

    public ActionSpace getSecondaryField() {
        return secondaryField;
    }

    public WorkspaceType getType() {
        return type;
    }
}
