package de.dhbwka.java.exercise.collections.library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends JFrame {

    private static final String[] COLUMN_NAMES = {"Nr.", "Titel", "Autor", "Jahr", "Verleger"};
    private static final int FOOTER_HEIGHT = 40;
    public static final String FILE_NAME = "myCollection.txt";
    public static final String TEMP_FILE = "tempfile.txt";

    private enum Dialog {
        IS_LISTED, IS_MAYBE_LISTED, IS_EMPTY
    }

    private List<Book> bookList;
    private Book bookCache;
    private int counter = 0;

    private JTable mainTable;
    private DefaultTableModel tableModel;
    private int[] prefSizes;
    private JPanel footerPanel;
    private JLabel elementCount;

    public MainApp() {
        bookList = new ArrayList<>();

        this.setLayout(new BorderLayout());
        this.setSize(800, 600);

        prefSizes = new int[5];
        prefSizes[0] = (getWidth() / 100 * 8);
        prefSizes[1] = (getWidth() / 100 * 42);
        prefSizes[2] = (getWidth() / 100 * 25);
        prefSizes[3] = (int) (getWidth() / 100 * 12.5);
        prefSizes[4] = (int) (getWidth() / 100 * 12.5);

        this.addGUIElements();

        readStoredBooks();

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addGUIElements() {
        elementCount = new JLabel();
        initTable();

        // Adding the MainPanel to the Frame
        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(mainTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Footer Panel
        footerPanel = new JPanel(new BorderLayout());
        footerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, FOOTER_HEIGHT));

        // Footer - Counter Part
        JPanel counterPanel = new JPanel(new GridLayout(1,2));
        JLabel counterLabel = new JLabel("Anzahl: ");
        counterPanel.add(counterLabel);
        counterPanel.add(elementCount);

        // Footer - Action Part
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton deleteButton = new JButton("Löschen");
        deleteButton.addActionListener(e -> deleteData(mainTable.getSelectedRows()));

        JButton addButton = new JButton("Hinzufügen");
        addButton.addActionListener(e -> new AddDialog(MainApp.this));

        actionPanel.add(deleteButton);
        actionPanel.add(addButton);

        // Adding Counter & Action Panel to Footer
        footerPanel.add(counterPanel, BorderLayout.WEST);
        footerPanel.add(actionPanel, BorderLayout.EAST);

        this.add(footerPanel, BorderLayout.SOUTH);
    }

    private void initTable() {
        mainTable = new JTable();
        mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

        tableModel = (DefaultTableModel) mainTable.getModel();
        tableModel.addTableModelListener(e -> elementCount.setText(String.valueOf(tableModel.getRowCount())));

        for (String s : COLUMN_NAMES) {
            tableModel.addColumn(s);
        }

        mainTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        mainTable.getTableHeader().setPreferredSize(new Dimension(Integer.MAX_VALUE, 30));
        mainTable.setRowHeight(25);

        mainTable.getColumnModel().getColumn(0).setPreferredWidth(prefSizes[0]);
        mainTable.getColumnModel().getColumn(1).setPreferredWidth(prefSizes[1]);
        mainTable.getColumnModel().getColumn(2).setPreferredWidth(prefSizes[2]);
        mainTable.getColumnModel().getColumn(3).setPreferredWidth(prefSizes[3]);
        mainTable.getColumnModel().getColumn(4).setPreferredWidth(prefSizes[4]);

        mainTable.setShowGrid(true);
        mainTable.setGridColor(Color.gray);
        mainTable.setAutoCreateRowSorter(true);
    }

    private void addBookToGUI(Book book) {
        counter++;

        String[] toAdd = {String.valueOf(counter), book.getTitle(), book.getAuthor(), book.getYear(), book.getPublisher()};

        tableModel.addRow(toAdd);
    }

    private void deleteData(int[] elements) {
        if (elements.length <= 0) {
            return;
        }

        ArrayList<Book> toDeleteList = new ArrayList<>();

        // Heading
        JDialog deleteDialog = new JDialog(this, "Löschen?", JDialog.DEFAULT_MODALITY_TYPE);
        deleteDialog.setLayout(new BorderLayout(10,10));

        deleteDialog.add(new JLabel("Wollen Sie wirklich folgende Einträge löschen?"), BorderLayout.NORTH);


        // Part with the Elements
        JPanel valuesPanel = new JPanel();
        valuesPanel.setLayout(new GridLayout(elements.length, 1));

        for (int i : elements) {
            valuesPanel.add(new JLabel(bookList.get(i).toString()));
            toDeleteList.add(bookList.get(i));
        }

        deleteDialog.add(valuesPanel, BorderLayout.CENTER);

        // Buttons on the Bottom
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton abortBtn = new JButton("Abbrechen");
        abortBtn.addActionListener(e -> deleteDialog.dispose());
        actionPanel.add(abortBtn);

        JButton deleteBtn = new JButton("Löschen");
        deleteBtn.addActionListener(e -> {
            for (int i = elements.length - 1; i >= 0; i--) {
                tableModel.removeRow(elements[i]);
            }
            bookList.remove(toDeleteList);

            for (Book book : toDeleteList) {
                deleteBookFromStore(book);
            }

            deleteDialog.dispose();
        });
        actionPanel.add(deleteBtn);

        deleteDialog.add(actionPanel, BorderLayout.SOUTH);



        deleteDialog.setSize(500, 250);
        deleteDialog.setLocationRelativeTo(this);
        deleteDialog.setVisible(true);
        deleteDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void addNewBook(Book newBook) {
        bookCache = newBook;

        if(hasEmptyFields(newBook)) {
            return;
        }

        if (isAlreadyListed(newBook)) {
            return;
        }

        bookList.add(newBook);
        addBookToGUI(newBook);
        writeBookToStore(newBook);
    }

    private boolean hasEmptyFields(Book bookToCheck) {
        if (bookToCheck.getTitle().isEmpty() ||
                bookToCheck.getAuthor().isEmpty() ||
                bookToCheck.getYear().isEmpty() ||
                bookToCheck.getPublisher().isEmpty()) {
            showInformationDialog(Dialog.IS_EMPTY, buildEmptyFieldsMsg(bookToCheck));
            return true;
        } else {
            return false;
        }
    }

    private boolean isAlreadyListed(Book bookToCheck) {
        boolean isSameTitle;
        boolean isSameYear;
        boolean isSameAuthor;
        boolean isSamePublisher;

        ArrayList<Book> duplicates = new ArrayList<>();
        ArrayList<Book> maybeDuplicates = new ArrayList<>();


        for (Book b : bookList) {
            isSameTitle = b.getTitle().equals(bookToCheck.getTitle());
            isSameAuthor = b.getAuthor().equals(bookToCheck.getAuthor());
            isSameYear = b.getYear().equals(bookToCheck.getYear());
            isSamePublisher = b.getPublisher().equals(bookToCheck.getPublisher());

            if (isSameTitle && isSameAuthor && isSameYear && isSamePublisher) {
                duplicates.add(b);
            }

            if (isSameTitle && isSameAuthor) {
                maybeDuplicates.add(b);
            }
        }

        if (duplicates.size() <= 0 && maybeDuplicates.size() <= 0) {
            return false;
        } else {
            if (duplicates.size() > 0) {
                showInformationDialog(Dialog.IS_LISTED, buildDuplicateMsg(duplicates.get(0)));
                return true;
            }

            if (maybeDuplicates.size() > 0) {
                showInformationDialog(Dialog.IS_MAYBE_LISTED, buildMaybeDuplicateMsg(maybeDuplicates));
                return true;
            }

            return true;
        }
    }

    private void showInformationDialog(Dialog type, String msg) {
        switch (type) {
            case IS_LISTED:
                JOptionPane.showMessageDialog(this, msg,
                        "Duplikat", JOptionPane.ERROR_MESSAGE);
                break;

            case IS_MAYBE_LISTED:
                int option = JOptionPane.showOptionDialog(this, msg,
                        "Mögliches Duplikat", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null, new String[]{"Speichern", "Abbrechen"},
                        "default");
                if (option == JOptionPane.YES_OPTION) {
                    writeBookToStore(bookCache);
                }
                break;

            case IS_EMPTY:
                JOptionPane.showMessageDialog(this, msg, "Fehlerhafte eingabe!", JOptionPane.INFORMATION_MESSAGE);
                break;

            default:
                JOptionPane.showMessageDialog(this, "Unbekannter Fehler aufgetreten",
                        "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String buildMaybeDuplicateMsg(ArrayList<Book> books) {
        StringBuilder builder = new StringBuilder();

        if (books.size() <= 1) {
            builder.append("Folgendes Buch hat ähnliche Daten: \n \n");
        } else {
            builder.append("Folgende Bücher haben ähnliche Daten: \n \n");
        }

        for (Book b : books) {
            builder.append(b.toString());
        }

        return builder.toString();

    }

    private String buildDuplicateMsg(Book book) {
        StringBuilder builder = new StringBuilder();
        builder.append("Ein Buch mit diesen Eingaben ist bereits gelistet: \n \n");
        builder.append(book.toString());

        return builder.toString();
    }

    private String buildEmptyFieldsMsg(Book book) {
        ArrayList<String> fields = new ArrayList<>();
        if (book.getTitle().isEmpty()) fields.add("Titel");
        if (book.getAuthor().isEmpty()) fields.add("Autor");
        if (book.getYear().isEmpty()) fields.add("Jahr");
        if (book.getPublisher().isEmpty()) fields.add("Verleger");

        StringBuilder builder = new StringBuilder();

        if (fields.size() > 1) {
            builder.append("Folgende Felder dürfen nicht leer sein: \n");
            for (int i = 0; i < fields.size(); i++) {
                builder.append(fields.get(i));

                if (i < fields.size() -1) {
                    builder.append(", ");
                }
            }
        } else {
            builder.append("Folgendes Feld darf nicht leer sein: \n");
            builder.append(fields.get(0));
        }

        return builder.toString();
    }

    private void readStoredBooks() {
        File saveFile = new File(FILE_NAME);
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line = br.readLine();

            while (line != null) {
                String[] element = line.split(";");

                if (element.length >= 4) {
                    Book loadedBook = new Book(element[0], element[1], element[2], element[3]);
                    bookList.add(loadedBook);
                    addBookToGUI(loadedBook);
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStringFromBook(Book book) {
        StringBuilder sb = new StringBuilder();
        sb.append(book.getTitle());
        sb.append(";");
        sb.append(book.getAuthor());
        sb.append(";");
        sb.append(book.getYear());
        sb.append(";");
        sb.append(book.getPublisher());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    private void writeBookToStore(Book toWrite) {
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(FILE_NAME, true))) {

            writer.write(getStringFromBook(toWrite));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deleteBookFromStore(Book toDelete) {
        File saveFile = new File(FILE_NAME);
        if (!saveFile.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
             BufferedWriter bw = new BufferedWriter(new FileWriter(TEMP_FILE))) {

            String line = br.readLine();

            while (line != null) {
                String[] element = line.split(";");

                if (element.length >= 4) {
                    Book readBook = new Book(element[0], element[1], element[2], element[3]);
                    if (!readBook.equals(toDelete)) {
                        bw.write(getStringFromBook(readBook));
                    }
                }

                line = br.readLine();
            }

            bw.close();
            new File(TEMP_FILE).renameTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MainApp();
    }
}