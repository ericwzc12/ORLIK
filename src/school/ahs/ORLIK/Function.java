package school.ahs.ORLIK;

import java.util.List;

public class Function extends Scope {

    private List<Blueprint> param;
    private Blueprint returnType;

    public Function(String name, List<Instruction> instructions, List<Blueprint> param, Blueprint returnType) {
        super(name, instructions);
        this.param = param;
        this.returnType = returnType;
    }

    public List<Blueprint> getParam() {
        return param;
    }

    public Blueprint getReturnType() {
        return returnType;
    }
}
