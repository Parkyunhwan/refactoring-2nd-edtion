package com.refactoring.chapter1;

import com.refactoring.chapter1.data.Invoice;
import com.refactoring.chapter1.data.Plays;

import java.util.*;

public class StatementData {
    private String customer;
    private List<EnrichPerformance> performances;


    public StatementData(Invoice invoice, Plays plays) {
        this.customer = invoice.getCustomer();
        this.performances = invoice.getPerformances().stream()
                .map(performance -> new EnrichPerformance(performance, plays.get(performance))).toList();
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
}
