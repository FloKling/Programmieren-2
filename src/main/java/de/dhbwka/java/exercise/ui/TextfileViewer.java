package de.dhbwka.java.exercise.ui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by floriankling on 18.05.17.
 */
public class TextfileViewer {

    private final int ROWS_TEXTAREA = 5;
    private final int ROWS = 10;

    private JFrame jFrame;
    private JFileChooser fc;
    private String filePath;
    private JPanel panel;

    public TextfileViewer() {
        jFrame = new JFrame();
        panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        jFrame.add(panel);

        panel.setLayout(boxLayout);

        jFrame.setSize(300, 300);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initFileChooser();
        initFileChooserListener();

        jFrame.setVisible(true);
    }

    private void initFileChooser() {
        fc = new JFileChooser();
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
    }

    private void initFileChooserListener() {
        int state = fc.showOpenDialog(panel);

        if (state == JFileChooser.APPROVE_OPTION) {
            filePath = fc.getSelectedFile().getAbsolutePath();
            if (!filePath.isEmpty()) {
                new TextFrame(400, 400, filePath);
                addLabelsToPane(readFile());
//                addLinesToTextArea(readFile());
            }
            System.out.println(fc.getSelectedFile().getAbsolutePath());
        } else {
            System.out.println("No selection");
            System.exit(-1);
        }
    }

    private void addLinesToTextArea(List<String> lines) {
        JTextArea textArea = new JTextArea();
        if (lines.size() > ROWS_TEXTAREA) {
            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane);
        } else {
            panel.add(textArea);
        }

        for (String line : lines) {
            textArea.append(line);
            textArea.append(System.lineSeparator());
        }

    }

    private void addLabelsToPane(List<String> strings) {
        int counter = 0;
        for (String line : strings) {
            if (counter < ROWS) {
                JLabel label = new JLabel(line);
                panel.add(label);
            }
            counter++;
        }
    }

    private List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try (FileReader fileReader = new FileReader(new File(filePath)); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String thisLine;
            while (null != (thisLine = bufferedReader.readLine())) {
                lines.add(thisLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }


    public static void main(String[] args) {
        new TextfileViewer();
    }
}
