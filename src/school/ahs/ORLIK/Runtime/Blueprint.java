package school.ahs.ORLIK.Runtime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Blueprint {

    public final Set<Constructor> constructors;
    public final Set<Function> functions;
    public final Set<Variable> variables;

    public Blueprint() {
        this.constructors = new HashSet<>();
        this.functions = new HashSet<>();
        this.variables = new HashSet<>();
    }

    public Blueprint(List<Constructor> constructors, List<Function> functions, List<Variable> variables) {
        this.constructors = new HashSet<>(constructors);
        this.functions = new HashSet<>(functions);
        this.variables = new HashSet<>(variables);
    }

    public Set<Constructor> getConstructors() {
        return constructors;
    }

    public Set<Function> getFunctions() {
        return functions;
    }

    public Set<Variable> getVariables() {
        return variables;
    }

}
