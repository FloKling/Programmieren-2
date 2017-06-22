package de.dhbwka.java.exercise.ui.paint;

import javax.swing.*;
import java.awt.*;

public class FirstPaint1 extends JComponent {

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(50, 50, 50, 50);
        g.setColor(new Color(0x3300ff00, true));
        g.fillOval(25, 75, 50, 50);
        g.fillOval(25, 25, 50, 50);
        g.fillOval(75, 25, 50, 50);
        g.fillOval(75, 75, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(50, 50, 50, 50);
        g.drawOval(50, 75, 50, 50);
        g.drawOval(75, 50, 50, 50);
        g.drawOval(75, 75, 50, 50);
        g.drawOval(25, 25, 50, 50);
        g.drawOval(25, 50, 50, 50);
        g.drawOval(50, 25, 50, 50);
        g.drawOval(50, 50, 50, 50);
        g.drawOval(25, 75, 50, 50);
        g.drawOval(75, 25, 50, 50);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("First Paint");
        f.add(new FirstPaint1());
        f.setSize(300, 200);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}