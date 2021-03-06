package school.ahs.ORLIK.Runtime;

import java.util.*;

public class Function extends Thing {

    private final Block block;
    private final List<Parameter> parameters;
    private final Blueprint returns;

    public Function() {
        this.block = new Block();
        this.parameters = new ArrayList<>();
        this.returns = null;
    }

    public Function(Block block, List<Parameter> parameters, Blueprint returns) {
        this.block = block;
        this.parameters = new ArrayList<>(parameters);
        this.returns = returns;
    }

    public Block getBlock() {
        return block;
    }

    public Optional<Thing> call(List<Thing> things, Set<Variable> environment) {
        if (parameters.size() != things.size()) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }

        Set<Variable> variables = new HashSet<>(environment);
        Iterator<Thing> thingIterator = things.iterator();
        Iterator<Parameter> parameterIterator = parameters.iterator();
        while (thingIterator.hasNext() && parameterIterator.hasNext()) {
            Thing thing = thingIterator.next();
            Parameter parameter = parameterIterator.next();
            if (thing.getBlueprint().get() != parameter.getBlueprint()) {
                throw new IllegalArgumentException("Invalid argument blueprints.");
            }

            variables.add(new Variable(parameter.getIdentifier(), thing));
        }

        block.execute(variables);
        if (returns != null) {
            Variable returnsVariable = variables.stream().filter(v -> v.getIdentifier().equals("return")).findFirst().orElseThrow(() -> new IllegalArgumentException());
            if (returnsVariable.getThing().getBlueprint().get() != returns) {
                throw new IllegalArgumentException();
            }
            return Optional.of(returnsVariable.getThing());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Function function = (Function) o;
        return Objects.equals(block, function.block) &&
                Objects.equals(parameters, function.parameters) &&
                Objects.equals(returns, function.returns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), block, parameters, returns);
    }
}
