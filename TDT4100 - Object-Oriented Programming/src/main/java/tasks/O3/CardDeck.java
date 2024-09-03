package tasks.O3;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private List<Card> deck = new ArrayList<Card>();

    public CardDeck(int n) {
        if (n < 0) throw new IllegalArgumentException();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                this.deck.add(new Card(Card.suits[i], j + 1));
            }
        }
    }

    public int getCardCount() {
        return this.deck.size();
    }

    public Card getCard(int n) {
        if (n < 0 || n > this.getCardCount()) throw new IllegalArgumentException();
        return this.deck.get(n);
    }

    public void shufflePerfectly() {
        List<Card> splitDeck = this.deck.subList(this.getCardCount() / 2, this.getCardCount());
        List<Card> splitDeck2 = this.deck.subList(0, getCardCount() / 2);
        List<Card> shuffledDeck = new ArrayList<Card>();

        for (int i = 0; i < this.getCardCount(); i++) {
            shuffledDeck.add(i % 2 == 0 ? splitDeck2.get(i/2) : splitDeck.get(i/2));
        }

        this.deck = shuffledDeck;
    } 
}

