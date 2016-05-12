package com.jpmorgan.stockmarket;

public interface StockCalculatorFactory {
    StockCalculator createStockCalculator(Stock stock);
}
