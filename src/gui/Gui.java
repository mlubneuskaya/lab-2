package gui;

import calculator.Function1;
import calculator.Function2;
import calculator.FunctionCalculator;
import memory.Memory;

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
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


import static memory.MemoryVariable.MEM1;
import static memory.MemoryVariable.MEM2;
import static memory.MemoryVariable.MEM3;

public class Gui extends JFrame {
    private JLabel chosenFunction;
    private JTextField resultField;
    private JTextField xField;
    private JTextField yField;
    private JTextField zField;
    private JTextField memoryField;
    private final String[] functionFiles;
    private final Memory memory;
    private final ComponentCreator componentCreator;
    private final FunctionCalculator calculator;

    public Gui(UiConfigParams params, String[] functionFiles) {
        super(params.title);
        memory = new Memory();
        calculator = new FunctionCalculator();
        setSize(params.width, params.height);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - params.width) / 2,
                (kit.getScreenSize().height - params.height) / 2);
        componentCreator = new ComponentCreator();
        this.functionFiles = functionFiles;
        Box variableBox = createVariableBox();
        Box resultBox = createResultBox();
        Box chosenFunctionBox = createChosenFunctionBox();
        Box radioButtonBox = createRadioButtonBox();
        Box buttonBox = createButtonBox();
        Box memoryBox = createMemoryBox();
        Box currentMemoryBox = createCurrentMemoryBox();
        Box box = Box.createVerticalBox();
        List<Box> boxes = Arrays.asList(radioButtonBox, chosenFunctionBox, variableBox,
                resultBox, currentMemoryBox, memoryBox, buttonBox);
        insertBoxes(box, boxes);
        this.getContentPane().add(box);
    }

    private Box createVariableBox() {
        Box variableBox = Box.createHorizontalBox();
        JLabel xLabel = componentCreator.createLabel("x");
        JLabel yLabel = componentCreator.createLabel("y");
        JLabel zLabel = componentCreator.createLabel("z");
        xField = componentCreator.createTextField("0", true);
        yField = componentCreator.createTextField("0", true);
        zField = componentCreator.createTextField("0", true);
        List<JComponent> components = Arrays.asList(xLabel, xField, yLabel, yField, zLabel, zField);
        insertComponents(variableBox, components);
        return variableBox;
    }

    private Box createResultBox() {
        JLabel resultLabel = componentCreator.createLabel("result");
        resultField = componentCreator.createTextField("0", false);
        Box resultBox = Box.createHorizontalBox();
        List<JComponent> components = Arrays.asList(resultLabel, resultField);
        insertComponents(resultBox, components);
        return resultBox;
    }

    private Box createChosenFunctionBox() {
        chosenFunction = new JLabel(new ImageIcon(functionFiles[0]));
        Box chosenFunctionBox = Box.createHorizontalBox();
        List<JComponent> components = Collections.singletonList(chosenFunction);
        insertComponents(chosenFunctionBox, components);
        return chosenFunctionBox;
    }

    private Box createRadioButtonBox() {
        Box radioButtonBox = Box.createHorizontalBox();
        JRadioButton function1 = createFunctionButton("function1", functionFiles[0]);
        function1.setSelected(true);
        calculator.setStrategy(new Function1());
        JRadioButton function2 = createFunctionButton("function2", functionFiles[1]);
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(function1);
        radioButtonGroup.add(function2);
        List<JComponent> components = Arrays.asList(function1, function2);
        insertComponents(radioButtonBox, components);
        return radioButtonBox;
    }

    private Box createButtonBox() {
        Box buttonBox = Box.createHorizontalBox();
        JButton clear = createClearButton(componentCreator);
        JButton calculate = createCalculateButton(componentCreator);
        JButton sum = createSumButton(componentCreator);
        JButton mc = createMcButton(componentCreator);
        List<JComponent> components = Arrays.asList(clear, calculate, sum, mc);
        insertComponents(buttonBox, components);
        return buttonBox;
    }

    private Box createMemoryBox() {
        memoryField = componentCreator.createTextField("0", false);
        Box memoryBox = Box.createHorizontalBox();
        List<JComponent> components = Collections.singletonList(memoryField);
        insertComponents(memoryBox, components);
        return memoryBox;
    }

    private Box createCurrentMemoryBox() {
        JRadioButton xButton = createCurrentMemoryButton("mem1");
        xButton.setSelected(true);
        memory.setCurrentVariable(MEM1);
        JRadioButton yButton = createCurrentMemoryButton("mem2");
        JRadioButton zButton = createCurrentMemoryButton("mem3");
        ButtonGroup currentVariablesGroup = new ButtonGroup();
        currentVariablesGroup.add(xButton);
        currentVariablesGroup.add(yButton);
        currentVariablesGroup.add(zButton);
        Box currentMemoryBox = Box.createHorizontalBox();
        List<JComponent> components = Arrays.asList(xButton, yButton, zButton);
        insertComponents(currentMemoryBox, components);
        return currentMemoryBox;
    }

    private JRadioButton createFunctionButton(String name, String functionFile) {
        JRadioButton function = new JRadioButton(name);
        function.setName(name);
        function.addActionListener(actionEvent -> {
            if (function.getName().equals("function1")) {
                calculator.setStrategy(new Function1());
            } else {
                calculator.setStrategy(new Function2());
            }
            chosenFunction.setIcon(new ImageIcon(functionFile));
            resultField.setText("0");
        });
        return function;
    }

    private JRadioButton createCurrentMemoryButton(String name) {
        JRadioButton currentVariable = new JRadioButton(name);
        currentVariable.addActionListener(actionEvent -> {
            switch (name) {
                case "mem1":
                    memory.setCurrentVariable(MEM1);
                    break;
                case "mem2":
                    memory.setCurrentVariable(MEM2);
                    break;
                case "mem3":
                    memory.setCurrentVariable(MEM3);
                    break;
            }
            memoryField.setText(String.valueOf(memory.getMemoryValue()));
        });
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
        memory.clearCurrentVariableMemory();
        return clear;
    }

    private JButton createCalculateButton(ComponentCreator componentCreator) {
        JButton calculate = componentCreator.createButton("calculate");
        calculate.addActionListener(actionEvent -> {
            try {
                double x = Double.parseDouble(xField.getText());
                double y = Double.parseDouble(yField.getText());
                double z = Double.parseDouble(zField.getText());
                resultField.setText(String.valueOf(calculator.calculate(x, y, z)));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Gui.this, "please enter numbers");
                resultField.setText("error");
            } catch (ArithmeticException ex) {
                JOptionPane.showMessageDialog(Gui.this, "result is not a number\n" +
                        "please enter different values");
                resultField.setText("error");
            }
        });
        return calculate;
    }

    private void insertBoxes(Box container, List<Box> boxes) {
        container.add(Box.createVerticalGlue());
        boxes = new ArrayList<>(boxes);
        JComponent lastComponent = boxes.get(boxes.size() - 1);
        boxes.remove(boxes.size() - 1);
        for (JComponent component : boxes) {
            container.add(component);
            container.add(Box.createVerticalStrut(30));
        }
        container.add(lastComponent);
        container.add(Box.createVerticalGlue());
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
            double value = memory.getMemoryValue() + result;
            memory.setMemoryValue(value);
            memoryField.setText(String.valueOf(value));
        });
        return sum;
    }

    private JButton createMcButton(ComponentCreator componentCreator) {
        JButton mc = componentCreator.createButton("MC");
        mc.addActionListener(actionEvent -> {
            memory.clearCurrentVariableMemory();
            memoryField.setText("0");
        });
        return mc;
    }
}