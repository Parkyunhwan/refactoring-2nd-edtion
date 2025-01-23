package com.refactoring.chapter1;

import com.refactoring.chapter1.calculator.PerformanceCalculator;
import com.refactoring.chapter1.data.Invoice;
import com.refactoring.chapter1.data.Play;

public class EnrichPerformance {
    private Invoice.Performance performance;
    private Play play;
    private int amount;
    private int volumeCredits;

    public EnrichPerformance(Invoice.Performance performance, Play play) {
        PerformanceCalculator calculator = PerformanceCalculator.createPerformanceCalculator(performance, play);
        this.performance = performance;
        this.play = play;
        this.amount = calculator.amountFor();
        this.volumeCredits = calculator.volumeCreditsFor();
    }

    public Invoice.Performance getPerformance() {
        return performance;
    }

    public Play getPlay() {
        return play;
    }

    public int getAmount() {
        return amount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }

    public String getPlayName() {
        return play.getName();
    }

    public int getPerformanceAudience() {
        return performance.getAudience();
    }
}
