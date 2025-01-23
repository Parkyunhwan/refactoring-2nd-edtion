package com.refactoring.chapter1;

public class PerformanceCalculator {
    Invoice.Performance performance;
    Play play;

    public PerformanceCalculator(Invoice.Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public int amountFor() {
        int performanceAmountResult = 0;

        switch (play.getType()) {
            case "tragedy":
                performanceAmountResult = 40000;
                if (performance.getAudience() > 30) {
                    performanceAmountResult += 1000 * (performance.getAudience() - 30);
                }
                break;
            case "comedy":
                performanceAmountResult = 30000;
                if (performance.getAudience() > 20) {
                    performanceAmountResult += 10000 + 500 * (performance.getAudience() - 20);
                }
                performanceAmountResult += 300 * performance.getAudience();
                break;
            default:
                throw new RuntimeException("알 수 없는 장르: " + play.getType());
        }
        return performanceAmountResult; // 값이 바뀌는 변수값 반환
    }
}
