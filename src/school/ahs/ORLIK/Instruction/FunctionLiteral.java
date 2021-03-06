package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.*;
import school.ahs.ORLIK.Runtime.Runtime;

import java.util.*;
import java.util.stream.Collectors;

public class FunctionLiteral implements Instruction {

    private final String identifier;
    private final List<String> paramIdentifiers;
    private final String returnTypeIdentifier;
    private final Block block;

    public FunctionLiteral(String code, Block block) {
        if (!code.startsWith("do ")) {
            throw new IllegalArgumentException();
        }
        int nowIndex = code.indexOf(" now(");
        if (nowIndex == -1) {
            throw new IllegalArgumentException();
        }
        int closeParenIndex = code.indexOf(')', nowIndex);
        this.identifier = code.substring(3, nowIndex);
        this.paramIdentifiers = createParamIdentifiers(code.substring(nowIndex + 5, closeParenIndex));

        int givesIndex = code.indexOf(" gives ", closeParenIndex);
        if (givesIndex != -1) {
            returnTypeIdentifier = code.substring(givesIndex + 7);
        } else {
            returnTypeIdentifier = null;
        }

        this.block = block;
    }

    private List<String> createParamIdentifiers(String inParens) {
        if (inParens.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(inParens.split(",")));
    }

    private Variable getVariable(String identifier, Set<Variable> variables) {
        return variables.stream().filter(v -> v.getIdentifier().equals(identifier)).findFirst().get();
    }

    @Override
    public void execute(Set<Variable> variables) {
        List<Parameter> parameters = paramIdentifiers.stream().map(i -> {
            String[] thingIdentifierBlueprintIdentifier = i.split(" called ");
            String thingIdentifier = thingIdentifierBlueprintIdentifier[1];
            String blueprintIdentifier = thingIdentifierBlueprintIdentifier[0];
            Blueprint blueprint = (Blueprint) variables.stream().filter(v -> v.getIdentifier().equals(blueprintIdentifier)).findFirst().get().getThing();
            return new Parameter(thingIdentifier, blueprint);
        }).collect(Collectors.toList());
        Blueprint returns;
        if (returnTypeIdentifier != null) {
            returns = (Blueprint) variables.stream().filter(v -> v.getIdentifier().equals(returnTypeIdentifier)).findAny().get().getThing();
        } else {
            returns = null;
        }
        Function function = new Function(block, parameters, returns);
        variables.add(new Variable(identifier, function));
    }

}
