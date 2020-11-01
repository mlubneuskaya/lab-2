import gui.FunctionConfig;
import gui.Gui;
import gui.UiConfigParams;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FunctionConfig config1 = new FunctionConfig("function1", "resources/function1.png");
        FunctionConfig config2 = new FunctionConfig("function2", "resources/function2.png");
        List<FunctionConfig> configs = new ArrayList<>();
        configs.add(config1);
        configs.add(config2);
        UiConfigParams params = new UiConfigParams(600, 500, "functions' value calculating");
        Gui gui = new Gui(params, configs);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
