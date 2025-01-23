package com.refactoring.chapter1;

import com.refactoring.chapter1.calculator.ComedyCalculator;
import com.refactoring.chapter1.calculator.TragedyCalculator;

public abstract class PerformanceCalculator {
    protected Invoice.Performance performance;
    protected Play play;

    public PerformanceCalculator(Invoice.Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public static PerformanceCalculator createPerformanceCalculator(Invoice.Performance performance, Play play) {
        return switch (play.getType()) {
            case "tragedy" -> new TragedyCalculator(performance, play);
            case "comedy" -> new ComedyCalculator(performance, play);
            default -> throw new RuntimeException("알 수 없는 장르: " + play.getType());
        };
    }

    abstract public int amountFor();

    public int volumeCreditsFor() {
        return Math.max(performance.getAudience() - 30, 0);
    }
}
