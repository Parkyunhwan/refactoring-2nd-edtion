package com.refactoring.chapter1;

public abstract class PerformanceCalculator {
    Invoice.Performance performance;
    Play play;

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
