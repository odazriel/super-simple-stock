package com.jpmorgan.stockmarket;

final class CommonStockCalculator extends AbstractStockCalculator {

    public CommonStockCalculator(Stock stock) {
        super(stock);
    }

    @Override
    public double calculateYield(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        } else {
            return getStock().getLastDividend() / price;
        }
    }
}
