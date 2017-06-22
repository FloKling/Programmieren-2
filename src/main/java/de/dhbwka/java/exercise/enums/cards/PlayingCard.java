package de.dhbwka.java.exercise.enums.cards;

/**
 * Created by floriankling on 22.06.17.
 */
public class PlayingCard implements Comparable<PlayingCard> {

    private Suit suit;
    private CardValue cardValue;

    public PlayingCard(Suit suit, CardValue cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    @Override
    public String toString() {
        return suit.name() + " " + cardValue.name();
    }

    @Override
    public int compareTo(PlayingCard c) {
        if (suit.equals(c.suit)) {
            if (c.cardValue.ordinal() < cardValue.ordinal()) {
                return -1;
            } else if (c.cardValue.ordinal() == cardValue.ordinal()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (c.suit.ordinal() < suit.ordinal()) {
                return 1;
            } else
                return -1;
        }
    }
}
