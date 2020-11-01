package gui;

import calculator.Function;

public class FunctionConfig {
    public String name;
    public String filePath;
    public Function function;

    public FunctionConfig(String name, String filePath, Function function) {
        this.name = name;
        this.filePath = filePath;
        this.function = function;
    }
}
