package de.dhbwka.java.exercise.threads.trafficlight;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by floriankling on 06.07.17.
 */
public class TrafficLight extends JFrame implements Runnable {

    private LightPhase current = LightPhase.GREEN;

    public TrafficLight() {
        this.setSize(50, 100);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(10, 10, 40, 100);

        switch (current) {
            case GREEN:
                g.setColor(Color.GRAY);
                g.fillOval(10, 30, 20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(10, 50, 20, 20);
                g.setColor(Color.GREEN);
                g.fillOval(10, 70, 20, 20);

                break;
            case YELLOW:
                g.setColor(Color.GRAY);
                g.fillOval(10, 30, 20, 20);
                g.setColor(Color.YELLOW);
                g.fillOval(10, 50, 20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(10, 70, 20, 20);
                break;
            case YELLOW_RED:
                g.setColor(Color.RED);
                g.fillOval(10, 30, 20, 20);
                g.setColor(Color.YELLOW);
                g.fillOval(10, 50, 20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(10, 70, 20, 20);
                break;
            case RED:
                g.setColor(Color.RED);
                g.fillOval(10, 30, 20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(10, 50, 20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(10, 70, 20, 20);
                break;
        }
    }

    public static void main(String[] args) {
        new TrafficLight();
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(current.getDuration() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current = current.nextPhase();
            repaint();
        }
    }
}
