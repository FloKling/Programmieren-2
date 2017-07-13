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

    public static void main(String[] args) {
        new TrafficLight();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(10, 10, 40, 100);

        g.setColor(current.isRed() ? Color.RED : Color.GRAY);
        g.fillOval(10, 30, 20, 20);
        g.setColor(current.isYellow() ? Color.YELLOW : Color.GRAY);
        g.fillOval(10, 50, 20, 20);
        g.setColor(current.isGreen() ? Color.GREEN : Color.GRAY);
        g.fillOval(10, 70, 20, 20);
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
