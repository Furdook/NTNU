package tasks.O1;

public class Digit {
    private int base;
    private int count = 0;

    public Digit(int base) {
        if (base <= 0) throw new IllegalArgumentException();
        this.base = base;
    }
    
    public int getBase() {
        return this.base;
    }

    public int getValue() {
        return this.count;
    }

    public boolean increment() {
        this.count++;
        
        if (this.count == this.base) 
            this.count = 0;

        return this.count == 0 ? true : false;   
    }

    @Override
    public String toString() {
        return Integer.toHexString(this.count).toUpperCase();
    }
}
