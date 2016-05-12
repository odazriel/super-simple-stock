package com.jpmorgan.stockmarket;

/**
 * A Stock Market object that can be modified by adding new stocks to it.
 * Existing stocks cannot be removed or updated.
 */
public interface ModifiableStockMarket extends StockMarket {
    void addStock(Stock stock);
}
