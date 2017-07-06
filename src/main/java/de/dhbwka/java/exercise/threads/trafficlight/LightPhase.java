package de.dhbwka.java.exercise.threads.trafficlight;

/**
 * Created by floriankling on 06.07.17.
 */
public enum LightPhase {

    GREEN(true, false, false, 1) {
        @Override
        public LightPhase nextPhase() {
            return YELLOW;
        }
    },
    YELLOW(true, false, false, 1) {
        @Override
        public LightPhase nextPhase() {
            return RED;
        }
    },
    RED(true, false, false, 1) {
        @Override
        public LightPhase nextPhase() {
            return YELLOW_RED;
        }
    },
    YELLOW_RED(true, false, false, 1) {
        @Override
        public LightPhase nextPhase() {
            return GREEN;
        }
    };

    private boolean green;
    private boolean yellow;
    private boolean red;
    private int duration;

    LightPhase(boolean green, boolean yellow, boolean red, int duration) {
        this.green = green;
        this.yellow = yellow;
        this.red = red;
        this.duration = duration;
    }

    public abstract LightPhase nextPhase();


    public boolean isGreen() {
        return green;
    }

    public boolean isYellow() {
        return yellow;
    }

    public boolean isRed() {
        return red;
    }

    public int getDuration() {
        return duration;
    }
}
