package school.ahs.ORLIK.Runtime;

import school.ahs.ORLIK.Instruction.Assignment;
import school.ahs.ORLIK.Instruction.IfStatement;
import school.ahs.ORLIK.Instruction.Print;

import java.io.File;
import java.util.*;

public class Runtime extends Block implements Instruction {

    public final String code;
    public final Set<Blueprint> blueprints;

    public Runtime(String code) {
        this.blueprints = new HashSet<>();
        this.code = code.replace("\n", "");
    }

    public Optional<Blueprint> getBlueprint(String identifier) {
        return blueprints.stream().filter(b -> b.identifier.equals(identifier)).findFirst();
    }

    private Instruction getInstruction(String str) {
        try {
            return new Assignment(str, this);
        } catch (Exception e1) {
            try {
                return new Print(str, this);
            } catch (Exception e2) {
                return null;
            }
        }
    }

    private Instruction getInstruction(String str, Block block) {
        try {
            return new IfStatement(str, this, block);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void execute(Set<Variable> variables) {
        int cursor = 0;
        int nextSemicolon = code.indexOf(';');
        int nextBrace = code.indexOf('{');

        while (nextSemicolon != -1 && nextBrace != -1) {
            if (nextSemicolon < nextBrace) {
                String raw = code.substring(cursor, nextSemicolon);
                Instruction i = getInstruction(raw);
                i.execute(variables);
                cursor = nextSemicolon + 1;
            } else {
                int braceCount = 1;
                int closeBrace = nextBrace + 1;
                while (braceCount > 0) {
                    closeBrace += 1;
                    if (code.charAt(closeBrace) == '}') {
                        braceCount -= 1;
                    } else if (code.charAt(closeBrace) == '{') {
                        braceCount += 1;
                    }
                }
                String raw = code.substring(cursor, nextBrace);
                String rawBlock = code.substring(nextBrace + 1, closeBrace);
                Block block = new Runtime(rawBlock);
                Instruction instruction = getInstruction(raw, block);
                instruction.execute(variables);
                cursor = closeBrace + 1;
            }
        }

        if (nextSemicolon != -1) {
            String raw = code.substring(cursor, nextSemicolon);
            Instruction i = getInstruction(raw);
            i.execute(variables);
        } else if (nextBrace != -1) {
            int braceCount = 1;
            int closeBrace = nextBrace + 1;
            while (braceCount > 0 && closeBrace != code.length()) {
                closeBrace += 1;
                if (code.charAt(closeBrace) == '}') {
                    braceCount -= 1;
                } else if (code.charAt(closeBrace) == '{') {
                    braceCount += 1;
                }
            }
            String raw = code.substring(cursor, nextBrace);
            String rawBlock = code.substring(nextBrace + 1, closeBrace);
            Block block = new Runtime(rawBlock);
            Instruction instruction = getInstruction(raw, block);
            instruction.execute(variables);
        }
    }
}
