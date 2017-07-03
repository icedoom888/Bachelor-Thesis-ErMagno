package it.polimi.ingsw.GC_29.Client;

import java.util.List;
import java.util.Map;

/**
 * Created by Christian on 02/07/2017.
 */
public interface InputInterfaceGUI {

    void sendInput(String input);

    void sendInput(Map<String, Integer> activatedCardMap);

    void sendInput(List<Integer> councilPrivilegeEffectChosenList, boolean b);

    void setCouncilPrivilegeEffectChosenList(List<Integer> councilPrivilegeEffectChosenList);

    void setActivatedCardMap(Map<String, Integer> activatedCardMap);
}
