package school.ahs.ORLIK.Runtime;

import java.util.Objects;

public class Parameter {

    private final String identifier;
    private final Blueprint blueprint;

    public Parameter(String identifier, Blueprint blueprint) {
        this.identifier = identifier;
        this.blueprint = blueprint;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Blueprint getBlueprint() {
        return blueprint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(identifier, parameter.identifier) &&
                Objects.equals(blueprint, parameter.blueprint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, blueprint);
    }
}
