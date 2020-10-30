package gui;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;

class ComponentCreator {
    public JTextField createTextField(String text, boolean editable) {
        JTextField textField = new JTextField(text);
        Dimension dimension = new Dimension(10 * textField.getPreferredSize().width,
                textField.getPreferredSize().height);
        textField.setMinimumSize(dimension);
        textField.setPreferredSize(dimension);
        textField.setMaximumSize(dimension);
        textField.setEditable(editable);
        return textField;
    }

    public JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        Dimension dimension = new Dimension(10 * label.getPreferredSize().width,
                label.getPreferredSize().height);
        label.setMaximumSize(dimension);
        return label;
    }

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        Dimension dimension = new Dimension(button.getPreferredSize());
        button.setMinimumSize(dimension);
        button.setPreferredSize(dimension);
        button.setMaximumSize(dimension);
        return button;
    }
}