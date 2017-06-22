package de.dhbwka.java.exercise.collections.library;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddDialog extends JDialog {

    private MainApp mainApp;

    // GUI-Elements
    private JTextField titleField;
    private JTextField authorField;
    private JTextField yearField;
    private JTextField publisherField;



    public AddDialog(Component owner) {
        super((Window) owner, "Hinzufügen", JDialog.DEFAULT_MODALITY_TYPE);
        mainApp = (MainApp) owner;

        this.setLayout(new BorderLayout());
        this.addVisualElements();

        this.setSize(500, 250);
        this.setLocationRelativeTo(owner);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }



    private void saveNewEntry() {
        // Saving Book to ArrayList
        String title = titleField.getText();
        String author = authorField.getText();
        String year = yearField.getText();
        String publisher = publisherField.getText();

        Book bookCache = new Book(title, author, year, publisher);

        mainApp.addNewBook(bookCache);
//        // Saving Book to SaveFile
//        writeSaveFile(bookCache);

        clearInputFields();
    }

    private void clearInputFields() {
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
        publisherField.setText("");
    }

    private void addVisualElements() {
        // Upper Part with Book Facts
        JPanel bookFactsPanel = new JPanel(new GridLayout(4,2));

        JLabel titleLabel = new JLabel("Titel");
        titleField = new JTextField();

        JLabel authorLabel = new JLabel("Autor");
        authorField = new JTextField();

        JLabel yearLabel = new JLabel("Jahr");
        yearField = new JTextField();

        JLabel publisherLabel = new JLabel("Verlag");
        publisherField = new JTextField();


        bookFactsPanel.add(titleLabel);
        bookFactsPanel.add(titleField);

        bookFactsPanel.add(authorLabel);
        bookFactsPanel.add(authorField);

        bookFactsPanel.add(yearLabel);
        bookFactsPanel.add(yearField);

        bookFactsPanel.add(publisherLabel);
        bookFactsPanel.add(publisherField);

        // Save Button
        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.X_AXIS));

        JButton saveButton = new JButton("Speichern");
        saveButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 20));
        saveButton.addActionListener(e -> saveNewEntry());

        savePanel.add(saveButton);

        // Output
        JPanel sortingPanel = new JPanel();

        JLabel sortingLabel = new JLabel("Ausgabe sortieren");
        JButton sortTitleButton = new JButton("Titel");
        JButton sortAuthorButton = new JButton("Autor");
        JButton sortYearButton = new JButton("Jahr");
        JButton sortPublisherButton = new JButton("Verlag");

        sortingPanel.add(sortingLabel);
        sortingPanel.add(sortTitleButton);
        sortingPanel.add(sortAuthorButton);
        sortingPanel.add(sortYearButton);
        sortingPanel.add(sortPublisherButton);


        // Adding the Elements to the main Frame
        this.add(bookFactsPanel, BorderLayout.NORTH);
        this.add(savePanel, BorderLayout.CENTER);
        this.add(sortingPanel, BorderLayout.SOUTH);
    }
}