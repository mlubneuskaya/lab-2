package gui;

import calculator.FunctionCalculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Gui extends JFrame {
    private final FunctionCalculator functionCalculator;
    private final JLabel chosenFunction;
    private final JTextField resultField;
    private final JTextField xField;
    private final JTextField yField;
    private final JTextField zField;

    public Gui(int width, int height, String title, FunctionCalculator functionCalculator,
               String functionFile1, String functionFile2) {
        super(title);
        this.functionCalculator = functionCalculator;
        setSize(width, height);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);
        ComponentCreator componentCreator = new ComponentCreator();
        Box variableBox = Box.createHorizontalBox();
        JLabel xLabel = componentCreator.createLabel("x");
        JLabel yLabel = componentCreator.createLabel("y");
        JLabel zLabel = componentCreator.createLabel("z");
        xField = componentCreator.createTextField("0", true);
        yField = componentCreator.createTextField("0", true);
        zField = componentCreator.createTextField("0", true);
        List<JComponent> components = Arrays.asList(xLabel, xField, yLabel, yField, zLabel, zField);
        insertComponents(variableBox, components);
        JLabel resultLabel = componentCreator.createLabel("result");
        resultField = componentCreator.createTextField("0", false);
        Box resultBox = Box.createHorizontalBox();
        components = Arrays.asList(resultLabel, resultField);
        insertComponents(resultBox, components);
        chosenFunction = new JLabel(new ImageIcon(functionFile1));
        Box chosenFunctionBox = Box.createHorizontalBox();
        components = Collections.singletonList(chosenFunction);
        insertComponents(chosenFunctionBox, components);
        Box radioButtonBox = Box.createHorizontalBox();
        JRadioButton function1 = createFunctionButton("function1", functionFile1, 1);
        function1.setSelected(true);
        JRadioButton function2 = createFunctionButton("function2", functionFile2, 2);
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(function1);
        radioButtonGroup.add(function2);
        components = Arrays.asList(function1, function2);
        insertComponents(radioButtonBox,components);
        JButton clear = createClearButton(componentCreator);
        JButton calculate = createCalculateButton(componentCreator);
        Box buttonBox = Box.createHorizontalBox();
        components = Arrays.asList(clear, calculate);
        insertComponents(buttonBox, components);
        Box box = Box.createVerticalBox();
        List<Box> boxes = Arrays.asList(radioButtonBox, chosenFunctionBox, variableBox, resultBox, buttonBox);
        insertBoxes(box, boxes);
        this.getContentPane().add(box);
    }
    private JRadioButton createFunctionButton(String name, String functionFile, int formulaId){
        JRadioButton function = new JRadioButton(name);
        function.addActionListener(actionEvent -> {
            functionCalculator.setFormulaId(formulaId);
            chosenFunction.setIcon(new ImageIcon(functionFile));
            resultField.setText("0");
        });
        return function;
    }
    private JButton createClearButton(ComponentCreator componentCreator){
        JButton clear = componentCreator.createButton("clear");
        clear.addActionListener(actionEvent -> {
            xField.setText("0");
            yField.setText("0");
            zField.setText("0");
            resultField.setText("0");
        });
        return clear;
    }
    private JButton createCalculateButton(ComponentCreator componentCreator){
        JButton calculate = componentCreator.createButton("calculate");
        calculate.addActionListener(actionEvent -> {
            try {
                double x = Double.parseDouble(xField.getText());
                double y = Double.parseDouble(yField.getText());
                double z = Double.parseDouble(zField.getText());
                resultField.setText(String.valueOf(functionCalculator.calculate(x, y, z)));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Gui.this,
                        "");
            }
        });
        return calculate;
    }
    private void insertBoxes(Box container, List<Box> boxes){
        container.add(Box.createHorizontalGlue());
        for(Box box: boxes){
            container.add(box);
            container.add(Box.createVerticalGlue());
        }
    }
    private void insertComponents(Box container, List<JComponent> components){
        container.add(Box.createHorizontalGlue());
        components = new ArrayList<>(components);
        JComponent lastComponent = components.get(components.size()-1);
        components.remove(components.size()-1);
        for(JComponent component: components){
            container.add(component);
            container.add(Box.createHorizontalStrut(20));
        }
        container.add(lastComponent);
        container.add(Box.createHorizontalGlue());
    }
}