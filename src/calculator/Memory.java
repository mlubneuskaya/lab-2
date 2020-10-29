package calculator;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    private final Map<Integer, Double> memory = new HashMap<>();
    private Integer currentVariable;

    public Memory(int numberOfFields) {
        for (int i = 0; i < numberOfFields; i++) {
            memory.put(i, 0.0);
        }
    }

    public void setCurrentVariable(Integer variable) {
        currentVariable = variable;
    }

    public Double getMemoryValue() {
        return memory.get(currentVariable);
    }

    public void setMemoryValue(Double value) {
        memory.replace(currentVariable, value);
    }

    public void clearCurrentVariableMemory() {
        memory.replace(currentVariable, 0.0);
    }
}
