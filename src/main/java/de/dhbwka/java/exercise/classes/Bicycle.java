package de.dhbwka.java.exercise.classes;

public class Bicycle extends Vehicle {
    private static int numWheels = 2;

    @Override
    public String toString() {
        return "Bicycle{" + super.toString() + "} ";
    }

    private static double vMax = 30;

    public Bicycle() {
        super(numWheels, vMax);
    }

    public Bicycle(double currentSpeed) {
        super(numWheels, currentSpeed, vMax);
    }

    @Override
    public void info() {
        System.out.println("Ein tolles Fahrrad");
    }
}