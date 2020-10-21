import gui.Gui;
import gui.calculator.FunctionCalculator;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FunctionCalculator functionCalculator = new FunctionCalculator();
        Gui gui = new Gui(1000, 1000, "functions' value calculating",
                functionCalculator, "resources/function1.png", "resources/function2.png");
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
