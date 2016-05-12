package com.jpmorgan.stockmarket;

import java.util.HashMap;
import java.util.Map;

class StockMarketImpl implements ModifiableStockMarket {
    private final Map<String, Stock> stocks = new HashMap<>();

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void accept(Visitor<Stock> visitor) {
        for (final Stock stock : stocks.values()) {
            visitor.visit(stock);
        }
    }

    public void addStock(Stock stock) {
        if (stocks.containsKey(stock.getSymbol())) {
            throw new IllegalArgumentException(String.format("Stock [%s] already exists", stock.getSymbol()));
        } else {
            stocks.put(stock.getSymbol(), stock);
        }
    }
}
