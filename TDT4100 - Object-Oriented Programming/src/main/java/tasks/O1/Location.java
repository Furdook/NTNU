package tasks.O1;

public class Location {
    private int[] pos = {0, 0};

    public Location() { }
    
    public void up() {
        this.pos[1]--;
    } 

    public void down() {
        this.pos[1]++;
    }

    public void left() {
        this.pos[0]--;
    }

    public void right() {
        this.pos[0]++;
    }

    public int getX() {
        return this.pos[0];
    }

    public int getY() {
        return this.pos[1];
    }
}
