package com.jpmorgan.stockmarket;

import java.util.ArrayList;
import java.util.List;

class ToListVisitor<T> implements Visitor<T> {

    private final List<T> result = new ArrayList<>();

    @Override
    public void visit(T object) {
        result.add(object);
    }

    List<T> getResult() {
        return result;
    }
}
