package tasks.O6.observable;

import java.util.ArrayList;
import java.util.List;

public class StockIndex implements StockListener {
    private List<Stock> stockList = new ArrayList<>();

    // index useless for this task 🤷‍♀️
    public StockIndex(String index, Stock... stock) {
        for (Stock i : stock) {
            this.stockList.add(i);
        }
    }

    public void addStock(Stock stock) {
        if (this.stockList.contains(stock)) return;
        this.stockList.add(stock);
    }

    public void removeStock(Stock stock) {
        this.stockList.remove(stock);
    }

    public double getIndex() {
        return this.stockList.stream().mapToDouble(s -> s.getPrice()).sum();
    }

    @Override  // unused lol 🙃
    public void stockPriceChanged(Stock stock, double oldValue, double newValue) {
        
    }

}
