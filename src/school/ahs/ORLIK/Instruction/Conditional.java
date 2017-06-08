package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.Block;
import school.ahs.ORLIK.Runtime.Instruction;
import school.ahs.ORLIK.Runtime.Variable;

import java.util.Set;
import java.util.Stack;

public abstract class Conditional implements Instruction{

    private String statement;
    private Block block;

    public Conditional(String statement, Block block) throws IllegalArgumentException{

        this.statement = statement.replace(" ", "");
        this.block = block;

        validate();

    }

    public String getStatement(){

        return statement;

    }

    public Block getBlock(){

        return block;

    }

    private void validate() throws IllegalArgumentException{

        int i = getLoopName().length();
        if(statement.indexOf(getLoopName()) == 0){
            if(statement.charAt(i) != '('){
                throw new IllegalArgumentException();
            }
        }
        else
            throw new IllegalArgumentException();

        Stack<Integer> parentheses = new Stack<>();
        for(; i < statement.length(); i++) {

            if(statement.charAt(i) == '(')
                parentheses.push(1);
            else if(statement.charAt(i) == ')') {
                if(parentheses.isEmpty())
                    throw new IllegalArgumentException("Invalid Parentheses");
                parentheses.pop();
            }
        }

        if(!parentheses.isEmpty())
            throw new IllegalArgumentException("Invalid parentheses");

    }

    public abstract String getLoopName();

    @Override
    public abstract void execute(Set<Variable> variables);
}
