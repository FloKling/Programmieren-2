package de.dhbwka.java.exercise.ui.event;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by floriankling on 01.06.17.
 */
public class ShellGame extends JFrame {

    private static final String FILE_NAME = "files/ergebnisse.huetchenspiel.txt";

    private JPanel namePanel;
    private JPanel buttonPanel;
    private JPanel footerPanel;
    private JTextField nameTextField;
    private JTextField resultTextField;

    private int attemps = 0;

    public ShellGame() {
        BorderLayout borderLayout = new BorderLayout(5, 5);
        JPanel panel = new JPanel(borderLayout);

        initNamePanel();
        initButtonPanel();
        initFooterPanel();


        panel.add(namePanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(footerPanel, BorderLayout.SOUTH);
        add(panel);
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shell game");
    }

    private void initFooterPanel() {
        footerPanel = new JPanel(new BorderLayout());
        resultTextField = new JTextField();

        footerPanel.add(resultTextField, BorderLayout.CENTER);
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel(new BorderLayout());
        GridLayout gridLayout = new GridLayout(1, 3);
        JPanel actionButtonPanel = new JPanel(gridLayout);
        JButton shellOne = new JButton("Hütchen 1");
        shellOne.addActionListener(f -> play(1));
        JButton shellTwo = new JButton("Hütchen 2");
        shellTwo.addActionListener(shell -> play(2));
        JButton shellThree = new JButton("Hütchen 3");
        shellThree.addActionListener(shell -> play(3));

        actionButtonPanel.add(shellOne);
        actionButtonPanel.add(shellTwo);
        actionButtonPanel.add(shellThree);

        JPanel statisticPanel = new JPanel(new BorderLayout());
        JButton statistic = new JButton("Statistik");
        JButton exit = new JButton("Beenden");
        exit.addActionListener(f -> System.exit(0));
        statistic.addActionListener(f -> getStatisticFromFile());

        statisticPanel.add(statistic, BorderLayout.WEST);
        statisticPanel.add(exit, BorderLayout.CENTER);
        buttonPanel.add(actionButtonPanel, BorderLayout.CENTER);
        buttonPanel.add(statisticPanel, BorderLayout.SOUTH);
    }

    private void play(int bettedShell) {
        int result = (int) (Math.random() * 4);

        if (bettedShell == result) {
            resultTextField.setText("Gewonnen nach " + attemps + " Versuchen, die Kugel war unter Hütchen " + result);
            writeToFile();
            attemps = 0;
        } else {
            resultTextField.setText("Verloren, die Kugel war unter Hütchen " + result);
            attemps++;
        }
        System.out.println(result);
    }

    private void writeToFile() {
        String playerName = nameTextField.getText();
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(FILE_NAME, true))) {
            printWriter.append(playerName).append("\t").append("|").append(attemps + "").append(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initNamePanel() {
        namePanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("Spielername");
        nameTextField = new JTextField();
        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(nameTextField, BorderLayout.CENTER);
    }

    private void getStatisticFromFile() {
        java.util.List<Integer> results = new ArrayList<>();
        try (FileReader fileReader = new FileReader(new File(FILE_NAME)); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("|")) {
                    Integer attemps = Integer.parseInt(line.substring(line.lastIndexOf("|") + 1, line.length()));
                    results.add(attemps);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!results.isEmpty()) {
            Integer total = 0;
            for (Integer i : results) total += i;

            resultTextField.setText("Erfolg nach durchschnittlich " + total.doubleValue() / results.size() + " Versuchen");
        }
    }


    public static void main(String[] args) {
        new ShellGame();
    }
}
