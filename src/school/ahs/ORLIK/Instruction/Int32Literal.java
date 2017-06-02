package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.*;
import school.ahs.ORLIK.Runtime.Runtime;
import school.ahs.ORLIK.StandardLibrary.Int32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Int32Literal implements Instruction {

    public final int value;
    public final String identifier;

    public Int32Literal(String statement, Runtime runtime) throws IllegalArgumentException {
        validateAssignmentStatement(statement);

        int space1 = statement.indexOf(' ');
        int paren1 = statement.indexOf('(');
        int paren2 = statement.indexOf(')');
        int space2 = statement.indexOf(' ', space1 + 1);
        int space3 = statement.indexOf(' ', space2 + 1);

        validateIndexes(space1, paren1, paren2, space2, space3);

        String blueprintIdentifier = statement.substring(space1 + 1, paren1);
        value = Integer.parseInt(statement.substring(paren1, paren2 + 1));
        String thingIdentifier = statement.substring(space3);

        this.identifier = thingIdentifier;
    }

    private void validateAssignmentStatement(String statement) throws IllegalArgumentException {
        if (!statement.startsWith("givemeanew Int32(")) {
            throw new IllegalArgumentException("Assignment statement does not start with 'givemeanew Int32('");
        }
        if (!statement.contains(") called ")) {
            throw new IllegalArgumentException("Assignment statement does not contain ' of '");
        }
    }

    private void validateIndexes(int... indexes) throws IllegalArgumentException {
        for (int i = 0; i < indexes.length - 1; i++) {
            if (indexes[i] < 0 || indexes[i] > indexes[i + 1]) {
                throw new IllegalArgumentException("Indexes out of order or not present.");
            }
        }
    }

    @Override
    public void execute(Set<Variable> variables) {
        Variable variable = new Variable(identifier, new Int32(value));
    }

}
