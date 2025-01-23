package com.refactoring.chapter1.calculator;

import com.refactoring.chapter1.EnrichPerformance;
import com.refactoring.chapter1.data.Invoice;
import com.refactoring.chapter1.data.Play;

public abstract class PerformanceCalculator {
    protected Invoice.Performance performance;
    protected Play play;

    public PerformanceCalculator(Invoice.Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public static PerformanceCalculator createPerformanceCalculator(EnrichPerformance enrichPerformance) {
        return switch (enrichPerformance.getPlay().getType()) {
            case "tragedy" -> new TragedyCalculator(enrichPerformance.getPerformance(), enrichPerformance.getPlay());
            case "comedy" -> new ComedyCalculator(enrichPerformance.getPerformance(), enrichPerformance.getPlay());
            default -> throw new RuntimeException("알 수 없는 장르: " + enrichPerformance.getPlay().getType());
        };
    }

    abstract public int amountFor();

    public int volumeCreditsFor() {
        return Math.max(performance.getAudience() - 30, 0);
    }
}
