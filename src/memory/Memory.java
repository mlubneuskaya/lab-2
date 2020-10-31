package memory;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    private final Map<MemoryVariable, Double> memory = new HashMap<>();
    private MemoryVariable currentVariable;

    public Memory() {
        for (MemoryVariable variable : MemoryVariable.values()) {
            memory.put(variable, 0.0);
        }
    }

    public void setCurrentVariable(MemoryVariable variable) {
        currentVariable = variable;
    }

    public Double getMemoryValue() {
        return memory.get(currentVariable);
    }

    public void setMemoryValue(Double value) {
        memory.put(currentVariable, value);
    }

    public void clearCurrentVariableMemory() {
        memory.put(currentVariable, 0.0);
    }
}
