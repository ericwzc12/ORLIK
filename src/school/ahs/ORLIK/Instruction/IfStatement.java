package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.Block;
import school.ahs.ORLIK.Runtime.Instruction;
import school.ahs.ORLIK.Runtime.Runtime;
import school.ahs.ORLIK.Runtime.Variable;
import java.util.Set;
import java.util.Stack;

public class IfStatement implements Instruction{

    private Block block;
    private String statement;
    private String expressions;

    public IfStatement(String statement, Block block) throws IllegalArgumentException{

        this.block = block;
        this.statement = statement.replace(" ", "");
        expressions = "&& || == !=";
        validate();

    }

    private void validate() throws IllegalArgumentException{
        int i = 15;
        if(statement.indexOf("anybodywantsome") == 0){
            if(statement.charAt(i) != '('){
                throw new IllegalArgumentException("Invalid if statement");
            }
        }
        else
            throw new IllegalArgumentException("Invalid if statement");

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

    @Override
    public void execute(Set<Variable> variables) {
        BooleanEvaluator eval = new BooleanEvaluator();
        if(eval.evaluate(statement.substring(15), variables))
            block.execute(variables);
    }

    /*private boolean recursiveEval(String expression, Set<Variable> variables){

        Stack<Integer> parentheses = new Stack<>();
        expression = expression.substring(1, expression.length() - 1);

        System.err.println(expression);
        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '('){
                parentheses.push(0);
            } else if(expression.charAt(i) == ')'){
                parentheses.pop();
            } else if(parentheses.isEmpty() && i + 2 < expression.length() && expressions.contains(expression.substring(i, i+2))){
                switch( expression.substring(i, i+2)){
                    case "==": return recursiveEval(expression.substring(0, i), variables) == recursiveEval(expression.substring(i+2, expression.length()), variables);
                    case "&&": return recursiveEval(expression.substring(0, i), variables) && recursiveEval(expression.substring(i+2, expression.length()), variables);
                    case "||": return recursiveEval(expression.substring(0, i), variables) || recursiveEval(expression.substring(i+2, expression.length()), variables);
                    case "!=": return recursiveEval(expression.substring(0, i), variables) != recursiveEval(expression.substring(i+2, expression.length()), variables);
                }
            }
        }
        System.err.print(expression);
        switch(expression){
            case "true": return true;
            case "false": return false;
            default: return ((Int32)getVariable(expression, variables).getThing()).getValue() != 0;
        }
    }

    private Variable getVariable(String identifier, Set<Variable> variables) {
        return variables.stream().filter(v -> v.identifier.equals(identifier)).findFirst().get();
    }*/

}
