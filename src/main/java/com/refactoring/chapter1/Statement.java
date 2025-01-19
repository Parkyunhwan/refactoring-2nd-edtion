package com.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {
    int totalAmount = 0;
    public String statement(Invoice invoice, Plays plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", invoice.getCustomer()));

        for (Invoice.Performance perf : invoice.getPerformances()) {
            Play play = plays.get(perf.getPlayID());
            int thisAmount = 0;

            switch (play.getType()) {
                case "tragedy":
                    thisAmount = 40000;
                    if (perf.getAudience() > 30) {
                        thisAmount += 1000 * (perf.getAudience() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (perf.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                    }
                    thisAmount += 300 * perf.getAudience();
                    break;
                default:
                    throw new RuntimeException("알 수 없는 장르: " + play.getType());
            }

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
}
