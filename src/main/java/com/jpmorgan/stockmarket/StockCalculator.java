package com.jpmorgan.stockmarket;

/**
 * Calculates basic stock information such as the dividend yield and P/E Ratio.
 */
public interface StockCalculator {
    double calculateYield(double price);
    double calculatePERatio(double price);
}
