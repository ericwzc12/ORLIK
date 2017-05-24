package school.ahs.ORLIK.Runtime;

import java.util.*;

public class Blueprint {

    public final String identifier;
    public final Set<Constructor> constructors;
    public final Set<Function> functions;
    public final Set<Parameter> fields;

    public Blueprint() {
        this.identifier = "";
        this.constructors = new HashSet<>();
        this.functions = new HashSet<>();
        this.fields = new HashSet<>();
    }

    public Blueprint(String identifier, List<Constructor> constructors, List<Function> functions, List<Parameter> fields) {
        this.identifier = identifier;
        this.constructors = new HashSet<>(constructors);
        this.functions = new HashSet<>(functions);
        this.fields = new HashSet<>(fields);
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

    public Optional<Function> getFunction(String identifier) {
        return functions.stream().filter(f -> f.identifier.equals(identifier)).findFirst();
    }

    public Set<Parameter> getFields() {
        return fields;
    }

    public Optional<Function> getField(String identifier) {

    }

}
