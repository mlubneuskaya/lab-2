package gui;

import calculator.FunctionCalculator;
import calculator.Memory;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
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
    private final JTextField xMemory;
    private final JTextField yMemory;
    private final JTextField zMemory;
    private final Memory memory;

    public Gui(UiConfigParams params, String[] functionFiles) {
        super(params.title);
        functionCalculator = new FunctionCalculator();
        memory = new Memory();
        setSize(params.width, params.height);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - params.width) / 2,
                (kit.getScreenSize().height - params.height) / 2);
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

        chosenFunction = new JLabel(new ImageIcon(functionFiles[0]));
        Box chosenFunctionBox = Box.createHorizontalBox();
        components = Collections.singletonList(chosenFunction);
        insertComponents(chosenFunctionBox, components);

        Box radioButtonBox = Box.createHorizontalBox();
        JRadioButton function1 = createFunctionButton("function1", functionFiles[0], 1);
        function1.setSelected(true);
        JRadioButton function2 = createFunctionButton("function2", functionFiles[1], 2);
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(function1);
        radioButtonGroup.add(function2);
        components = Arrays.asList(function1, function2);
        insertComponents(radioButtonBox, components);

        JButton clear = createClearButton(componentCreator);
        JButton calculate = createCalculateButton(componentCreator);
        JButton sum = createSumButton(componentCreator);
        JButton mc = createMcButton(componentCreator);
        Box buttonBox = Box.createHorizontalBox();
        components = Arrays.asList(clear, calculate, sum, mc);
        insertComponents(buttonBox, components);

        xMemory = componentCreator.createTextField("0", false);
        yMemory = componentCreator.createTextField("0", false);
        zMemory = componentCreator.createTextField("0", false);
        Box memoryBox = Box.createHorizontalBox();
        components = Arrays.asList(xLabel, xMemory, yLabel, yMemory, zLabel, zMemory);
        insertComponents(memoryBox, components);

        JRadioButton xButton = createCurrentVariableButton("x");
        xButton.setSelected(true);
        memory.setCurrentVariable("x");
        JRadioButton yButton = createCurrentVariableButton("y");
        JRadioButton zButton = createCurrentVariableButton("z");
        ButtonGroup currentVariablesGroup = new ButtonGroup();
        currentVariablesGroup.add(xButton);
        currentVariablesGroup.add(yButton);
        currentVariablesGroup.add(zButton);
        Box currentVariablesBox = Box.createHorizontalBox();
        components = Arrays.asList(xButton, yButton, zButton);
        insertComponents(currentVariablesBox, components);

        Box box = Box.createVerticalBox();
        List<Box> boxes = Arrays.asList(radioButtonBox, chosenFunctionBox, variableBox, currentVariablesBox, memoryBox,
                resultBox, buttonBox);
        insertBoxes(box, boxes);
        this.getContentPane().add(box);
    }

    private JRadioButton createFunctionButton(String name, String functionFile, int formulaId) {
        JRadioButton function = new JRadioButton(name);
        function.addActionListener(actionEvent -> {
            functionCalculator.setFormulaId(formulaId);
            chosenFunction.setIcon(new ImageIcon(functionFile));
            resultField.setText("0");
            memory.clearAll();
            xMemory.setText("0");
            yMemory.setText("0");
            zMemory.setText("0");
        });
        return function;
    }

    private JRadioButton createCurrentVariableButton(String name) {
        JRadioButton currentVariable = new JRadioButton(name);
        currentVariable.addActionListener(actionEvent -> memory.setCurrentVariable(name));
        return currentVariable;
    }

    private JButton createClearButton(ComponentCreator componentCreator) {
        JButton clear = componentCreator.createButton("clear");
        clear.addActionListener(actionEvent -> {
            xField.setText("0");
            yField.setText("0");
            zField.setText("0");
            resultField.setText("0");
        });
        memory.clearAll();
        return clear;
    }

    private JButton createCalculateButton(ComponentCreator componentCreator) {
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

    private void insertBoxes(Box container, List<Box> boxes) {
        container.add(Box.createHorizontalGlue());
        for (Box box : boxes) {
            container.add(box);
            container.add(Box.createVerticalGlue());
        }
    }

    private void insertComponents(Box container, List<JComponent> components) {
        container.add(Box.createHorizontalGlue());
        components = new ArrayList<>(components);
        JComponent lastComponent = components.get(components.size() - 1);
        components.remove(components.size() - 1);
        for (JComponent component : components) {
            container.add(component);
            container.add(Box.createHorizontalStrut(20));
        }
        container.add(lastComponent);
        container.add(Box.createHorizontalGlue());
    }

    private JButton createSumButton(ComponentCreator componentCreator) {
        JButton sum = componentCreator.createButton("M+");
        sum.addActionListener(actionEvent -> {
            double result = Double.parseDouble(resultField.getText());
            String variable = memory.getCurrentVariable();
            double value = 0.0;
            switch (variable) {
                case "x":
                    value = Double.parseDouble(xField.getText());
                    xMemory.setText(String.valueOf(result + value));
                    break;
                case "y":
                    value = Double.parseDouble(yField.getText());
                    yMemory.setText(String.valueOf(result + value));
                    break;
                case "z":
                    value = Double.parseDouble(zField.getText());
                    zMemory.setText(String.valueOf(result + value));
                    break;
            }
            resultField.setText(String.valueOf(result + value));
            memory.setMemoryValue(value);
        });
        return sum;
    }

    private JButton createMcButton(ComponentCreator componentCreator) {
        JButton mc = componentCreator.createButton("MC");
        mc.addActionListener(actionEvent -> {
            memory.clearCurrentVariableMemory();
            String variable = memory.getCurrentVariable();
            switch (variable) {
                case "x":
                    xMemory.setText("0");
                    break;
                case "y":
                    yMemory.setText("0");
                    break;
                case "z":
                    zMemory.setText("0");
                    break;
            }
        });
        return mc;
    }
}