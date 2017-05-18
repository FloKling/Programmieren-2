package de.dhbwka.java.exercise.classes;

public class RacingCar extends Car {
    public RacingCar() {
        super();
        setvMax(220);
    }

    public RacingCar(double currentSpeed) {
        super();
        setvMax(220);
        setvCurr(currentSpeed);
    }

    @Override
    public void info() {
        System.out.println("Racing Car");
    }

    @Override
    public String toString() {
        return "RacingCar{" + super.toString() + "} ";
    }
}