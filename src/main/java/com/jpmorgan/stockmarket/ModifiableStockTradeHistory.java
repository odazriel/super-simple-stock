package com.jpmorgan.stockmarket;

public interface ModifiableStockTradeHistory extends StockTradeHistory {
    void addTrade(Trade trade);
}
