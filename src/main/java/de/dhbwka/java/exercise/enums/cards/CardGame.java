package de.dhbwka.java.exercise.enums.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by floriankling on 22.06.17.
 */
public class CardGame {

    List<PlayingCard> cards;

    public CardGame() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (CardValue cardValue : CardValue.values()) {
                cards.add(new PlayingCard(suit, cardValue));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void sort() {
        Collections.sort(cards);
    }

    public PlayingCard get() {
        PlayingCard card = cards.get(cards.size() - 1);
        cards.remove(card);
        return card;
    }

    public List<PlayingCard> all() {
        return cards;
    }

    public void printCards() {
        for (PlayingCard playingCard : all()) {
            System.out.println(playingCard);
        }
    }
}
