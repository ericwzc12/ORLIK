package school.ahs.ORLIK.Instruction;

import com.sun.javaws.exceptions.InvalidArgumentException;
import school.ahs.ORLIK.Runtime.Block;
import school.ahs.ORLIK.Runtime.Instruction;
import school.ahs.ORLIK.Runtime.Variable;

import java.util.Set;

public class WhileLoop extends Conditional implements Instruction{

    public WhileLoop(String statement, Block block) throws InvalidArgumentException{

        super(statement, block);
    }

    @Override public String getLoopName(){

        return "O";

    }

    @Override
    public void execute(Set<Variable> variables) {
        BooleanEvaluator eval = new BooleanEvaluator();
        while(eval.evaluate(super.getStatement().substring(1), variables)){
            super.getBlock().execute(variables);
        }

    }
}