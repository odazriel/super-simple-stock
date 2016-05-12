package com.jpmorgan.stockmarket;

public interface Visitor<T> {
    void visit(T object);
}
