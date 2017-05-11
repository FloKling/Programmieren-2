package de.dhbwka.java.exercise.ui;

import javax.swing.*;

/**
 * Created by floriankling on 11.05.17.
 */
public class ComponentFrame extends JFrame {

    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton button;
    private JToggleButton toggleButton;
    private JCheckBox checkBox;
    private JComboBox comboBox;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JRadioButton radio3;


    public ComponentFrame() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 100);
        setTitle("");

        panel = new JPanel();
        panel.setSize(600, 100);
        add(panel);

        label = new JLabel("JLabel");
        textField = new JTextField("JTextField");
        textField.setSize(60, 20);
        passwordField = new JPasswordField("JPasswordField");
        passwordField.setSize(60, 20);
        button = new JButton("JButton");
        toggleButton = new JToggleButton("JToggleButton");
        checkBox = new JCheckBox("JCheckBox");
        comboBox = new JComboBox();

        comboBox.addItem("1. Item");
        comboBox.addItem("2. Item");
        comboBox.addItem("3. Item");

        panel.add(label);
        panel.add(textField);
        panel.add(passwordField);
        panel.add(button);
        panel.add(toggleButton);
        panel.add(checkBox);
        panel.add(comboBox);

        JPanel radioPanel = new JPanel();


        ButtonGroup buttonGroup = new ButtonGroup();

        radio1 = new JRadioButton("Radio-Button-1");
        radio2 = new JRadioButton("Radio-Button-2");
        radio3 = new JRadioButton("Radio-Button-3");

        buttonGroup.add(radio1);
        buttonGroup.add(radio2);
        buttonGroup.add(radio3);

        radioPanel.add(radio1);
        radioPanel.add(radio2);
        radioPanel.add(radio3);

        panel.add(radioPanel);


        setVisible(true);
    }

    public static void main(String[] args) {
        new ComponentFrame();
    }
}