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
                .map(performance -> new PerformanceCalculator(performance, playFor(performance)).amountFor())
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

    public Play getPlay(Invoice.Performance perf) {
        return playFor(perf);
    }

    private Play playFor(Invoice.Performance perf) {
        return plays.get(perf);
    }
}
