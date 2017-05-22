package school.ahs.ORLIK.Runtime;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Runtime {

    public static void main(String[] args) {

    }

    public final Set<Blueprint> blueprints;

    public Runtime(InputStream inputStream) {
        this.blueprints = new HashSet<>();
    }

    public Optional<Blueprint> getBlueprint(String identifier) {
        return blueprints.stream().filter(b -> b.identifier.equals(identifier)).findFirst();
    }

}
