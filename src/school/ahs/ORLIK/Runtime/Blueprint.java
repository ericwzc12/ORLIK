package school.ahs.ORLIK.Runtime;

import java.util.*;

public class Blueprint {

    public final String identifier;
    public final Set<Constructor> constructors;
    public final Set<Function> functions;
    public final Set<Variable> variables;

    public Blueprint() {
        this.identifier = "";
        this.constructors = new HashSet<>();
        this.functions = new HashSet<>();
        this.variables = new HashSet<>();
    }

    public Blueprint(String identifier, List<Constructor> constructors, List<Function> functions, List<Variable> variables) {
        this.identifier = identifier;
        this.constructors = new HashSet<>(constructors);
        this.functions = new HashSet<>(functions);
        this.variables = new HashSet<>(variables);
    }

    public Set<Constructor> getConstructors() {
        return constructors;
    }

    public Optional<Constructor> getConstructor(String identifier) {
        return constructors.stream().filter(c -> c.identifier.equals(identifier)).findFirst();
    }

    public Set<Function> getFunctions() {
        return functions;
    }

    public Set<Variable> getVariables() {
        return variables;
    }

}
