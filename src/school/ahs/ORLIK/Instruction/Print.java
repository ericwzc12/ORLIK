package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.Runtime;
import school.ahs.ORLIK.Runtime.Function;
import school.ahs.ORLIK.Runtime.Instruction;
import school.ahs.ORLIK.Runtime.Variable;
import school.ahs.ORLIK.StandardLibrary.Int32;

import java.util.Set;

public class Print implements Instruction {

    private final String string;

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
        String result = string;
        for (Variable v : variables) {
            if (result.contains(v.getIdentifier())) {
                int index = result.indexOf(v.getIdentifier());
                if (v.getThing() instanceof Int32) {
                    Int32 int32 = (Int32) v.getThing();
                    result = result.substring(0, index) + int32.getValue() + result.substring(v.getIdentifier().length() + index);
                }
            }
        }

        System.out.println(result);
    }
}
