package com.refactoring.chapter1;

import java.util.ArrayList;
import java.util.List;

public class StatementData {
    private Invoice invoice;
    private Plays plays;

    public StatementData(Invoice invoice, Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Plays getPlays() {
        return plays;
    }

    public List<Invoice.Performance> getPerformances() {
        return invoice.getPerformances();
    }

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public Play getPlay(Invoice.Performance perf) {
        return playFor(perf);
    }

    private Play playFor(Invoice.Performance perf) {
        return plays.get(perf);
    }
}
