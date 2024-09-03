package tasks.O4;

public class Card {
    private char suit;
    private int face;
    public static char[] suits = {'S', 'H', 'D', 'C'};

    public Card(char suit, int face) {
        if (face > 13 || face <= 0) throw new IllegalArgumentException();
        if (new String(suits).indexOf(suit) == -1) throw new IllegalArgumentException();

        this.suit = suit;
        this.face = face;
    }

    public char getSuit() {
        return this.suit;
    }

    public int getFace() {
        return this.face;
    }

    @Override
    public String toString() {
        return "" + this.suit + this.face;
    }
}
