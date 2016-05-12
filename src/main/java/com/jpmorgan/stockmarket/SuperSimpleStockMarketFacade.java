package com.jpmorgan.stockmarket;

public final class SuperSimpleStockMarketFacade {

    private final StockMarketFactory stockMarketFactory;
    private final StockCalculatorFactory calculatorFactory;

    public SuperSimpleStockMarketFacade(
            StockMarketFactory stockMarketFactory, StockCalculatorFactory stockCalculatorFactory) {
        this.stockMarketFactory = stockMarketFactory;
        this.calculatorFactory = stockCalculatorFactory;
    }

    public double calculateDividendYield(String stockSymbol, double price) {
        final Stock stock = getStock(stockSymbol);
        return calculatorFactory.createStockCalculator(stock).calculateYield(price);
    }

    public double calculatePERatio(String stockSymbol, double price) {
        final Stock stock = getStock(stockSymbol);
        return calculatorFactory.createStockCalculator(stock).calculatePERatio(price);
    }

    public double calculateVolumeWeightedStockPrice(String stockSymbol) {
        final Stock stock = getStock(stockSymbol);

        final VolumeWeightedStockPriceTradeVisitor visitor = new VolumeWeightedStockPriceTradeVisitor();
        stock.getTradeHistory().accept(visitor, GBCEAllShareIndexStockVisitor.VOLUME_WEIGHTED_STOCK_PRICE_TIME);
        return visitor.getResult();
    }

    public double calculateGBCEAllShareIndex() {
        GBCEAllShareIndexStockVisitor visitor = new GBCEAllShareIndexStockVisitor();
        stockMarketFactory.createStockMarket().accept(visitor);
        return visitor.getResult();
    }

    public void addTrade(String stockSymbol, Trade trade) {
        final Stock stock = getStock(stockSymbol);
        if (stock.getTradeHistory() instanceof ModifiableStockTradeHistory) {
            final ModifiableStockTradeHistory modifiableStockTradeHistory = (ModifiableStockTradeHistory) stock.getTradeHistory();
            modifiableStockTradeHistory.addTrade(trade);
        }
    }

    private Stock getStock(String stockSymbol) {
        final Stock stock = stockMarketFactory.createStockMarket().getStock(stockSymbol);
        if (stock == null) {
            throw new IllegalArgumentException("Non existing Stock");
        } else {
            return stock;
        }
    }
}
