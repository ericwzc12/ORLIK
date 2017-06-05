package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.Runtime;
import school.ahs.ORLIK.Runtime.Function;
import school.ahs.ORLIK.Runtime.Instruction;
import school.ahs.ORLIK.Runtime.Variable;

import java.util.Set;

public class Print implements Instruction {

    final String string;

    public Print(String line) throws IllegalArgumentException {
        validatePrintStatement(line);
        string = line.substring(6, line.length() - 1);
    }

    private void validatePrintStatement(String line) throws IllegalArgumentException {
        if (!line.startsWith("print(")) {
            throw new IllegalArgumentException("Print should start with print(");
        }
        if (!line.endsWith(")")) {
            throw new IllegalArgumentException("Line should end with )");
        }
    }

    @Override
    public void execute(Set<Variable> variables) {
        System.out.println(string);
    }
}
