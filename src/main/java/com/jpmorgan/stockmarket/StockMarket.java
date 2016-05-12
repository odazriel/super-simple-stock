package com.jpmorgan.stockmarket;

/**
 * A Stock Market object.
 * The Stock Market allows to retrieve stocks by their symbol and iterator through all the stocks.
 */
public interface StockMarket {
    Stock getStock(String symbol);
    void accept(Visitor<Stock> visitor);
}
