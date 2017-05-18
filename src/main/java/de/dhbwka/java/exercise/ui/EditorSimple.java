package de.dhbwka.java.exercise.ui;

import javax.swing.*;

/**
 * Created by floriankling on 18.05.17.
 */
public class EditorSimple extends JFrame {

    public EditorSimple() {
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


        fileMenu = new JMenu("Datei") {{
            add(new JMenuItem("Neu"));
            add(new JMenuItem("Öffnen"));
            addSeparator();
            add(new JMenuItem("Schließen"));
            addSeparator();
            add(new JMenuItem("Speichern"));
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
