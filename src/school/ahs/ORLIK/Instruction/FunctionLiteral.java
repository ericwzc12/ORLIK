package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.*;
import school.ahs.ORLIK.Runtime.Runtime;

import java.util.*;
import java.util.stream.Collectors;

public class FunctionLiteral implements Instruction {

    public final String identifier;
    public final List<String> paramIdentifiers;
    public final String returnTypeIdentifier;
    public final Block block;

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
        return variables.stream().filter(v -> v.identifier.equals(identifier)).findFirst().get();
    }

    @Override
    public void execute(Set<Variable> variables) {
        List<Parameter> parameters = paramIdentifiers.stream().map(i -> {
            String[] thingIdentifierBlueprintIdentifier = i.split(" called ");
            String thingIdentifier = thingIdentifierBlueprintIdentifier[0];
            String blueprintIdentifier = thingIdentifierBlueprintIdentifier[1];
            Blueprint blueprint = (Blueprint) variables.stream().filter(v -> v.identifier.equals(blueprintIdentifier)).findFirst().get().thing;
            return new Parameter(thingIdentifier, blueprint);
        }).collect(Collectors.toList());
        Blueprint returns;
        if (returnTypeIdentifier != null) {
            returns = (Blueprint) variables.stream().filter(v -> v.identifier.equals(returnTypeIdentifier)).findAny().get().thing;
        } else {
            returns = null;
        }
        Function function = new Function(block, parameters, returns);
        variables.add(new Variable(identifier, function));
    }

}
