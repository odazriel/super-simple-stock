package com.jpmorgan.stockmarket;

abstract class AbstractStockCalculator implements StockCalculator {

    private final Stock stock;

    AbstractStockCalculator(Stock stock) {
        this.stock = stock;
    }

    protected Stock getStock() {
        return stock;
    }

    @Override
    public double calculatePERatio(double price) {
        if (stock.getLastDividend() == 0) {
            return 0.0;
        } else {
            return price / stock.getLastDividend();
        }
    }
}
