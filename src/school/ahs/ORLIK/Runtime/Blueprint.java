package school.ahs.ORLIK.Runtime;

import java.util.ArrayList;
import java.util.List;

public class Blueprint {

    public final List<Constructor> constructors;
    public final List<Function> functions;
    public final List<Variable> variables;

    public Blueprint(List<Constructor> constructors, List<Function> functions, List<Variable> variables) {
        this.constructors = new ArrayList<>(constructors);
        this.functions = new ArrayList<>(functions);
        this.variables = new ArrayList<>(variables);
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public List<Variable> getVariables() {
        return variables;
    }
    
}
