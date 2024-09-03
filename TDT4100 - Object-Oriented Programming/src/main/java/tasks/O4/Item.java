package tasks.O4;

public class Item {
    private String name;
    private String type;
    private Merchant owner;
    private double price;

    public Item(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Merchant getOwner() {
        return this.owner;
    }

    public void changeOwner(Merchant owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0) throw new RuntimeException();
        this.price = price;
    }
    
}
