package de.dhbwka.java.exercise.enums.cards;

/**
 * Created by floriankling on 22.06.17.
 */
public class GameMain {

    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        cardGame.printCards();
        cardGame.shuffle();
        cardGame.printCards();
        System.out.println("");
        System.out.println("ZIEHE KARTEN");
        System.out.println("");

        PlayingCard heartSeven = new PlayingCard(Suit.HEART, CardValue.SEVEN);

        for (int i = 0; i < 10; i++) {
            PlayingCard card = cardGame.get();
            System.out.println(card + " \t" + " ist im Vergleich zu Herz 7 " + (card.compareTo(heartSeven) < 0 ? "kleiner" : "größer"));
        }


        System.out.println("");
        System.out.println("NUN SORTIEREN");
        cardGame.sort();
        cardGame.printCards();
    }
}
