package com.jpmorgan.stockmarket;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

@RunWith(EasyMockRunner.class)
abstract class AbstractStockCalculatorTest {
    protected static final double EPSILON = 0.000001;

    @Mock
    private Stock mockStock;

    @Test
    public void calculatePositivePERatio() throws Exception {
        assertEquals(1.0, calculatePERatio(1.0, 1.0), EPSILON);
    }

    @Test
    public void calculateZeroPERatio() throws Exception {
        assertEquals(0.0, calculatePERatio(1.0, 0.0), EPSILON);
    }

    @Test
    public void calculateLargePERatio() throws Exception {
        assertEquals(10.0, calculatePERatio(10.0, 100.0), EPSILON);
    }

    @Test
    public void calculateNegativePERatio() throws Exception {
        assertEquals(-2.0, calculatePERatio(-4.0, 8), EPSILON);
    }

    @Test
    public void calcualtePERationWithZeroDividend() throws Exception {
        assertEquals(0.0, calculatePERatio(0.0, 10.0), EPSILON);
    }

    private double calculatePERatio(double dividend, double price) {
        expect(mockStock.getLastDividend()).andReturn(dividend).anyTimes();
        replay(mockStock);

        return getStockCalculator(mockStock).calculatePERatio(price);
    }

    protected abstract StockCalculator getStockCalculator(Stock stock);
}