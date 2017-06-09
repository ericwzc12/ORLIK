package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.Instruction;
import school.ahs.ORLIK.Runtime.Variable;
import school.ahs.ORLIK.StandardLibrary.Int32;

import java.util.Set;

public class Int32Expression implements Instruction {

    public final String identifier;
    public final String expression;

    public Int32Expression(String statement) throws IllegalArgumentException {
        validateAssignmentStatement(statement);

        int space1 = statement.indexOf(' ');
        int paren1 = statement.indexOf('(');
        int paren2 = statement.indexOf(')');
        int space2 = statement.indexOf(' ', paren2 + 1);
        int space3 = statement.indexOf(' ', space2 + 1);

        validateIndexes(space1, paren1, paren2, space2, space3);

        expression = statement.substring(paren1 + 1, paren2);

        this.identifier = statement.substring(space3 + 1);
    }

    private void validateAssignmentStatement(String statement) throws IllegalArgumentException {
        if (!statement.startsWith("givemeanew Int32(")) {
            throw new IllegalArgumentException("Assignment statement does not start with 'givemeanew Int32('");
        }
        if (!statement.contains(") called ")) {
            throw new IllegalArgumentException("Assignment statement does not contain ' of '");
        }
    }

    private void validateIndexes(int... indexes) throws IllegalArgumentException {
        for (int i = 0; i < indexes.length - 1; i++) {
            if (indexes[i] < 0 || indexes[i] > indexes[i + 1]) {
                throw new IllegalArgumentException("Indexes out of order or not present.");
            }
        }
    }

    @Override
    public void execute(Set<Variable> variables) {
        ExpressionEvaluator ee = new ExpressionEvaluator();
        Variable variable = new Variable(identifier, ee.evaluate(expression, variables));
        variables.add(variable);
    }

}
