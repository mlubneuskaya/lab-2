import gui.Gui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Gui gui = new Gui(1000, 1000, "functions' value calculating");
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
