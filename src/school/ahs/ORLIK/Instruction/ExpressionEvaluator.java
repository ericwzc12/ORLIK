package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.Runtime.Variable;
import school.ahs.ORLIK.StandardLibrary.Int32;

import java.util.Set;

public class ExpressionEvaluator {

    public enum InfixOperators {
        ADDITION {
            public Int32 operate(Int32 lhs, Int32 rhs) {
                return new Int32(lhs.getValue() + rhs.getValue());
            }
            public String getSymbol() {
                return "+";
            }
        },
        SUBTRACTION {
            public Int32 operate(Int32 lhs, Int32 rhs) {
                return new Int32(lhs.getValue() - rhs.getValue());
            }
            public String getSymbol() {
                return "-";
            }
        };

        abstract Int32 operate(Int32 lhs, Int32 rhs);
        abstract String getSymbol();
    }

    public Int32 evaluate(String expression, Set<Variable> variables) {
        if (expression.startsWith("(") && expression.endsWith(")")) {
            expression = expression.substring(1, expression.length() - 1);
        }

        try {
            return new Int32(Integer.parseInt(expression));
        } catch (Exception e) {
            int parens = 0;
            char[] expr = expression.toCharArray();
            for (int i = 0; i < expr.length; i++) {
                switch (expr[i]) {
                    case '(': parens++;
                    case ')': parens--;
                }
            }

            int space1 = expression.indexOf(" ");
            int space2 = expression.indexOf(" ", space1 + 1);
            if (space1 == -1 || space2 == -1) {
                throw new RuntimeException();
            }

            String firstIdentifier, secondIdentifier;

            if (parens != 0) {
                int firstOpenParen = expression.indexOf('(');
                int firstCloseParen = expression.indexOf(')', firstOpenParen);
                int secondOpenParen = expression.indexOf('(', firstCloseParen);
                int secondCloseParen = expression.indexOf(')', secondOpenParen);

                firstIdentifier = expression.substring(firstOpenParen + 1, firstCloseParen);
                secondIdentifier = expression.substring(secondOpenParen + 1, secondCloseParen);
            } else {
                firstIdentifier = expression.substring(0, space1);
                secondIdentifier = expression.substring(space2 + 1);
            }

            Int32 lhs, rhs;

            try {
                lhs = new Int32(Integer.parseInt(firstIdentifier));
            } catch (Exception e1) {
                lhs = getInt32(firstIdentifier, variables);
            }

            try {
                rhs = new Int32(Integer.parseInt(secondIdentifier));
            } catch (Exception e1) {
                rhs = getInt32(secondIdentifier, variables);
            }

            String operator = expression.substring(space1 + 1, space2);
            if (operator.equals(InfixOperators.ADDITION.getSymbol())) {
                return InfixOperators.ADDITION.operate(lhs, rhs);
            } else if (operator.equals(InfixOperators.SUBTRACTION.getSymbol())) {
                return InfixOperators.SUBTRACTION.operate(lhs, rhs);
            } else {
                throw new RuntimeException();
            }
        }
    }

    private Int32 getInt32(String identifier, Set<Variable> variables) {
        return (Int32) variables.stream().filter(v -> v.getIdentifier().equals(identifier)).findAny().get().getThing();
    }

}
