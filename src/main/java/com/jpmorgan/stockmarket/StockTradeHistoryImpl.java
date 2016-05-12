package com.jpmorgan.stockmarket;

import java.time.Instant;
import java.util.*;

class StockTradeHistoryImpl implements ModifiableStockTradeHistory {

    private List<Trade> tradeHistory = new LinkedList<>();

    @Override
    public void addTrade(Trade trade) {
        final ListIterator<Trade> iterator = tradeHistory.listIterator();
        boolean isFound = false;
        while (iterator.hasNext() && !isFound) {
            final Trade current = iterator.next();
            if (current.getTimestamp().isBefore(trade.getTimestamp())) {
                isFound = true;
            }
        }

        if (!isFound) {
            iterator.add(trade);
        } else {
            iterator.previous();
            iterator.add(trade);
        }
    }

    @Override
    public void accept(Visitor<Trade> visitor, int millis) {
        final Instant earliest = Instant.now().minusMillis(millis);

        for (final Trade trade : tradeHistory) {
            if (trade.getTimestamp().isAfter(earliest)) {
                visitor.visit(trade);
            } else {
                break;
            }
        }
    }
}
