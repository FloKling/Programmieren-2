package de.dhbwka.java.exercise.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by floriankling on 08.06.17.
 */
public class Lottery {

    private List<Integer> numbers;
    private SortedSet<Integer> lotteryNumbers;
    private Integer superNumber;

    public Lottery() {
        this.numbers = new ArrayList<>();
        this.lotteryNumbers = new TreeSet<>();

        for (int i = 1; i < 50; i++) {
            this.numbers.add(i);
        }

        doLottery();
    }

    private void doLottery() {
        for (int i = 0; i < 7; i++) {
            int lotteryNumber = (int) (Math.random() * (numbers.size() - 1));

            if (i >= 6) {
                this.superNumber = numbers.get(lotteryNumber);
            } else {
                this.lotteryNumbers.add(numbers.get(lotteryNumber));
            }

            this.numbers.remove(lotteryNumber);
        }

        StringBuilder output = new StringBuilder();

        for (Integer i : this.lotteryNumbers) {
            output.append(i);
            output.append(" ");
        }

        output.append("Zusatzzahl: ");
        output.append(superNumber);

        System.out.println(output);
    }

    public static void main(String[] args) {
        new Lottery();
    }
}