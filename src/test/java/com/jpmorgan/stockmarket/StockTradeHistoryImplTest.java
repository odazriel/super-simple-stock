package com.jpmorgan.stockmarket;

import org.junit.Test;

import java.time.Instant;
import java.util.Arrays;

import static org.junit.Assert.*;

public class StockTradeHistoryImplTest {

    @Test
    public void visitEmptyList() throws Exception {
        final ToListVisitor<Trade> visitor = new ToListVisitor<>();
        new StockTradeHistoryImpl().accept(visitor, 100);

        assertTrue(visitor.getResult().isEmpty());
    }

    @Test
    public void addTradesReverseOrder() throws Exception {
        final ModifiableStockTradeHistory tradeHistory = new StockTradeHistoryImpl();

        final Trade trade1 = new TradeBuilder().setPrice(1.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(100)).build();
        final Trade trade2 = new TradeBuilder().setPrice(2.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(200)).build();
        final Trade trade3 = new TradeBuilder().setPrice(3.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(300)).build();

        tradeHistory.addTrade(trade1);
        tradeHistory.addTrade(trade2);
        tradeHistory.addTrade(trade3);

        final ToListVisitor<Trade> visitor = new ToListVisitor<>();
        tradeHistory.accept(visitor, 10000);

        assertEquals(Arrays.asList(trade1, trade2, trade3), visitor.getResult());
    }

    @Test
    public void addTradesInOrder() throws Exception {
        final ModifiableStockTradeHistory tradeHistory = new StockTradeHistoryImpl();

        final Trade trade1 = new TradeBuilder().setPrice(3.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(300)).build();
        final Trade trade2 = new TradeBuilder().setPrice(2.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(200)).build();
        final Trade trade3 = new TradeBuilder().setPrice(1.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(100)).build();

        tradeHistory.addTrade(trade1);
        tradeHistory.addTrade(trade2);
        tradeHistory.addTrade(trade3);

        final ToListVisitor<Trade> visitor = new ToListVisitor<>();
        tradeHistory.accept(visitor, 10000);

        assertEquals(Arrays.asList(trade3, trade2, trade1), visitor.getResult());
    }

    @Test
    public void filterTrades() throws Exception {
        final ModifiableStockTradeHistory tradeHistory = new StockTradeHistoryImpl();

        final Trade trade1 = new TradeBuilder().setPrice(3.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(300)).build();
        final Trade trade2 = new TradeBuilder().setPrice(2.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(200)).build();
        final Trade trade3 = new TradeBuilder().setPrice(1.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(100)).build();
        final Trade trade4 = new TradeBuilder().setPrice(1.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(400)).build();

        tradeHistory.addTrade(trade1);
        tradeHistory.addTrade(trade2);
        tradeHistory.addTrade(trade3);
        tradeHistory.addTrade(trade4);

        final ToListVisitor<Trade> visitor = new ToListVisitor<>();
        tradeHistory.accept(visitor, 250);

        assertEquals(Arrays.asList(trade3, trade2), visitor.getResult());
    }

    @Test
    public void filterAllTrades() throws Exception {
        final ModifiableStockTradeHistory tradeHistory = new StockTradeHistoryImpl();

        final Trade trade1 = new TradeBuilder().setPrice(3.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(300)).build();
        final Trade trade2 = new TradeBuilder().setPrice(2.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(200)).build();
        final Trade trade3 = new TradeBuilder().setPrice(1.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(100)).build();
        final Trade trade4 = new TradeBuilder().setPrice(1.0).setQuantity(1).setTimestamp(Instant.now().minusMillis(400)).build();

        tradeHistory.addTrade(trade1);
        tradeHistory.addTrade(trade2);
        tradeHistory.addTrade(trade3);
        tradeHistory.addTrade(trade4);

        final ToListVisitor<Trade> visitor = new ToListVisitor<>();
        tradeHistory.accept(visitor, 50);

        assertTrue(visitor.getResult().isEmpty());
    }
}