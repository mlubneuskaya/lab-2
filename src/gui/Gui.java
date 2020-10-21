package gui;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    public int width;
    public int height;

    public Gui(int width, int height, String title) {
        super(title);
        this.height = height;
        this.width = width;
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);

        Box radioButtonBox = Box.createHorizontalBox();
        JRadioButton function1 = new JRadioButton("function 1");
        JRadioButton function2 = new JRadioButton("function 2");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(function1);
        radioButtonGroup.add(function2);
        radioButtonBox.add(Box.createHorizontalGlue());
        radioButtonBox.add(function1, Box.createHorizontalStrut(10));
        radioButtonBox.add(function2, Box.createHorizontalStrut(10));
        radioButtonBox.add(Box.createHorizontalGlue());

        ComponentCreator componentCreator = new ComponentCreator();
        Box variableBox = Box.createHorizontalBox();
        JLabel xLabel = componentCreator.createLabel("x");
        JLabel yLabel = componentCreator.createLabel("y");
        JLabel zLabel = componentCreator.createLabel("z");
        JTextField xField = componentCreator.createTextField("0", true);
        JTextField yField = componentCreator.createTextField("0", true);
        JTextField zField = componentCreator.createTextField("0", true);
        variableBox.add(Box.createHorizontalGlue());
        variableBox.add(xLabel);
        variableBox.add(Box.createHorizontalStrut(20));
        variableBox.add(xField);
        variableBox.add(Box.createHorizontalStrut(20));
        variableBox.add(yLabel);
        variableBox.add(Box.createHorizontalStrut(20));
        variableBox.add(yField);
        variableBox.add(Box.createHorizontalStrut(20));
        variableBox.add(zLabel);
        variableBox.add(Box.createHorizontalStrut(20));
        variableBox.add(zField);
        variableBox.add(Box.createHorizontalGlue());

        JLabel resultLabel = componentCreator.createLabel("result");
        JTextField resultField = componentCreator.createTextField("0", false);
        Box resultBox = Box.createHorizontalBox();
        resultBox.add(Box.createHorizontalGlue());
        resultBox.add(resultLabel);
        resultBox.add(Box.createHorizontalStrut(20));
        resultBox.add(resultField);
        resultBox.add(Box.createHorizontalGlue());

        JButton clear = componentCreator.createButton("clear");
        JButton calculate = componentCreator.createButton("calculate");
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(clear);
        buttonBox.add(Box.createHorizontalStrut(100));
        buttonBox.add(calculate);
        buttonBox.add(Box.createHorizontalGlue());

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());
        box.add(radioButtonBox);
        box.add(Box.createVerticalGlue());
        box.add(variableBox);
        box.add(Box.createVerticalGlue());
        box.add(resultBox);
        box.add(Box.createVerticalGlue());
        box.add(buttonBox);
        box.add(Box.createVerticalGlue());
        this.getContentPane().add(box);
    }
}