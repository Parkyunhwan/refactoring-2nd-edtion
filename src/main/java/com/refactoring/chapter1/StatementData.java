package com.refactoring.chapter1;

import com.refactoring.chapter1.calculator.PerformanceCalculator;
import com.refactoring.chapter1.data.Invoice;
import com.refactoring.chapter1.data.Play;
import com.refactoring.chapter1.data.Plays;

import java.util.ArrayList;
import java.util.List;

public class StatementData {
    private Plays plays;

    private String customer;
    private List<Invoice.Performance> performances;


    public StatementData(Invoice invoice, Plays plays) {
        this.plays = plays;

        this.customer = invoice.getCustomer();
        this.performances = invoice.getPerformances();
    }

    public static StatementData createStatementData(Invoice invoice, Plays plays) {
        return new StatementData(invoice, plays);
    }

    public List<Invoice.Performance> getPerformances() {
        return performances;
    }

    public String getCustomer() {
        return customer;
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
