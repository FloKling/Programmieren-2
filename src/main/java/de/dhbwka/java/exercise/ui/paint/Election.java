package de.dhbwka.java.exercise.ui.paint;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Election extends JFrame {

    private static final int HEADER_HEIGHT = 50;
    private static final Font HEADER_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
    private static final int FOOTER_HEIGHT = 50;
    private static final Font FOOTER_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 16);

    public Election(ArrayList<Partei> parteien) {
        this.setSize(640, 480);


        this.add(new ElectionLayout(parteien));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<Partei> parteien = new ArrayList<>();
        parteien.add(new Partei("Union", 41.5, Color.black));
        parteien.add(new Partei("SPD", 25.7, Color.red));
        parteien.add(new Partei("FDP", 4.8, Color.yellow));
        parteien.add(new Partei("Linke", 8.6, Color.MAGENTA));
        parteien.add(new Partei("Grüne", 8.4, Color.green));
        parteien.add(new Partei("AfD", 4.7, Color.BLUE));
        parteien.add(new Partei("Piraten", 2.2, Color.orange));
        parteien.add(new Partei("Andere", 4.1, Color.gray));

        new Election(parteien);
    }

    static class Partei {
        public String name;
        public double percent;
        public Color color;

        public Partei(String name, double percent, Color color) {
            this.name = name;
            this.percent = percent;
            this.color = color;
        }
    }


    class ElectionLayout extends JComponent {
        private ArrayList<Partei> parteien;

        public ElectionLayout(ArrayList<Partei> parteien) {
            this.parteien = parteien;
        }

        @Override
        public void paint(Graphics g) {
            int width = getWidth();
            int height = getHeight();
            int padding = 5;

            int spacePer = ((width - (9 * padding)) / parteien.size());

            int drawingStart = HEADER_HEIGHT + 10;
            int drawingEnd = height - (FOOTER_HEIGHT * 2) + 10;
            int drawingSpace = drawingEnd - drawingStart;

            // Background
            Graphics2D g2d = (Graphics2D) g;

            Color startColor = new Color(0x0099dd);
            Color endColor = new Color(0x4444ff);

            GradientPaint gradient = new GradientPaint(0, 0, startColor, width, height, endColor);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);

            // Header
            g.setColor(new Color(0x1155ee));
            g.fillRect(0, 0, width, HEADER_HEIGHT);

            g.setColor(new Color(0x004788));
            g.fillRect(0, 0, width, HEADER_HEIGHT - 5);

            // Header-Text
            g.setColor(Color.WHITE);
            g.setFont(HEADER_FONT);
            int fontHeight = g.getFontMetrics().getHeight();
            g.drawString("Bundestagswahlen 2017", 15, fontHeight);
            g.drawString("in %", width - (g.getFontMetrics().stringWidth("in %") + 15), fontHeight);

            // Footer
            g.setColor(Color.WHITE);
            g.fillRect(0, height - (FOOTER_HEIGHT * 2), width, (FOOTER_HEIGHT * 2));

            g.setColor(endColor);
            g.fillRect(0, height - FOOTER_HEIGHT, width, FOOTER_HEIGHT);

            g.setColor(startColor);
            g.fillRect(0, height - FOOTER_HEIGHT + padding, width, FOOTER_HEIGHT - padding * 2);

            int currentPosition = (2 * padding);
            Partei maxPartei = parteien.stream()
                    .max((o1, o2) -> o1.percent < o2.percent ? -1 : o1.percent > o2.percent ? 1 : 0)
                    .get();

            g.setColor(Color.WHITE);
            int x = 10;
            while (x < maxPartei.percent) {
                int lineHeight = drawingEnd - (int) (drawingSpace / maxPartei.percent * x);
                g.drawLine(0, lineHeight, width, lineHeight);
                x += 10;
            }

            // Parteien Balken +
            for (Partei p : parteien) {
                double parteiHeightP = p.percent / maxPartei.percent;
                int parteiHeight = (int) (drawingSpace * parteiHeightP);
                g.setColor(p.color);
                g.fillRect(currentPosition, drawingStart + (drawingSpace - parteiHeight), (spacePer - 2 * padding), parteiHeight);

                g.setFont(FOOTER_FONT);
                g.setColor(Color.black);
                int stringWidth = g.getFontMetrics().stringWidth(p.name);
                stringWidth = ((spacePer - 2 * padding) - stringWidth) / 2;
                g.drawString(p.name, currentPosition + stringWidth, height - FOOTER_HEIGHT - g.getFontMetrics().getHeight());

                stringWidth = g.getFontMetrics().stringWidth(String.valueOf(p.percent));
                stringWidth = ((spacePer - 2 * padding) - stringWidth) / 2;
                g.drawString(String.valueOf(p.percent), currentPosition + stringWidth, height - g.getFontMetrics().getHeight());

                currentPosition += (spacePer + padding);
            }

            g.setColor(Color.blue);

            g.fillRect(0, height - (FOOTER_HEIGHT * 2), width, 3);
        }
    }
}