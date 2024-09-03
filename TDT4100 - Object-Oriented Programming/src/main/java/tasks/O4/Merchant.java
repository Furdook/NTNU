package tasks.O4;

import java.util.ArrayList;
import java.util.Collection;

public class Merchant {
    private Collection<Item> inventory = new ArrayList<>();
    private double money;

    public Merchant(double money) {
        if (money < 0) throw new IllegalArgumentException(); 
        this.money = money;
    }

    public double getBalance() {
        return this.money;
    }

    public Collection<Item> getInventory() {
        return this.inventory;
    }

    public void obtainItem(Item item) {
        this.inventory.add(item);
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    public void sellItem(Item item, Merchant buyer) {
        if ((buyer == this)
        || (buyer.getBalance() < item.getPrice())
        || (!this.inventory.contains(item))) throw new IllegalArgumentException();

        this.money += item.getPrice();
        buyer.money -= item.getPrice();

        this.inventory.remove(item);
        buyer.inventory.add(item);

        item.changeOwner(buyer);
    }

    public void itemSale(double sale, Item item) {
        if ((sale < 0 || sale > 1)
        || (!this.inventory.contains(item))) throw new IllegalArgumentException();

        item.setPrice(item.getPrice() * (1 - sale));
    }

    public void inventorySale(double sale) {
        for (Item item : this.inventory) {
            item.setPrice(item.getPrice() * (1 - sale));
        }

    }
}
