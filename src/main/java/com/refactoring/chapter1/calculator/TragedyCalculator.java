package com.refactoring.chapter1.calculator;

import com.refactoring.chapter1.data.Invoice;
import com.refactoring.chapter1.data.Play;

public class TragedyCalculator extends PerformanceCalculator {
    public TragedyCalculator(Invoice.Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amountFor() {
        int performanceAmountResult = 40000;
        if (performance.getAudience() > 30) {
            performanceAmountResult += 1000 * (performance.getAudience() - 30);
        }
        return performanceAmountResult;
    }
}
