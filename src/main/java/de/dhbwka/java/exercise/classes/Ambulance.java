package de.dhbwka.java.exercise.classes;

public class Ambulance extends Car {
    boolean blaulicht = true;

    public boolean isBlaulicht() {
        return blaulicht;
    }

    public void setBlaulicht(boolean blaulicht) {
        this.blaulicht = blaulicht;
    }

    public Ambulance() {
        super();
        setBlaulicht(true);
    }

    @Override
    public String toString() {
        return "Ambulance{" +
                "blaulicht=" + blaulicht
                + ", " + super.toString() + "} ";
    }

    public Ambulance(double currentSpeed, boolean blaulicht) {
        new Ambulance();
        setvCurr(currentSpeed);
        setBlaulicht(blaulicht);
    }

    @Override
    public void info() {
        System.out.println("Blaulicht");
    }
}