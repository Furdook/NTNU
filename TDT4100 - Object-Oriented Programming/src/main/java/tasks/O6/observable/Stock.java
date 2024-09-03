package tasks.O6.observable;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String ticker;
    private double price;
    private List<StockListener> listeneres = new ArrayList<>();

    public Stock(String ticker, double price) {
        this.ticker = ticker;
        this.setPrice(price);
    }

    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Price cannot be zero or less");

        for (StockListener stockListener : listeneres) {
            stockListener.stockPriceChanged(this, this.price, price);
        }

        this.price = price;
    }

    public String getTicker() {
        return this.ticker;
    }

    public double getPrice() {
        return this.price;
    }

    public void addStockListener(StockListener listener) {
        this.listeneres.add(listener);
    }

    public void removeStockListener(StockListener listener) {
        this.listeneres.remove(listener);
    }
}
