package school.ahs.ORLIK.Runtime;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Thing {

    public final Optional<Blueprint> blueprint;
    public final Set<Variable> variables;
    public final Set<Function> functions;

    public Thing() {
        blueprint = Optional.empty();
        variables = new HashSet<>();
        functions = new HashSet<>();
    }

    public Thing(Blueprint blueprint, Set<Variable> variables, Set<Function> functions) {
        this.blueprint = Optional.of(blueprint);
        this.variables = new HashSet<>(variables);
        this.functions = new HashSet<>(functions);
    }

    public Set<Function> getFunctions() {
        return functions;
    }

    public Set<Variable> getVariables() {
        return variables;
    }

    public Optional<Variable> getVariable(String identifier) {
        return variables.stream().filter(v -> v.identifier.equals(identifier)).findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thing thing = (Thing) o;
        return Objects.equals(functions, thing.functions) &&
                Objects.equals(variables, thing.variables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(functions, variables);
    }

}
