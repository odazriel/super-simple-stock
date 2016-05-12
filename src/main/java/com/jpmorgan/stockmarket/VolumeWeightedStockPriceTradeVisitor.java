package com.jpmorgan.stockmarket;

public class VolumeWeightedStockPriceTradeVisitor implements Visitor<Trade> {

    private int quantity = 0;
    private double tradedPrice = 0;

    @Override
    public void visit(Trade trade) {
        quantity += trade.getQuantity();
        tradedPrice += trade.getPrice() * trade.getQuantity();
    }

    public double getResult() {
        if (quantity == 0) {
            return 0;
        } else {
            return tradedPrice / quantity;
        }
    }
}
