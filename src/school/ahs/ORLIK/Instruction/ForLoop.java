package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.Block;
import school.ahs.ORLIK.Runtime.Instruction;
import school.ahs.ORLIK.Runtime.Variable;

import java.util.Set;

/**
 * Created by Eric on 06/06/2017.
 */
public class ForLoop extends Conditional implements Instruction{

    public ForLoop(String statement, Block block){
        super(statement, block);
    }
    @Override
    public String getLoopName() {
        return null;
    }

    @Override
    public void execute(Set<Variable> variables) {

    }

}
