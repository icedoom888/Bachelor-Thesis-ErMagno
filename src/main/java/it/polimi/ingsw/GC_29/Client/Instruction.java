package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Controllers.PlayerState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 13/06/17.
 */
public class Instruction {

    private String instruction;
    private String regex;
    private boolean isRegex = false;
    private List<PlayerState> stateList;

    public Instruction(String instruction, List<PlayerState> stateList){

        this.instruction = instruction;
        this.stateList = new ArrayList<>(stateList);
    }

    public Instruction(String instruction, List<PlayerState> stateList, String regex){

        this(instruction, stateList);

        this.regex = regex;

        this.isRegex = true;
    }

    public List<PlayerState> getStateList() {
        return stateList;
    }

    public String getInstruction() {
        return instruction;
    }

    public boolean isRegex() {
        return isRegex;
    }

    public String getRegex() {
        return regex;
    }
}
