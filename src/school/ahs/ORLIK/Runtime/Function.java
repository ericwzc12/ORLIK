package school.ahs.ORLIK.Runtime;

import java.util.*;

public class Function {

    public final String identifier;
    public final Block block;
    public final List<Parameter> parameters;

    public Function() {
        this.identifier = null;
        this.block = new Block();
        this.parameters = new ArrayList<>();
    }

    public Function(String identifier, Block block, List<Parameter> parameters) {
        this.identifier = identifier;
        this.block = block;
        this.parameters = new ArrayList<>(parameters);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Block getBlock() {
        return block;
    }

    public void call(List<Thing> things) {
        if (parameters.size() != things.size()) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }

        Set<Variable> variables = new HashSet<>();
        Iterator<Thing> thingIterator = things.iterator();
        Iterator<Parameter> parameterIterator = parameters.iterator();
        while (thingIterator.hasNext() && parameterIterator.hasNext()) {
            Thing thing = thingIterator.next();
            Parameter parameter = parameterIterator.next();
            if (thing.blueprint != parameter.blueprint) {
                throw new IllegalArgumentException("Invalid argument blueprints.");
            }

            variables.add(new Variable(parameter.identifier, thing));
        }

        block.execute(variables);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return Objects.equals(identifier, function.identifier) &&
                Objects.equals(block, function.block);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, block);
    }
    
}
