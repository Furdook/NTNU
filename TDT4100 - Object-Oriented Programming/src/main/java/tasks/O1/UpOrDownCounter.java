package tasks.O1;

public class UpOrDownCounter {
    private int start;
    private int end;

    public UpOrDownCounter(int start, int end) {
        if (start == end) throw new IllegalArgumentException("'start' cannot be the same as 'end'");

        this.start = start;
        this.end = end;
    }

    public int getCounter() {
        return this.start;
    }

    public boolean count() {
        if (this.start == this.end) return false;

        if (this.start < this.end) this.start++;
        else this.start--;

        return this.start == this.end ? false : true;
    }


}
