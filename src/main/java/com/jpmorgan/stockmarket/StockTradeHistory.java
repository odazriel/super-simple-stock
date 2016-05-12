package com.jpmorgan.stockmarket;

public interface StockTradeHistory {
    void accept(Visitor<Trade> visitor, int millis);
}
