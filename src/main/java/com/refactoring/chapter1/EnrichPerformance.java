package com.refactoring.chapter1;

import com.refactoring.chapter1.data.Invoice;
import com.refactoring.chapter1.data.Play;

public class EnrichPerformance {
    private Invoice.Performance performance;
    private Play play;

    public EnrichPerformance(Invoice.Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public Invoice.Performance getPerformance() {
        return performance;
    }

    public Play getPlay() {
        return play;
    }
}
