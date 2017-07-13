package de.dhbwka.java.exercise.enums;

import java.util.Calendar;

/**
 * Created by floriankling on 22.06.17.
 */
public class EnumMain {
    static void months() {
        Months month = Months.values()[Calendar.getInstance().get(Calendar.MONTH)];
        System.out.println("Der Monat " + month.getName() + " hat " + month.getDays() + " Tage und hieß früher " + month.getOldNames());
    }

    public static void main(String[] args) {
        months();
    }
}
