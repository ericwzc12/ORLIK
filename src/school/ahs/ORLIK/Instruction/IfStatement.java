package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.Block;
import school.ahs.ORLIK.Runtime.Instruction;
import school.ahs.ORLIK.Runtime.Variable;
import java.util.Set;

public class IfStatement extends Conditional implements Instruction{

    public IfStatement(String statement, Block block) throws IllegalArgumentException{
        super(statement, block);
    }

    @Override
    public String getLoopName(){

        return "anybodywantsome";

    }

    @Override
    public void execute(Set<Variable> variables) {
        BooleanEvaluator eval = new BooleanEvaluator();
        if(eval.evaluate(super.getStatement().substring(15), variables))
            super.getBlock().execute(variables);
    }

}
