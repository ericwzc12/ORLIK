package school.ahs.ORLIK.Runtime;

import java.util.*;

public class Blueprint extends Thing {

    private final Set<Constructor> constructors;

    public Blueprint(List<Constructor> constructors) {
        this.constructors = new HashSet<>(constructors);
    }

    public Set<Constructor> getConstructors() {
        return constructors;
    }

    public Optional<Constructor> getConstructor(String identifier) {
        return constructors.stream().filter(c -> c.getIdentifier().equals(identifier)).findFirst();
    }

    public Set<Function> getFunctions() {
        return getFunctions();
    }

}
