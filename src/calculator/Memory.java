package calculator;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    private final Map<String, Double> memory = new HashMap<>();
    private String currentVariable;

    public Memory() {
        memory.put("x", 0.0);
        memory.put("y", 0.0);
        memory.put("z", 0.0);
    }

    public void setCurrentVariable(String variable) {
        currentVariable = variable;
    }

    public String getCurrentVariable() {
        return currentVariable;
    }

    public void setMemoryValue(Double value) {
        memory.replace(currentVariable, value);
    }

    public void clearAll() {
        memory.forEach((key, value) -> memory.replace(key, 0.0));
    }

    public void clearCurrentVariableMemory(){
        memory.replace(currentVariable, 0.0);
    }
}
