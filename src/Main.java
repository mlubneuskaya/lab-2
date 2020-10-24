import gui.Gui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Gui gui = new Gui(600, 500, "functions' value calculating",
                "resources/function1.png", "resources/function2.png");
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
