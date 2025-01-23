package com.refactoring.chapter1;

import com.refactoring.chapter1.calculator.PerformanceCalculator;
import com.refactoring.chapter1.data.Invoice;
import com.refactoring.chapter1.data.Play;
import com.refactoring.chapter1.data.Plays;

import java.util.*;
import java.util.stream.Collectors;

public class StatementData {
    private Plays plays;

    private String customer;
    private List<EnrichPerformance> performances;


    public StatementData(Invoice invoice, Plays plays) {
        this.plays = plays;

        this.customer = invoice.getCustomer();
        this.performances = invoice.getPerformances().stream()
                .map(performance -> {
                    return new EnrichPerformance(performance, playFor(performance));
                }).toList();
    }

    public static StatementData createStatementData(Invoice invoice, Plays plays) {
        return new StatementData(invoice, plays);
    }

    public List<EnrichPerformance> getPerformances() {
        return performances;
    }

    public String getCustomer() {
        return customer;
    }

    public int totalAmount() {
        return getPerformances().stream()
                .mapToInt(EnrichPerformance::getAmount)
                .sum();
    }

    public int totalVolumeCredits() {
        return getPerformances().stream()
                .mapToInt(EnrichPerformance::getVolumeCredits)
                .sum();
    }

    public Play getPlay(Invoice.Performance perf) {
        return playFor(perf);
    }

    private Play playFor(Invoice.Performance perf) {
        return plays.get(perf);
    }
}
