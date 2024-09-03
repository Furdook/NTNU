package tasks.O3;

import java.util.ArrayList;
import java.util.List;

public class Nim {
    private List<Integer> piles = new ArrayList<>();

    public Nim(int pileSize) {
        for (int i = 0; i < 3; i++) {
            this.piles.add(pileSize);
        }
    }

    public void removePieces(int number, int targetPile) {
        if (this.isGameOver()) throw new IllegalStateException();
        if (!this.isValidMove(number, targetPile)) throw new IllegalArgumentException();

        this.piles.set(targetPile, this.piles.get(targetPile) - number);
    }

    public boolean isValidMove(int number, int targetPile) {
        if (this.isGameOver()) return false;
        if(number <= 0 || number > this.piles.get(targetPile)) return false;
        
        return true;
    } 

    public boolean isGameOver() {
        for (int pile : this.piles) {
            if (pile <= 0) 
                return true;
        }
        return false;
    } 

    public int getPile(int targetPile) {
        return this.piles.get(targetPile);
    }
}
