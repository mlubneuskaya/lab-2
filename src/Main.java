import calculator.Function1;
import calculator.Function2;
import gui.FunctionConfig;
import gui.Gui;
import gui.UiConfigParams;

import java.util.ArrayList;
import java.util.List;
import javax.swing.WindowConstants;

public class Main {
    public static void main(String[] args) {
        FunctionConfig config1 = new FunctionConfig("function1", "resources/function1.png",
                new Function1());
        FunctionConfig config2 = new FunctionConfig("function2", "resources/function2.png",
                new Function2());
        List<FunctionConfig> configs = new ArrayList<>();
        configs.add(config1);
        configs.add(config2);
        UiConfigParams params = new UiConfigParams(600, 500, "functions' value calculating");
        Gui gui = new Gui(params, configs);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
