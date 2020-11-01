package gui;

import calculator.Function1;
import calculator.Function2;
import calculator.FunctionCalculator;
import java.awt.*;
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
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import memory.MemoryVariable;


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
    private final Memory memory;
    private final ComponentCreator componentCreator;
    private final FunctionCalculator calculator;

    public Gui(UiConfigParams params, List<FunctionConfig> configs) {
        super(params.title);
        memory = new Memory();
        calculator = new FunctionCalculator();
        setSize(params.width, params.height);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - params.width) / 2,
                (kit.getScreenSize().height - params.height) / 2);
        componentCreator = new ComponentCreator();
        Box variableBox = createVariableBox();
        Box resultBox = createResultBox();
        Box chosenFunctionBox = createChosenFunctionBox(configs);
        Box radioButtonBox = createRadioButtonBox(configs);
        Box buttonBox = createButtonBox();
        Box memoryTextBox = createMemoryTextBox();
        Box currentMemoryBox = createChooseMemoryBox();
        Box box = Box.createVerticalBox();
        List<Box> boxes = Arrays.asList(radioButtonBox, chosenFunctionBox, variableBox,
                resultBox, buttonBox, currentMemoryBox, memoryTextBox);
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

    private Box createChosenFunctionBox(List<FunctionConfig> configs) {
        chosenFunction = new JLabel(new ImageIcon(configs.get(0).filePath));
        Box chosenFunctionBox = Box.createHorizontalBox();
        List<JComponent> components = Collections.singletonList(chosenFunction);
        insertComponents(chosenFunctionBox, components);
        return chosenFunctionBox;
    }

    private Box createRadioButtonBox(List<FunctionConfig> configs) {
        Box radioButtonBox = Box.createHorizontalBox();
        List<JRadioButton> buttons = configs.stream()
                .map(config-> createFunctionButton(config.name, config.filePath))
                .collect(Collectors.toList());
        buttons.get(0).setSelected(true);
        calculator.setFunction(new Function1());
        ButtonGroup radioButtonGroup = new ButtonGroup();
        buttons.forEach(radioButtonGroup::add);
        insertComponents(radioButtonBox, new ArrayList<>(buttons));
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

    private Box createMemoryTextBox() {
        memoryField = componentCreator.createTextField("0.0", false);
        Box memoryBox = Box.createHorizontalBox();
        List<JComponent> components = Collections.singletonList(memoryField);
        insertComponents(memoryBox, components);
        return memoryBox;
    }

    private Box createChooseMemoryBox() {
        JRadioButton xButton = createCurrentMemoryButton(MEM1);
        xButton.setSelected(true);
        memory.setCurrentVariable(MEM1);
        JRadioButton yButton = createCurrentMemoryButton(MEM2);
        JRadioButton zButton = createCurrentMemoryButton(MEM3);
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
                calculator.setFunction(new Function1());
            } else {
                calculator.setFunction(new Function2());
            }
            chosenFunction.setIcon(new ImageIcon(functionFile));
            resultField.setText("0");
        });
        return function;
    }

    private JRadioButton createCurrentMemoryButton(MemoryVariable variable) {
        JRadioButton currentVariable = new JRadioButton(variable.getName());
        currentVariable.addActionListener(actionEvent -> {
            memory.setCurrentVariable(variable);
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
            container.add(Box.createVerticalStrut(20));
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