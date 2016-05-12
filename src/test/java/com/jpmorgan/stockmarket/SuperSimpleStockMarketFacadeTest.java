package com.jpmorgan.stockmarket;

import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.*;

public class SuperSimpleStockMarketFacadeTest {

    private static final double EPSILON = 0.000001;

    private StockMarketFactory stockMarketFactory;
    private SuperSimpleStockMarketFacade facade;

    @Before
    public void before() {
        stockMarketFactory = new SingletonStockMarketFactory();
        facade = new SuperSimpleStockMarketFacade(stockMarketFactory, new StandardStockCalculatorFactory());
    }

    @Test
    public void calculateZeroDividendYieldCommon() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("TEA").setType(StockType.COMMON).setLastDividend(0).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        assertEquals(0.0, facade.calculateDividendYield("TEA", 2.0), EPSILON);
    }

    @Test
    public void calculatePositiveDividendYieldCommon() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("TEA").setType(StockType.COMMON).setLastDividend(10).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        assertEquals(5.0, facade.calculateDividendYield("TEA", 2.0), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateDividendYieldCommonZeroPrice() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("TEA").setType(StockType.COMMON).setLastDividend(10).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.calculateDividendYield("TEA", 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateDividendYieldCommonIllegalStock() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("TEA").setType(StockType.COMMON).setLastDividend(10).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.calculateDividendYield("BLA", 0.0);
    }

    @Test
    public void calculateZeroDividendYieldPreferred() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("GIN").setType(StockType.PREFERRED).setLastDividend(8)
                        .setFixedDividend(0).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        assertEquals(0.0, facade.calculateDividendYield("GIN", 2.0), EPSILON);
    }

    @Test
    public void calculatePositiveDividendYieldPreferred() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("GIN").setType(StockType.PREFERRED).setLastDividend(8)
                        .setFixedDividend(0.02).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        assertEquals(1.0, facade.calculateDividendYield("GIN", 2.0), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateDividendYieldPreferredZeroPrice() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("GIN").setType(StockType.PREFERRED).setLastDividend(8)
                        .setFixedDividend(0.02).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.calculateDividendYield("GIN", 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateDividendYieldPreferredIllegalStock() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("GIN").setType(StockType.PREFERRED).setLastDividend(8)
                        .setFixedDividend(0.02).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.calculateDividendYield("BLA", 0.0);
    }

    @Test
    public void calculatePositivePERatioCommon() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("POP").setType(StockType.COMMON).setLastDividend(8).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        assertEquals(2.0, facade.calculatePERatio("POP", 16.0), EPSILON);
    }

    @Test
    public void calculatePERatioCommonZeroPrice() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("POP").setType(StockType.COMMON).setLastDividend(8).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        assertEquals(0.0, facade.calculatePERatio("POP", 0.0), EPSILON);
    }

    @Test
    public void calculatePERatioCommonZeroDividend() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("POP").setType(StockType.COMMON).setLastDividend(8).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        assertEquals(0.0, facade.calculatePERatio("POP", 0.0), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculatePERatioCommonIllegalStock() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("POP").setType(StockType.COMMON).setLastDividend(8).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.calculatePERatio("BLA", 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeWeightedStockPriceNoStock() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("ALE").setType(StockType.COMMON).setLastDividend(23).setParValue(60).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.calculateVolumeWeightedStockPrice("BLA");
    }


    @Test
    public void calculateVolumeWeightedStockPriceNoHistory() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("ALE").setType(StockType.COMMON).setLastDividend(23).setParValue(60).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        assertEquals(0.0, facade.calculateVolumeWeightedStockPrice("ALE"), EPSILON);
    }

    @Test
    public void calculateVolumeWeightedStockPriceSingleTrade() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("ALE").setType(StockType.COMMON).setLastDividend(23).setParValue(60).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1000)).setPrice(1.0).setQuantity(5).setType(TradeType.BUY).build());

        assertEquals(1.0, facade.calculateVolumeWeightedStockPrice("ALE"), EPSILON);
    }

    @Test
    public void calculateVolumeWeightedStockPriceMultipleTrades() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("ALE").setType(StockType.COMMON).setLastDividend(23).setParValue(60).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1000)).setPrice(1.0).setQuantity(2).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1500)).setPrice(2.0).setQuantity(2).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(2000)).setPrice(4.0).setQuantity(1).setType(TradeType.BUY).build());

        assertEquals(2.0, facade.calculateVolumeWeightedStockPrice("ALE"), EPSILON);
    }

    @Test
    public void calculateVolumeWeightedStockPriceIrrelevantTrades() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("ALE").setType(StockType.COMMON).setLastDividend(23).setParValue(60).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(7000)).setPrice(4.0).setQuantity(1).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(8000)).setPrice(4.0).setQuantity(100).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(10000)).setPrice(50.0).setQuantity(9).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1000)).setPrice(1.0).setQuantity(2).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1500)).setPrice(2.0).setQuantity(2).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(2000)).setPrice(4.0).setQuantity(1).setType(TradeType.BUY).build());

        assertEquals(2.0, facade.calculateVolumeWeightedStockPrice("ALE"), EPSILON);
    }

    @Test
    public void calculateVolumeWeightedStockPriceAllIrrelevant() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("ALE").setType(StockType.COMMON).setLastDividend(23).setParValue(60).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(7000)).setPrice(4.0).setQuantity(1).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(8000)).setPrice(4.0).setQuantity(100).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(10000)).setPrice(50.0).setQuantity(9).setType(TradeType.BUY).build());

        assertEquals(0.0, facade.calculateVolumeWeightedStockPrice("ALE"), EPSILON);
    }

    @Test
    public void calculateGBCEAllShareIndexNoStocks() throws Exception {
        assertEquals(0.0, facade.calculateGBCEAllShareIndex(), EPSILON);
    }

    @Test
    public void calculateGBCEAllShareIndexSingleStock() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("ALE").setType(StockType.COMMON).setLastDividend(23).setParValue(60).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);

        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(7000)).setPrice(4.0).setQuantity(1).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(8000)).setPrice(4.0).setQuantity(100).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1000)).setPrice(1.0).setQuantity(2).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1500)).setPrice(2.0).setQuantity(2).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(2000)).setPrice(4.0).setQuantity(1).setType(TradeType.BUY).build());

        assertEquals(2.0, facade.calculateGBCEAllShareIndex(), EPSILON);
    }

    @Test
    public void calculateGBCEAllShareIndexSingleStockWithHistory() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("ALE").setType(StockType.COMMON).setLastDividend(23).setParValue(60).build();
        final Stock anotherStock =
                new StockBuilder().setSymbol("JOE").setType(StockType.COMMON).setLastDividend(13).setParValue(250).build();

        stockMarketFactory.createModifiableStockMarket().addStock(stock);
        stockMarketFactory.createModifiableStockMarket().addStock(anotherStock);

        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(7000)).setPrice(4.0).setQuantity(1).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(8000)).setPrice(4.0).setQuantity(100).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1000)).setPrice(1.0).setQuantity(2).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1500)).setPrice(2.0).setQuantity(2).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(2000)).setPrice(4.0).setQuantity(1).setType(TradeType.BUY).build());

        assertEquals(0.0, facade.calculateGBCEAllShareIndex(), EPSILON);
    }

    @Test
    public void calculateGBCEAllShareIndexMultipleStocks() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("ALE").setType(StockType.COMMON).setLastDividend(23).setParValue(60).build();
        final Stock anotherStock =
                new StockBuilder().setSymbol("JOE").setType(StockType.COMMON).setLastDividend(13).setParValue(250).build();

        stockMarketFactory.createModifiableStockMarket().addStock(stock);
        stockMarketFactory.createModifiableStockMarket().addStock(anotherStock);

        facade.addTrade("JOE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1000)).setPrice(6.0).setQuantity(1).setType(TradeType.BUY).build());
        facade.addTrade("JOE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(2500)).setPrice(10.0).setQuantity(1).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1000)).setPrice(1.0).setQuantity(2).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1500)).setPrice(2.0).setQuantity(2).setType(TradeType.BUY).build());
        facade.addTrade("ALE",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(2000)).setPrice(4.0).setQuantity(1).setType(TradeType.BUY).build());

        assertEquals(4.0, facade.calculateGBCEAllShareIndex(), EPSILON);
    }


    @Test(expected = IllegalArgumentException.class)
    public void addTradeNonExistingStock() throws Exception {
        facade.addTrade("BLA",
                new TradeBuilder().setTimestamp(Instant.now().minusMillis(1000)).setPrice(6.0).setQuantity(1).setType(TradeType.BUY).build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingStock() throws Exception {
        final Stock stock =
                new StockBuilder().setSymbol("TEA").setType(StockType.COMMON).setLastDividend(10).setParValue(100).build();
        stockMarketFactory.createModifiableStockMarket().addStock(stock);
        stockMarketFactory.createModifiableStockMarket().addStock(stock);
    }
}