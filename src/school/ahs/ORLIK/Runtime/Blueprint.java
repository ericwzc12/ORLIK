package school.ahs.ORLIK.Runtime;

import java.util.*;

public class Blueprint extends Thing {

    public final String identifier;
    public final Set<Constructor> constructors;

    public Blueprint(String identifier, List<Constructor> constructors) {
        this.identifier = identifier;
        this.constructors = new HashSet<>(constructors);
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

}
