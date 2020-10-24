import gui.Gui;
import gui.UiConfigParams;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String[] functionFiles = new String[]{"resources/function1.png", "resources/function2.png"};
        UiConfigParams params = new UiConfigParams(600, 500, "functions' value calculating");
        Gui gui = new Gui(params, functionFiles);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
