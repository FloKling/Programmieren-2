package de.dhbwka.java.exercise.enums;

/**
 * Created by floriankling on 22.06.17.
 */
public enum Months {
    JANUARY("Januar", 31, "Hartung, Eismond"),
    FEBRUARY("Februar", 28, "Hornung, Schmelzmond, Taumond, Narrenmond, Rebmond, Hintester"),
    MARCH("März", 30, "Lenzing, Lenzmond"),
    APRIL("April", 30, "Launing, Ostermond"),
    MAY("Mai", 30, "Winnemond*, Blumenmond"),
    JUNE("Juni", 30, "Brachet, Brachmond"),
    JULY("Juli", 30, "Heuert, Heumond"),
    AUGUST("August", 30, "Ernting, Erntemond, Bisemond"),
    SEPTEMBER("September", 30, "Scheiding, Herbstmond"),
    OCTOBER("Oktober", 30, "Gilbhart, Gilbhard, Weinmond"),
    NOVEMBER("November", 30, "Nebelung, Windmond, Wintermond"),
    DECEMBER("Dezember", 30, "Julmond, Heilmond, Christmond, Dustermond");

    private final int days;
    private final String name;
    private final String oldNames;

    Months(String name, int days, String oldNames) {
        this.days = days;
        this.name = name;
        this.oldNames = oldNames;
    }

    public int getDays() {
        return days;
    }

    public String getName() {
        return name;
    }

    public String getOldNames() {
        return oldNames;
    }

}
