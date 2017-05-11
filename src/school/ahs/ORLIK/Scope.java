package school.ahs.ORLIK;

import java.util.List;

public class Scope {

    private String name;
    private List<Instruction> instructions;

    public Scope(String name, List<Instruction> instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return name + " {...}";
    }

}
