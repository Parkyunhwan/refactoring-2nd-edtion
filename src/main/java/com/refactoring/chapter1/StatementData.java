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
                .map(performance -> PerformanceCalculator.createPerformanceCalculator(performance, playFor(performance)).amountFor())
                .reduce(0, Integer::sum);
    }

    public int totalVolumeCredits() {
        return getPerformances().stream()
                .map(performance -> PerformanceCalculator.createPerformanceCalculator(performance, playFor(performance)).volumeCreditsFor())
                .reduce(0, Integer::sum);
    }

    public Play getPlay(Invoice.Performance perf) {
        return playFor(perf);
    }

    private Play playFor(Invoice.Performance perf) {
        return plays.get(perf);
    }
}
