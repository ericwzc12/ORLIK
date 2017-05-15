package school.ahs.ORLIK.Runtime;

import java.util.Set;

public interface Instruction {

    void execute(Set<Variable> variables);

}
