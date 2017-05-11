package de.dhbwka.java.exercise.ui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by floriankling on 11.05.17.
 */
public class TextFrame extends JFrame {

    private int width;
    private int height;
    private String fileName;

    public TextFrame(int width, int height, String fileName) throws HeadlessException {
        this.width = width;
        this.height = height;
        this.fileName = fileName;

        List<String> linesToDisplay = readFile();

        JTextArea textArea = new JTextArea();

        for (String line : linesToDisplay) {
            textArea.append(line);
            textArea.append(System.lineSeparator());
        }

        add(textArea);

        setTitle(fileName);
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private java.util.List<String> readFile() {
        java.util.List<String> lines = new ArrayList<String>();
        try (FileReader fileReader = new FileReader(new File(fileName)); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String thisLine;
            while ((thisLine = bufferedReader.readLine()) != null) {
                lines.add(thisLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Wrong argument size");
            return;
        }

        int width = 400, height = 400;
        try {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("You didn't enter Numbers");
        }


        String fileName = args[2];
        File file = new File(fileName);
        if (!file.exists())
            throw new IOException("GIBTS NICHT");


        new TextFrame(width, height, fileName);
    }
}
