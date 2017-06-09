package school.ahs.ORLIK.Runtime;

import java.util.Objects;

public class Variable {

    private final String identifier;
    private final Thing thing;

    public Variable(String identifier, Thing thing) {
        this.identifier = identifier;
        this.thing = thing;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Thing getThing() {
        return thing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(identifier, variable.identifier) &&
                Objects.equals(thing, variable.thing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, thing);
    }

}
