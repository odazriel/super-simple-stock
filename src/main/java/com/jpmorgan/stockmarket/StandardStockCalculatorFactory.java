package com.jpmorgan.stockmarket;

public class StandardStockCalculatorFactory implements StockCalculatorFactory {

    @Override
    public StockCalculator createStockCalculator(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Stock must not be null");
        } else if (stock.getType() == StockType.COMMON) {
            return new CommonStockCalculator(stock);
        } else if (stock.getType() == StockType.PREFERRED) {
            return new PreferredStockCalculator(stock);
        } else {
            // Should never get here in current implementation (fail-safe for future changes)
            throw new IllegalArgumentException("Unsupported Stock Type");
        }
    }
}
