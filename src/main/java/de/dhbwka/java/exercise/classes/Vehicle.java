package de.dhbwka.java.exercise.classes;

public abstract class Vehicle {
    int numWheels = 0;
    double vCurr, vMax, currentPosition;

    public abstract void info();

    void drive(double time) {
        currentPosition = currentPosition + (vCurr * time);
    }

    public Vehicle(int numWheels, double vMax) {
        this.numWheels = numWheels;
        this.vMax = vMax;
    }

    public Vehicle(int numWheels, double vCurr, double vMax) {
        this.numWheels = numWheels;
        this.vCurr = vCurr;
        this.vMax = vMax;
    }

    public int getNumWheels() {
        return numWheels;
    }

    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    public double getvCurr() {
        return vCurr;
    }

    public void setvCurr(double vCurr) {
        if(vCurr > vMax) {
            this.vCurr = vMax;
        } else {
            this.vCurr = vCurr;
        }
    }

    public double getvMax() {
        return vMax;
    }

    public void setvMax(double vMax) {
        this.vMax = vMax;
    }

    public double getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(double currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "numWheels=" + numWheels +
                ", vCurr=" + vCurr +
                ", vMax=" + vMax +
                ", currentPosition=" + currentPosition +
                '}';
    }
}