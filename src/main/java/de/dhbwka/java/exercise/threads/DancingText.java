package de.dhbwka.java.exercise.threads;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by floriankling on 06.07.17.
 */
public class DancingText extends JFrame implements Runnable {

    private AnimatedText animatedText;
    private java.util.List<Color> colors = Arrays.asList(new Color[]{Color.BLACK, Color.BLUE, Color.YELLOW, Color.GREEN, Color.RED});

    public DancingText() throws HeadlessException {
        animatedText = new AnimatedText();
        add(animatedText);
        this.setSize(400, 200);
        this.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
                Collections.shuffle(colors);
                animatedText.setForeground(colors.get(0));
                animatedText.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class AnimatedText extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            if (g instanceof Graphics2D) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.drawString("Dancing text", 70, 20);
            }
        }
    }

    public static void main(String[] args) {
        new DancingText();
    }
}
