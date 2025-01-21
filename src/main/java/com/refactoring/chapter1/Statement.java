package com.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {
    public String statement(Invoice invoice, Plays plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", invoice.getCustomer()));

        for (Invoice.Performance perf : invoice.getPerformances()) {
            Play play = plays.get(perf.getPlayID());
            int thisAmount = amountFor(perf, play);

            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            if ("comedy".equals(play.getType())) {
                volumeCredits += (int) Math.floor((double) perf.getAudience() / 5);
            }

            result.append(String.format("    %s: %s (%s석)\n", play.getName(), numberFormat.format(thisAmount / 100.0), perf.getAudience()));
            totalAmount += thisAmount;
        }

        result.append(String.format("총액: %s\n", numberFormat.format(totalAmount / 100.0)));
        result.append(String.format("적립 포인트: %s점", volumeCredits));

        return result.toString();
    }

    int amountFor(Invoice.Performance performance, Play play) {
        int performanceAmountResult = 0;

        switch (play.getType()) {
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
                throw new RuntimeException("알 수 없는 장르: " + play.getType());
        }
        return performanceAmountResult; // 값이 바뀌는 변수값 반환
    }
}
