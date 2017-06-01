package de.dhbwka.java.exercise.ui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URISyntaxException;

/**
 * Created by floriankling on 18.05.17.
 */
public class EditorSimple extends JFrame {

    private JTextPane textPane;
    private JScrollPane scrollPane;

    public EditorSimple() {
        textPane = new JTextPane();
        scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);

        this.setSize(640, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);


        JMenuBar menuBar;
        JMenu fileMenu, editMenu, sendToSubMenu;
        menuBar = new JMenuBar();

        sendToSubMenu = new JMenu("Senden an") {{
            add(new JMenuItem("Email-Empfänger"));
            add(new JMenuItem("Email-Empfänger (zur Überarbeitung)"));
            add(new JMenuItem("Email-Empfänger (als Anlage)"));
            addSeparator();
            add(new JMenuItem("Verteilerempfänger"));
            add(new JMenuItem("Onlinebesrechungsteilnehmer"));
            add(new JMenuItem("Exchange-Ordner..."));
            add(new JMenuItem("Fax-Empfänger..."));
            addSeparator();
            add(new JMenuItem("Microsoft Powerpoint"));
        }};

        JMenuItem saveItem = new JMenuItem("Speichern") {{
            setEnabled(false);

            addActionListener((ActionEvent f) -> {
                String text = textPane.getText();
                try (PrintStream printStream = new PrintStream(new File(textPane.getPage().toURI()))) {
                    printStream.print(text);
                } catch (FileNotFoundException | URISyntaxException e) {
                    e.printStackTrace();
                }

            });
        }};


        fileMenu = new JMenu("Datei") {{
            add(new JMenuItem("Neu") {{
                addActionListener(f -> {
                    textPane.setText("");
                });
            }});
            add(new JMenuItem("Öffnen") {{
                addActionListener(f -> {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(new FileFilter() {

                        @Override
                        public boolean accept(File f) {
                            return f.isDirectory() ||
                                    f.getName().toLowerCase().endsWith(".txt");
                        }

                        @Override
                        public String getDescription() {
                            return "Text Files";
                        }

                    });

                    int state = fc.showOpenDialog(scrollPane);

                    if (state == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        try {
                            textPane.setPage(file.toURI().toURL());
                            saveItem.setEnabled(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("No selection");
                        System.exit(0);
                    }

                });
            }});
            addSeparator();
            add(new JMenuItem("Schließen") {{
                addActionListener(f -> {
                    int result = JOptionPane.showConfirmDialog(null, "Wollen sie das Programm wirklich beenden?");
                    if (result == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }
                });
            }});
            addSeparator();
            add(saveItem);
            add(new JMenuItem("Speichern unter..."));
            add(new JMenuItem("Als Webseite speichern"));
            add(new JMenuItem("Suchen"));
            addSeparator();
            add(new JMenuItem("Versionen"));
            addSeparator();
            add(new JMenuItem("Webseitenvorschau"));
            addSeparator();
            add(new JMenuItem("Seite einrichten"));
            add(new JMenuItem("Seitenansicht"));
            add(new JMenuItem("Drucken"));
            add(sendToSubMenu);
            add(new JMenuItem("Eigenschaften"));
            add(new JMenuItem("Beenden"));
        }};
        menuBar.add(fileMenu);

        editMenu = new JMenu("Bearbeiten") {{
            add(new JMenuItem("Rückgängig"));
            add(new JMenuItem("Wiederholen"));
            addSeparator();
            add(new JMenuItem("Ausschneiden"));
            add(new JMenuItem("Kopieren"));
            add(new JMenuItem("Office-Zwischenablage"));
            add(new JMenuItem("Einfügen"));
            add(new JMenuItem("Inhalte einfügen"));
            add(new JMenuItem("Als Hyperlink einfügen"));
            addSeparator();
            add(new JMenuItem("Löschen"));
            add(new JMenuItem("Alles markieren"));
            addSeparator();
            add(new JMenuItem("Suchen"));
            add(new JMenuItem("Ersetzen"));
            add(new JMenuItem("Gehe zu"));
            addSeparator();
            add(new JMenuItem("Verknüpfungen"));
            add(new JMenuItem("Objekt"));
        }};
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
    }


    public static void main(String[] args) {
        new EditorSimple();
    }
}
