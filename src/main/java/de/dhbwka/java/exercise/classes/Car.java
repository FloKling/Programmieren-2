package de.dhbwka.java.exercise.classes;

public class Car extends Vehicle {

    @Override
    public void info() {
        System.out.println("Ganz normales Auto");
    }

    @Override
    public String toString() {
        return "Car{" + super.toString() + "} ";
    }

    private static int numWheels = 4;
    private static double vMax = 140;

    public Car() {
        super(numWheels, vMax);
    }

    public Car(double currentSpeed) {
        super(numWheels, vMax);
        setvCurr(currentSpeed);
    }
}