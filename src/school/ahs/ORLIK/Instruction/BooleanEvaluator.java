package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.Variable;
import school.ahs.ORLIK.StandardLibrary.Int32;
import java.util.Set;
import java.util.Stack;

public class BooleanEvaluator {

    private String operators;

    public BooleanEvaluator(){

        operators = "&& || == !=";

    }

    public boolean evaluate(String expression, Set<Variable> variables){
        return recursiveEval(expression, variables);

    }

    private boolean recursiveEval(String expression, Set<Variable> variables){

        if(expression.indexOf('(') == 0)
            expression = expression.substring(1, expression.length() - 1);

        Stack<Integer> parentheses = new Stack<>();

        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '('){
                parentheses.push(0);
            } else if(expression.charAt(i) == ')'){
                parentheses.pop();
            } else if(parentheses.isEmpty() && i + 2 < expression.length() && operators.contains(expression.substring(i, i+2))){
                switch( expression.substring(i, i+2)){
                    case "==": return recursiveEval(expression.substring(0, i), variables) == recursiveEval(expression.substring(i+2, expression.length()), variables);
                    case "&&": return recursiveEval(expression.substring(0, i), variables) && recursiveEval(expression.substring(i+2, expression.length()), variables);
                    case "||": return recursiveEval(expression.substring(0, i), variables) || recursiveEval(expression.substring(i+2, expression.length()), variables);
                    case "!=": return recursiveEval(expression.substring(0, i), variables) != recursiveEval(expression.substring(i+2, expression.length()), variables);
                }
            }
        }
        switch(expression){
            case "true": return true;
            case "false": return false;
            default: return ((Int32) getVariable(expression, variables).getThing()).getValue() != 0;
        }

    }

    private Variable getVariable(String identifier, Set<Variable> variables) {
        return variables.stream().filter(v -> v.identifier.equals(identifier)).findFirst().get();
    }
}
