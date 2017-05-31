package Instruction;

import school.ahs.ORLIK.Runtime.*;
import school.ahs.ORLIK.Runtime.Runtime;

import java.util.Set;

public class Assignment implements Instruction {

    public static void main(String[] args) {
        new Assignment("", new Runtime(System.in)).validateIndexes(0, 1, 2, 3, 4);

    }

    public final Constructor constructor;
    public final String identifier;

    public Assignment(String statement, Runtime runtime) throws IllegalArgumentException {
        validateAssignmentStatement(statement);

        int space1 = statement.indexOf(' ');
        int paren1 = statement.indexOf('(');
        int paren2 = statement.indexOf(')');
        int space2 = statement.indexOf(' ', space1 + 1);
        int space3 = statement.indexOf(' ', space2 + 1);

        validateIndexes(space1, paren1, paren2, space2, space3);

        String blueprintIdentifier = statement.substring(space1 + 1, paren1);
        String constructorIdentifier = statement.substring(paren1, paren2 + 1);
        String thingIdentifier = statement.substring(space3);

        Blueprint blueprint = runtime.getBlueprint(blueprintIdentifier).orElseThrow(() -> new IllegalArgumentException());
        this.constructor = blueprint.getConstructor(constructorIdentifier).orElseThrow(() -> new IllegalArgumentException());
        this.identifier = thingIdentifier;
    }

    private void validateAssignmentStatement(String statement) throws IllegalArgumentException {
        if (!statement.startsWith("givemeanew ")) {
            throw new IllegalArgumentException("Assignment statement does not start with 'givemeanew '");
        }
        if (!statement.contains(" called ")) {
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

    }
}
