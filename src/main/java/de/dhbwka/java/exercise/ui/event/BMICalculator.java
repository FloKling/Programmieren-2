package de.dhbwka.java.exercise.ui.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by floriankling on 01.06.17.
 */
public class BMICalculator extends JFrame {
    private JTextField weightTextField;
    private JTextField heightTextField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField bmiTextField;
    private JTextField infoTextField;

    public BMICalculator() {
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(2, 2));

        top.add(new JLabel("Gewicht in kg:"));

        weightTextField = new JTextField();
        top.add(weightTextField);
        top.add(new JLabel("Größe in m"));
        heightTextField = new JTextField();
        top.add(heightTextField);

        // Middle part
        JPanel middle = new JPanel();
        middle.setLayout(new GridLayout(1,2));

        maleRadioButton = new JRadioButton("männlich");

        femaleRadioButton = new JRadioButton("weiblich");

        ButtonGroup gender = new ButtonGroup();
        gender.add(maleRadioButton);
        gender.add(femaleRadioButton);

        middle.add(maleRadioButton);
        middle.add(femaleRadioButton);

        JButton calc = new JButton("Berechnen");
        calc.addActionListener(this::calculateBMI);

        // Bottom part
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(1,2));

        bottom.add(new JLabel("BMI:"));

        bmiTextField = new JTextField();
        bottom.add(bmiTextField);

        infoTextField = new JTextField();

        this.setLayout(new GridLayout(5, 1));
        this.add(top);
        this.add(middle);
        this.add(calc);
        this.add(bottom);
        this.add(infoTextField);

        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void calculateBMI(ActionEvent actionEvent) {
        double bmi = 0.0;
        try {
            bmi = Double.parseDouble(weightTextField.getText()) / Math.pow(Double.parseDouble(heightTextField.getText()), 2);
        } catch (NumberFormatException e) {
            weightTextField.setText("");
            heightTextField.setText("");
        }

        bmiTextField.setText(String.valueOf(bmi));

        boolean isMale = maleRadioButton.isSelected();
        boolean isFemale = femaleRadioButton.isSelected();
        if (isMale || isFemale) {
            if (isMale) {
                if (bmi < 20) {
                    infoTextField.setText("Untergewicht");
                } else if (bmi < 25) {
                    infoTextField.setText("Normalgewicht");
                } else if (bmi < 30) {
                    infoTextField.setText("Übergewicht");
                } else if (bmi < 40) {
                    infoTextField.setText("Adipositas");
                } else {
                    infoTextField.setText("massive Adipositas");
                }
            } else {
                if (bmi < 19) {
                    infoTextField.setText("Untergewicht");
                } else if (bmi < 24) {
                    infoTextField.setText("Normalgewicht");
                } else if (bmi < 30) {
                    infoTextField.setText("Übergewicht");
                } else if (bmi < 40) {
                    infoTextField.setText("Adipositas");
                } else {
                    infoTextField.setText("massive Adipositas");
                }
            }
        }
    }

    public static void main(String[] args) {
        new BMICalculator();
    }
}
