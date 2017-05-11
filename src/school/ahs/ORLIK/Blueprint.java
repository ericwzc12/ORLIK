package school.ahs.ORLIK;

import java.util.HashMap;
import java.util.Map;

public class Blueprint {

    private Map<String, Blueprint> variables;

    public Blueprint() {
        this.variables = new HashMap<>();
    }

    public void set(String variableName, Blueprint variableType) {
        variables.put(variableName, variableType);
    }

    public Blueprint get(String variableName){
        return variables.get(variableName);
    }



}
