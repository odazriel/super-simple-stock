package com.jpmorgan.stockmarket;

final class PreferredStockCalculator extends AbstractStockCalculator {

    public PreferredStockCalculator(Stock stock) {
        super(stock);
    }

    @Override
    public double calculateYield(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        } else {
            return (getStock().getFixedDividend() * getStock().getParValue()) / price;
        }
    }
}