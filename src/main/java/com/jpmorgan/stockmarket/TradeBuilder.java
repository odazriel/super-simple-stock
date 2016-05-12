package com.jpmorgan.stockmarket;

import java.time.Instant;

public class TradeBuilder {
    private Instant timestamp;
    private int quantity;
    private TradeType type;
    private double price;

    public TradeBuilder setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public TradeBuilder setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        } else {
            this.quantity = quantity;
            return this;
        }
    }

    public TradeBuilder setType(TradeType type) {
        this.type = type;
        return this;
    }

    public TradeBuilder setPrice(double price) {
        if (price < Stock.PRICE_EPSILON) {
            throw new IllegalArgumentException("Price must be positive");
        } else {
            this.price = price;
            return this;
        }
    }

    public Trade build() {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity was not set");
        } else if (price < Stock.PRICE_EPSILON) {
            throw new IllegalArgumentException("Price was not set");
        } else {
            if (timestamp == null) {
                timestamp = Instant.now();
            }
            return new TradeImpl(timestamp, quantity, type, price);
        }
    }
}
