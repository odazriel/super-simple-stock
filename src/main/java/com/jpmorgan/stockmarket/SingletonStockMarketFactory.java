package com.jpmorgan.stockmarket;

/**
 * A Stock Market Factory object that, for the entire lifespan of the object, will always return the same stock market.
 * Note: Two instances of the factory will return different stock market objects.
 */
public class SingletonStockMarketFactory implements StockMarketFactory {

    private final ModifiableStockMarket stockMarket = new StockMarketImpl();

    public StockMarket createStockMarket() {
        return stockMarket;
    }

    public ModifiableStockMarket createModifiableStockMarket() {
        return stockMarket;
    }
}
