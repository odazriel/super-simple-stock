package com.jpmorgan.stockmarket;

import org.easymock.Mock;
import org.junit.Test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

public class PreferredStockCalculatorTest extends AbstractStockCalculatorTest {

    @Mock
    private Stock mockStock;

    @Test
    public void calculatePositiveYield() throws Exception {
        assertEquals(1.0, calculateYield(1.0, 1.0, 1.0), EPSILON);
    }

    @Test
    public void calculateZeroDividendYield() throws Exception {
        assertEquals(0.0, calculateYield(0.0, 2.0, 1.0), EPSILON);
    }

    @Test
    public void calculateZeroParYield() throws Exception {
        assertEquals(0.0, calculateYield(2.0, 0.0, 1.0), EPSILON);
    }

    @Test
    public void calculateLargeYield() throws Exception {
        assertEquals(500.0, calculateYield(100.0, 50.0, 10.0), EPSILON);
    }

    @Test
    public void calculateNegativeYield() throws Exception {
        assertEquals(-2.0, calculateYield(-4.0, 1.0, 2), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroPrice() throws Exception {
        calculateYield(10.0, 5.0, 0.0);
    }

    private double calculateYield(double dividend, double parValue, double price) {
        expect(mockStock.getFixedDividend()).andReturn(dividend);
        expect(mockStock.getParValue()).andReturn(parValue);
        replay(mockStock);

        return getStockCalculator(mockStock).calculateYield(price);
    }

    @Override
    protected StockCalculator getStockCalculator(Stock stock) {
        return new PreferredStockCalculator(stock);
    }
}