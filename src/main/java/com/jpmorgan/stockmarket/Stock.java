package com.jpmorgan.stockmarket;

/**
 * An object representing a stock.
 */
public interface Stock {
    double PRICE_EPSILON = 0.0000001;

    String getSymbol();
    StockType getType();
    double getLastDividend();
    double getFixedDividend();
    double getParValue();
    StockTradeHistory getTradeHistory();
}