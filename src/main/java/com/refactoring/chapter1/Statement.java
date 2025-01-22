package com.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {
    public String statement(Invoice invoice, Plays plays) {
        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", invoice.getCustomer()));
        for (Invoice.Performance perf : invoice.getPerformances()) {
            result.append(String.format("    %s: %s (%s석)\n", playFor(plays, perf).getName(), usd(amountFor(perf, plays)), perf.getAudience()));
        }

        result.append(String.format("총액: %s\n", usd(totalAmount(invoice, plays))));
        result.append(String.format("적립 포인트: %s점", totalVolumeCredits(invoice, plays)));

        return result.toString();
    }

    private int totalAmount(Invoice invoice, Plays plays) {
        int result = 0;
        for (Invoice.Performance perf : invoice.getPerformances()) {
            result += amountFor(perf, plays);
        }
        return result;
    }

    private int totalVolumeCredits(Invoice invoice, Plays plays) {
        int result = 0;
        for (Invoice.Performance perf : invoice.getPerformances()) {
            result += volumeCreditsFor(plays, perf);
        }
        return result;
    }

    private static String usd(int amount) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(amount / 100.0);
    }

    private int volumeCreditsFor(Plays plays, Invoice.Performance perf) {
        int result = Math.max(perf.getAudience() - 30, 0);
        if ("comedy".equals(playFor(plays, perf).getType())) {
            result += (int) Math.floor((double) perf.getAudience() / 5);
        }
        return result;
    }

    private Play playFor(Plays plays, Invoice.Performance perf) {
        return plays.get(perf.getPlayID());
    }

    int amountFor(Invoice.Performance performance, Plays plays) {
        int performanceAmountResult = 0;

        switch (playFor(plays, performance).getType()) {
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
                throw new RuntimeException("알 수 없는 장르: " + playFor(plays, performance).getType());
        }
        return performanceAmountResult; // 값이 바뀌는 변수값 반환
    }
}
