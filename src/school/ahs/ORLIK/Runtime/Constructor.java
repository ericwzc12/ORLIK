package school.ahs.ORLIK.Runtime;

import java.util.ArrayList;
import java.util.List;

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

}
