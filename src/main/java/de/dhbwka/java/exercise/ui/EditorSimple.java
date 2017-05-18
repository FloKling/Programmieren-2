package de.dhbwka.java.exercise.ui;

import javax.swing.*;

/**
 * Created by floriankling on 18.05.17.
 */
public class EditorSimple extends JFrame {

    public EditorSimple() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();

        menu = new JMenu("Datei");
        menuBar.add(menu);
        menuItem = new JMenuItem("Neu");
        menu.add(menuItem);
        menuItem = new JMenuItem("Öffnen");
        menu.add(menuItem);

        menu.addSeparator();
        menuItem = new JMenuItem("Schließen");
        menu.add(menuItem);
        menu.addSeparator();

        menuItem = new JMenuItem("Speichern");
        menu.add(menuItem);
        menuItem = new JMenuItem("Speichern unter...");
        menu.add(menuItem);
        menuItem = new JMenuItem("Als Webseite speichern");
        menu.add(menuItem);
        menuItem = new JMenuItem("Suchen");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Seite einrichten");
        menu.add(menuItem);
        menuItem = new JMenuItem("Seitenansicht");
        menu.add(menuItem);
        menuItem = new JMenuItem("Drucken");
        menu.add(menuItem);

        menu.addSeparator();


        // SUBMENU
        submenu = new JMenu("Senden an");

        menuItem = new JMenuItem("Email-Empfänger");
        submenu.add(menuItem);
        menuItem = new JMenuItem("Email-Empfänger (zur Überarbeitung)");
        submenu.add(menuItem);
        menuItem = new JMenuItem("Email-Empfänger (als Anlage)");
        submenu.add(menuItem);

        submenu.addSeparator();

        menuItem = new JMenuItem("Verteilerempfänger");
        submenu.add(menuItem);
        menuItem = new JMenuItem("Onlinebesrechungsteilnehmer");
        submenu.add(menuItem);
        menuItem = new JMenuItem("Exchange-Ordner...");
        submenu.add(menuItem);
        menuItem = new JMenuItem("Fax-Empfänger...");
        submenu.add(menuItem);

        submenu.addSeparator();

        menuItem = new JMenuItem("Microsoft Powerpoint");
        submenu.add(menuItem);

        menu.add(submenu);

        menuItem = new JMenuItem("Eigenschaften");
        menu.add(menuItem);

        menu.addSeparator();
        menu.addSeparator();

        menuItem = new JMenuItem("Beenden");
        menu.add(menuItem);



        //Build second menu in the menu bar.
        menu = new JMenu("Bearbeiten");

        menuItem = new JMenuItem("Rückgängig");
        menu.add(menuItem);
        menuItem = new JMenuItem("Wiederholen");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Ausschneiden");
        menu.add(menuItem);
        menuItem = new JMenuItem("Kopieren");
        menu.add(menuItem);
        menuItem = new JMenuItem("Office-Zwischenablage");
        menu.add(menuItem);
        menuItem = new JMenuItem("Einfügen");
        menu.add(menuItem);
        menuItem = new JMenuItem("Inhalte einfügen");
        menu.add(menuItem);
        menuItem = new JMenuItem("Als Hyperlink einfügen");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Löschen");
        menu.add(menuItem);
        menuItem = new JMenuItem("Alles markieren");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Suchen");
        menu.add(menuItem);
        menuItem = new JMenuItem("Ersetzen");
        menu.add(menuItem);
        menuItem = new JMenuItem("Gehe zu");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Verknüpfungen");
        menu.add(menuItem);
        menuItem = new JMenuItem("Objekt");
        menu.add(menuItem);

        menuBar.add(menu);

        setJMenuBar(menuBar);


        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
    }


    public static void main(String[] args) {
        new EditorSimple();
    }
}
