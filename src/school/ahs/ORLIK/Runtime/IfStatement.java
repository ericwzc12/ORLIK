package school.ahs.ORLIK.Runtime;


import java.util.Set;
import java.util.Stack;

public class IfStatement implements Instruction{

    private Block block;
    private String statement;

    private IfStatement(String statement, Runtime runtime, Block block) throws IllegalArgumentException{

        this.block = block;
        this.statement = statement.replace(" ", "");
        validate();
        System.out.print("If statement successfully created");

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



    }

    private boolean eval(Set<Variable> variables){

        Stack<Character> expressions = new Stack<>();
        Stack<String> first = new Stack<>();

        for(int i = 15; i < statement.length(); i++){

            if(statement.charAt(i) == '('){
                first.push(statement.substring(i+1, endOfTerm(i+1)));
            }

        }

    }

    private int endOfTerm(int beginIndex){

        int i = beginIndex;
        while(i < statement.length() && Character.isLetter(statement.charAt(i))){
            i++;
        }
        return i;

    }

    public static void main(String[] args){

        IfStatement test = new IfStatement("anybodywantsome(x < y)", new Block(), new Runtime());

    }

}
