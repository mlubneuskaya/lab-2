import gui.Gui;
import gui.calculator.FunctionCalculator;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FunctionCalculator functionCalculator = new FunctionCalculator();
        Gui gui = new Gui(1000, 1000, "functions' value calculating", functionCalculator);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
