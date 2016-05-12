package com.jpmorgan.stockmarket;

import java.time.Instant;

/**
 * An object representing a Trade for a specific Stock.
 */
public interface Trade {
    Instant getTimestamp();
    int getQuantity();
    TradeType getType();
    double getPrice();
}
