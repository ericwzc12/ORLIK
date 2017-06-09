package school.ahs.ORLIK.Runtime;

import school.ahs.ORLIK.Instruction.*;
import school.ahs.ORLIK.StandardLibrary.Int32;

import java.io.File;
import java.util.*;

public class Runtime extends Block implements Instruction {

    private final String code;
    private final boolean shouldInitializeStandardLibrary;

    public Runtime(String code) {
        this(code, false);
    }

    public Runtime(String code, boolean initializeStandardLibrary) {
        this.code = code.replace("\n", "");
        this.shouldInitializeStandardLibrary = initializeStandardLibrary;
    }

    private Instruction getInstruction(String str) {
        try {
            Int32Expression int32Expression = new Int32Expression(str);
            System.err.println("Created Int32Expression from instruction:" + str);
            return int32Expression;
        } catch (Exception e5) {
            try {
                Print print = new Print(str);
                System.err.println("Created Print from instruction: " + str);
                return print;
            } catch (Exception e2) {
                try {
                    FunctionCall functionCall = new FunctionCall(str);
                    System.err.println("Created FunctionCall from instruction: " + str);
                    return functionCall;
                } catch (Exception e4) {
                    try {
                        Assignment assignment = new Assignment(str);
                        System.err.println("Created Assignment from instruction: " + str);
                        return assignment;
                    } catch (Exception e1) {
                        return null;
                    }
                }
            }
        }
    }

    private Instruction getInstruction(String str, Block block) {
        try {
            IfStatement ifStatement = new IfStatement(str, block);
            System.err.println("Created IfStatement from instruction: " + str);
            return ifStatement;
        } catch (Exception e) {
            try {
                FunctionLiteral functionLiteral = new FunctionLiteral(str, block);
                System.err.println("Created FunctionLiteral from instruction: " + str);
                return functionLiteral;
            } catch (Exception e2) {
                try {
                    WhileLoop whileLoop = new WhileLoop(str, block);
                    System.err.println("Created WhileLoop from instruction: " + str);
                    return whileLoop;
                } catch (Exception e3) {
                    return null;
                }
            }
        }
    }

    @Override
    public void execute(Set<Variable> variables) {
        if (shouldInitializeStandardLibrary) {
            initializeStandardLibrary(variables);
        }

        System.err.println("Executing runtime with variables: " + variables + " code: " + code);

        int cursor = 0;
        int nextSemicolon = code.indexOf(';');
        int nextBrace = code.indexOf('{');

        while (nextSemicolon != -1 && nextBrace != -1) {
            if (nextSemicolon < nextBrace) {
                interpretRawInstruction(cursor, nextSemicolon, variables);
                cursor = nextSemicolon + 1;
            } else {
                cursor = interpretRawBlock(cursor, nextBrace, variables) + 1;
            }
            nextSemicolon = code.indexOf(';', cursor);
            nextBrace = code.indexOf('{', cursor);
        }

        while (nextSemicolon != -1) {
            interpretRawInstruction(cursor, nextSemicolon, variables);
            cursor = nextSemicolon + 1;
            nextSemicolon = code.indexOf(';', cursor);
        }
        while (nextBrace != -1) {
            cursor = interpretRawBlock(cursor, nextBrace, variables) + 1;
            nextBrace = code.indexOf('{', cursor);
        }
    }

    private void interpretRawInstruction(int cursor, int nextSemicolon, Set<Variable> variables) {
        String raw = code.substring(cursor, nextSemicolon).trim();
        Instruction i = getInstruction(raw);
        if (i != null) {
            i.execute(variables);
        } else {
            System.err.println("Could not interpret raw instruction: " + raw);
        }
    }

    private int interpretRawBlock(int cursor, int nextBrace, Set<Variable> variables) {
        int braceCount = 1;
        int closeBrace = nextBrace;
        while (braceCount > 0 && closeBrace < code.length() - 1) {
            closeBrace += 1;
            if (code.charAt(closeBrace) == '}') {
                braceCount -= 1;
            } else if (code.charAt(closeBrace) == '{') {
                braceCount += 1;
            }
        }
        if (closeBrace == code.length()) {
            throw new RuntimeException("Runtime error: Braces must be balanced.");
        }
        String raw = code.substring(cursor, nextBrace).trim();
        String rawBlock = code.substring(nextBrace + 1, closeBrace).trim();
        System.err.println("Created raw block: " + rawBlock);
        Block block = new Runtime(rawBlock);
        Instruction instruction = getInstruction(raw, block);
        if (instruction != null) {
            instruction.execute(variables);
        } else {
            System.err.println("Could not get block instruction from: " + raw);
        }
        return closeBrace;
    }

    private void initializeStandardLibrary(Set<Variable> variables) {
        System.err.println("Initializing standard library");

        Blueprint int32Blueprint = Int32.getInt32Blueprint();
        variables.add(new Variable("Int32", int32Blueprint));
    }

}
