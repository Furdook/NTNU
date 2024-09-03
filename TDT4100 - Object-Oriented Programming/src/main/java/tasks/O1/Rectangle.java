package tasks.O1;

public class Rectangle {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getMinX() {
        return Math.min(this.x1, this.x2);
    }

    public int getMinY() {
        return Math.min(this.y1, this.y2);
    }

    public int getMaxX() {
        return Math.max(this.x1, this.x2);
    }
    
    public int getMaxY() {
        return Math.max(this.y1, this.y2);
    }
   
    public int getWidth() {
        return this.getMaxX() - this.getMinX();
    } 
    
    public int getHeight() {
        return this.getMaxY() - this.getMinY();
    } 

    public boolean isEmpty() {
        return this.getWidth() == 0 || this.getHeight() == 0;
    }

    public boolean contains(int x, int y) {
        return  x <= this.getMaxX() && 
                x >= this.getMinX() && 
                y <= this.getMaxY() && 
                y >= this.getMinY() && 
                !this.isEmpty();

    }

    public boolean add(int x, int y) {
        if (this.contains(x, y)) return false;
        
        if (x > this.getMaxX()) {
            if (this.x1 > this.x2) this.x1 = x;
            else this.x2 = x;
        }

        if (y > this.getMaxY()) {
            if (this.y1 > this.y2) this.y1 = y;
            else this.y2 = y;
        }

        if (x < this.getMinX()) {
            if (this.x1 < this.x2) this.x1 = x;
            else this.x2 = x;
        }

        if (y < this.getMinY()) {
            if (this.y1 < this.y2) this.y1 = y;
            else this.y2 = y;
        }
        return true;
    }

    public boolean add(Rectangle rect) {
        return this.add(rect.getMaxX(), rect.getMaxY()) || 
               this.add(rect.getMinX(), rect.getMinY());
    }

    public Rectangle union(Rectangle rect) {
        return new Rectangle(
            Math.min(rect.getMinX(), this.getMinX()), 
            Math.min(rect.getMinY(), this.getMinY()), 
            Math.max(rect.getMaxX(), this.getMaxX()), 
            Math.max(rect.getMaxY(), this.getMaxY())
        );
    }

    public boolean intersects(Rectangle rect) {
        return this.contains(rect.getMinX(), rect.getMinY()) || 
               this.contains(rect.getMaxX(), rect.getMaxY()) || 
               rect.contains(this.getMinX(), this.getMinY()) || 
               rect.contains(this.getMaxX(), this.getMaxY());
    }

    public Rectangle intersection(Rectangle rect) {
        if (!this.intersects(rect)) return null;
        
        return new Rectangle(
            Math.max(rect.getMinX(), this.getMinX()), 
            Math.max(rect.getMinY(), this.getMinY()), 
            Math.min(rect.getMaxX(), this.getMaxX()), 
            Math.min(rect.getMaxY(), this.getMaxY())
        );
    }
}
