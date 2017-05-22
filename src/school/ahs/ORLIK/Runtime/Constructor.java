package school.ahs.ORLIK.Runtime;

import java.util.*;

public class Constructor {

    public final String identifier;
    public final Block block;
    public final List<Parameter> parameters;
    public final Blueprint blueprint;

    public Constructor() {
        this.identifier = "";
        this.block = new Block();
        this.parameters = new ArrayList<>();
        this.blueprint = new Blueprint();
    }

    public Constructor(String identifier, Block block, List<Parameter> parameters, Blueprint blueprint) {
        this.identifier = identifier;
        this.block = block;
        this.parameters = new ArrayList<>(parameters);
        this.blueprint = blueprint;
    }

    public Block getBlock() {
        return block;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public Thing construct(List<Thing> things) throws IllegalArgumentException {
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

        Thing thing = new Thing(blueprint, variables, blueprint.functions);

        block.execute(variables);

        return thing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constructor that = (Constructor) o;
        return Objects.equals(block, that.block) &&
                Objects.equals(parameters, that.parameters) &&
                Objects.equals(blueprint, that.blueprint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, parameters, blueprint);
    }

}
