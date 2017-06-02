package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.*;
import school.ahs.ORLIK.Runtime.Runtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Assignment implements Instruction {

    public final String identifier;
    public final String inParens;
    public final Blueprint blueprint;

    public Assignment(String statement, Runtime runtime) throws IllegalArgumentException {
        validateAssignmentStatement(statement);

        int space1 = statement.indexOf(' ');
        int paren1 = statement.indexOf('(');
        int paren2 = statement.indexOf(')');
        int space2 = statement.indexOf(' ', space1 + 1);
        int space3 = statement.indexOf(' ', space2 + 1);

        validateIndexes(space1, paren1, paren2, space2, space3);

        String blueprintIdentifier = statement.substring(space1 + 1, paren1);
        inParens = statement.substring(paren1, paren2 + 1);
        String thingIdentifier = statement.substring(space3);

        blueprint = runtime.getBlueprint(blueprintIdentifier).orElseThrow(() -> new IllegalArgumentException());
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
        List<String> identifiers = new ArrayList<>(Arrays.asList(inParens.split(",")));
        List<Thing> things = identifiers.stream().map(i -> getVariable(i, variables)).map(v -> v.thing).collect(Collectors.toList());
        Constructor constructor = blueprint.constructors.stream().filter(c -> {
            for (int i = 0; i < identifiers.size(); i++) {
                if (c.parameters.get(i).blueprint.equals(getVariable(identifiers.get(i), variables).getThing().blueprint)) {
                    return false;
                }
            }
            return true;
        }).findFirst().get();
        Variable variable = new Variable(identifier, constructor.construct(things));
    }

    private Variable getVariable(String identifier, Set<Variable> variables) {
        return variables.stream().filter(v -> v.identifier.equals(identifier)).findFirst().get();
    }

}
