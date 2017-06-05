package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.Function;
import school.ahs.ORLIK.Runtime.Instruction;
import school.ahs.ORLIK.Runtime.Thing;
import school.ahs.ORLIK.Runtime.Variable;

import java.util.*;
import java.util.stream.Collectors;

public class FunctionCall implements Instruction {

    public final Optional<String> returnIdentifier;
    public final String functionIdentifier;
    public final List<String> parameterIdentifiers;

    public FunctionCall(String code) {
        int equals = code.indexOf('=');
        if (equals != -1) {
            returnIdentifier = Optional.of(code.substring(0, equals).trim());
        } else {
            returnIdentifier = Optional.empty();
        }

        int firstParen = code.indexOf('(');
        if (firstParen == -1) {
            throw new IllegalArgumentException();
        }
        functionIdentifier = code.substring(equals + 1, firstParen);
        int secondParen = code.indexOf(')', firstParen);
        if (secondParen == -1) {
            throw new IllegalArgumentException();
        }
        String inParens = code.substring(firstParen + 1, secondParen);
        if (inParens.isEmpty()) {
            parameterIdentifiers = new ArrayList<>();
        } else {
            parameterIdentifiers = new ArrayList<>(Arrays.asList(inParens.split(",")));
        }
    }

    @Override
    public void execute(Set<Variable> variables) {
        List<Thing> things = parameterIdentifiers.stream().map(i -> getVariable(i, variables)).map(v -> v.thing).collect(Collectors.toList());
        Function function = (Function) getVariable(functionIdentifier, variables).thing;
        function.call(things).ifPresent(t -> returnIdentifier.ifPresent(i -> variables.add(new Variable(i, t))));
    }

    private Variable getVariable(String identifier, Set<Variable> variables) {
        return variables.stream().filter(v -> v.identifier.equals(identifier)).findAny().get();
    }
}
