package com.refactoring.chapter1;

import java.util.List;

public class StatementData {
    private Invoice invoice;
    private Plays plays;

    public StatementData(Invoice invoice, Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public static StatementData createStatementData(Invoice invoice, Plays plays) {
        return new StatementData(invoice, plays);
    }

    public List<Invoice.Performance> getPerformances() {
        return invoice.getPerformances();
    }

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public int totalAmount() {
        return getPerformances().stream()
                .map(this::amountFor)
                .reduce(0, Integer::sum);
    }

    public int totalVolumeCredits() {
        return getPerformances().stream()
                .map(this::volumeCreditsFor)
                .reduce(0, Integer::sum);
    }

    public int volumeCreditsFor(Invoice.Performance perf) {
        int result = Math.max(perf.getAudience() - 30, 0);
        if ("comedy".equals(playFor(perf).getType())) {
            result += (int) Math.floor((double) perf.getAudience() / 5);
        }
        return result;
    }

    public int amountFor(Invoice.Performance performance) {
        int performanceAmountResult = 0;

        switch (playFor(performance).getType()) {
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
                throw new RuntimeException("알 수 없는 장르: " + playFor(performance).getType());
        }
        return performanceAmountResult; // 값이 바뀌는 변수값 반환
    }

    public Play getPlay(Invoice.Performance perf) {
        return playFor(perf);
    }

    private Play playFor(Invoice.Performance perf) {
        return plays.get(perf);
    }
}
