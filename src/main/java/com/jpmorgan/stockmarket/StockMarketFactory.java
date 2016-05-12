package com.jpmorgan.stockmarket;

public interface StockMarketFactory {
    StockMarket createStockMarket();
    ModifiableStockMarket createModifiableStockMarket();
}
