package de.dhbwka.java.exercise.ui;

import javax.swing.*;

public class Editor extends JFrame {

    private JTextPane textPane;
    private JScrollPane scrollPane;

    /**
     * Konstruktor für den Editor.
     * Hier werden alle UI-Komponenten erzeugt
     */
    public Editor() {
        textPane = new JTextPane();
        scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);

        this.setSize(640, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Main-Methode erzeugt einen neuen Editor zum Testen
     */
    public static void main(String[] args) {
        new Editor();
    }
}