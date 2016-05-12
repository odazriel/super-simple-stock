package com.jpmorgan.stockmarket;

class StockImpl implements Stock {

    private final String symbol;
    private final StockType type;
    private final double lastDividend;
    private final double fixedDividend;
    private final double parValue;
    private final StockTradeHistory tradeHistory;

    public StockImpl(
            String symbol, StockType type,
            double lastDividend, double fixedDividend, double parValue, StockTradeHistory tradeHistory) {
        this.symbol = symbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
        this.tradeHistory = tradeHistory;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public StockType getType() {
        return type;
    }

    @Override
    public double getLastDividend() {
        return lastDividend;
    }

    @Override
    public double getFixedDividend() {
        return fixedDividend;
    }

    @Override
    public double getParValue() {
        return parValue;
    }

    @Override
    public StockTradeHistory getTradeHistory() {
        return tradeHistory;
    }
}
