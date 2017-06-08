package school.ahs.ORLIK.Instruction;

import school.ahs.ORLIK.StandardLibrary.Int32;

public class ExpressionEvaluator {

    public enum InfixExpression {
        ADDITION {
            public Int32 operate(Int32 lhs, Int32 rhs) {
                return new Int32(lhs.getValue() + rhs.getValue());
            }
        },
        SUBTRACTION {
            public Int32 operate(Int32 lhs, Int32 rhs) {
                return new Int32(lhs.getValue() + rhs.getValue());
            }
        };

        abstract Int32 operate(Int32 lhs, Int32 rhs);
        abstract
    }

    public ExpressionEvaluator(String instruction) {

    }

}
