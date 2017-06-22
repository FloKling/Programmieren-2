package de.dhbwka.java.exercise.ui.paint;

import javax.swing.*;
import java.awt.*;

public class FrameDraw extends JFrame {

    public FrameDraw() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar mb = new JMenuBar();
        mb.add(new JMenu("File"));

        this.setJMenuBar(mb);

        this.setSize(200, 200);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FrameDraw();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Hallo!", 20, 40);
    }
}
