package com.refactoring.chapter1;

public class Statement {
    int totalAmount = 0;
    public String statement(Invoice invoice, Plays plays) {
        return String.format("총액: %s", totalAmount);
    }
}
