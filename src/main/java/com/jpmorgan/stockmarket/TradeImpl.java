package com.jpmorgan.stockmarket;

import java.time.Instant;

class TradeImpl implements Trade {

    private final Instant timestamp;
    private final int quantity;
    private final TradeType type;
    private final double price;

    public TradeImpl(Instant timestamp, int quantity, TradeType type, double price) {
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.type = type;
        this.price = price;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public TradeType getType() {
        return type;
    }

    @Override
    public double getPrice() {
        return price;
    }
}