package school.ahs.ORLIK.Runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Constructor {

    public final String identifier;
    public final Block block;
    public final List<Variable> params;

    public Constructor() {
        this.identifier = null;
        this.block = new Block();
        this.params = new ArrayList<>();
    }

    public Constructor(String identifier, Block block, List<Variable> params) {
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

    public List<Variable> getParams() {
        return params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constructor that = (Constructor) o;
        return Objects.equals(identifier, that.identifier) &&
                Objects.equals(block, that.block) &&
                Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, block, params);
    }

}
