package de.dhbwka.java.exercise.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by floriankling on 18.05.17.
 */
public class CurrencyCalculator extends JFrame {

    private JTextField textField;
    private JButton eurToUsdButton;
    private JButton usdToEurButton;
    private JButton cancelButton;
    private JPanel panel;
    private BorderLayout borderLayout;

    public CurrencyCalculator() {
        borderLayout = new BorderLayout();
        panel = new JPanel(borderLayout);

        textField = new JTextField("Bitte hier den Betrag zum umrechnen angeben");

        panel.add(textField, BorderLayout.NORTH);

        eurToUsdButton = new JButton("EUR to USD");
        usdToEurButton = new JButton("USD to EUR");
        cancelButton = new JButton("Abbrechen");

        addButtonListeners();

        panel.add(eurToUsdButton, BorderLayout.WEST);
        panel.add(usdToEurButton, BorderLayout.CENTER);
        panel.add(cancelButton, BorderLayout.EAST);

        add(panel);
        setSize(400, 100);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Currency Calculator");
    }

    private void addButtonListeners() {
        cancelButton.addActionListener(f -> System.exit(-1));
        eurToUsdButton.addActionListener(f -> {});
        usdToEurButton.addActionListener(f -> {});
    }

    public static void main(String[] args) {
        new CurrencyCalculator();
    }
}
