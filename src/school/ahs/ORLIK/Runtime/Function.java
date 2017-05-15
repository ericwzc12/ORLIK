package school.ahs.ORLIK.Runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Function {

    public final String identifier;
    public final Block block;
    public final List<Variable> params;

    public Function() {
        this.identifier = null;
        this.block = new Block();
        this.params = new ArrayList<>();
    }

    public Function(String identifier, Block block, List<Variable> params) {
        this.identifier = identifier;
        this.block = block;
        this.params = new ArrayList<>(params);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return Objects.equals(identifier, function.identifier) &&
                Objects.equals(block, function.block);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, block);
    }
    
}
