package com.refactoring.chapter1;

public class ComedyCalculator extends PerformanceCalculator {
    public ComedyCalculator(Invoice.Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amountFor() {
        int performanceAmountResult = 30000;
        if (performance.getAudience() > 20) {
            performanceAmountResult += 10000 + 500 * (performance.getAudience() - 20);
        }
        performanceAmountResult += 300 * performance.getAudience();
        return performanceAmountResult;
    }

    @Override
    public int volumeCreditsFor() {
        return super.volumeCreditsFor() + (int) Math.floor((double) performance.getAudience() / 5);
    }
}
