package com.jpmorgan.stockmarket;

public class StockBuilder {
    private String symbol;
    private StockType type;
    private double lastDividend;
    private double fixedDividend;
    private double parValue;
    private StockTradeHistory tradeHistory;

    public StockBuilder setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public StockBuilder setType(StockType type) {
        this.type = type;
        return this;
    }

    public StockBuilder setLastDividend(double lastDividend) {
        this.lastDividend = lastDividend;
        return this;
    }

    public StockBuilder setFixedDividend(double fixedDividend) {
        this.fixedDividend = fixedDividend;
        return this;
    }

    public StockBuilder setParValue(double parValue) {
        this.parValue = parValue;
        return this;
    }

    public Stock build() {
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol must not be empty");
        } else {
            return new StockImpl(symbol, type, lastDividend, fixedDividend, parValue, new StockTradeHistoryImpl());
        }
    }
}
