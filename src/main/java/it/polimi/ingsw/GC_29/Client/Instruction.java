package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Controllers.PlayerState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 13/06/17.
 */

/**
 * This class represent the concept of the instruction for the game.
 * the field "instruction" is the description of the instruction (also it is the string checked if the instruction
 * does not have a regex).
 * The instrcution could contain a regex.
 *
 * the field stateList is a list of all the playerState where the instruction is valid.
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
