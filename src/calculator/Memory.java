package calculator;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    private final Map<Integer, Double> memory = new HashMap<>();
    private Integer currentVariableId;

    public Memory() {
        memory.put(1, 0.0);
        memory.put(2, 0.0);
        memory.put(3, 0.0);
    }

    public void setCurrentVariableId(int variableId) {
        currentVariableId = variableId;
    }

    public Integer getCurrentVariableId() {
        return currentVariableId;
    }

    public void setMemoryValue(Double value) {
        memory.replace(currentVariableId, value);
    }

    public void clearAll() {
        memory.forEach((key, value) -> memory.replace(key, 0.0));
    }
}
