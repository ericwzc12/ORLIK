package school.ahs.ORLIK.Runtime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Block implements Instruction {

    private final List<Instruction> instructions;

    public Block() {
        this.instructions = new ArrayList<>();
    }

    public Block(List<Instruction> instructions) {
        this.instructions = new ArrayList<>(instructions);
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public void execute(Set<Variable> variables) {
        Set<Variable> created = new HashSet<>(variables);
        for (Instruction instruction : instructions) {
            instruction.execute(created);
        }
    }

}
