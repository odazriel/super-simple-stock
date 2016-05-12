package com.jpmorgan.stockmarket;

import org.junit.Test;

import static org.junit.Assert.*;

public class VolumeWeightedStockPriceTradeVisitorTest {

    private static final double EPSILON = 0.000001;

    @Test
    public void noTrades() throws Exception {
        assertEquals(0.0, new VolumeWeightedStockPriceTradeVisitor().getResult(), EPSILON);
    }

    @Test
    public void singleTrade() throws Exception {
        final VolumeWeightedStockPriceTradeVisitor visitor = new VolumeWeightedStockPriceTradeVisitor();

        visitor.visit(new TradeBuilder().setPrice(2.0).setQuantity(3).build());

        assertEquals(2.0, visitor.getResult(), EPSILON);
    }

    @Test
    public void multipleTrades() throws Exception {
        final VolumeWeightedStockPriceTradeVisitor visitor = new VolumeWeightedStockPriceTradeVisitor();

        visitor.visit(new TradeBuilder().setPrice(3.0).setQuantity(1).build());
        visitor.visit(new TradeBuilder().setPrice(4.0).setQuantity(2).build());
        visitor.visit(new TradeBuilder().setPrice(12.0).setQuantity(2).build());

        assertEquals(7.0, visitor.getResult(), EPSILON);
    }
}