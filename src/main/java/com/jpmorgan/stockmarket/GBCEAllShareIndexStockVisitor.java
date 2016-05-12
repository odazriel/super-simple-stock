package com.jpmorgan.stockmarket;

public class GBCEAllShareIndexStockVisitor implements Visitor<Stock> {

    public final static int VOLUME_WEIGHTED_STOCK_PRICE_TIME = 5000;

    private int counter = 0;
    private double mul = 1;

    @Override
    public void visit(Stock stock) {
        counter++;

        VolumeWeightedStockPriceTradeVisitor visitor = new VolumeWeightedStockPriceTradeVisitor();
        stock.getTradeHistory().accept(visitor, VOLUME_WEIGHTED_STOCK_PRICE_TIME);
        mul *= visitor.getResult();
    }

    public double getResult() {
        if (counter == 0) {
            return 0;
        } else {
            return Math.pow(mul, 1.0 / counter);
        }
    }
}
