package com.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {
    public String statement(Invoice invoice, Plays plays) {
        StatementData statementData = new StatementData(invoice, plays);
        return renderPlainText(statementData);
    }

    private String renderPlainText(StatementData data) {
        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", data.getCustomer()));
        for (Invoice.Performance perf : data.getPerformances()) {
            result.append(String.format("    %s: %s (%s석)\n", data.getPlay(perf).getName(), usd(amountFor(perf, data)), perf.getAudience()));
        }

        result.append(String.format("총액: %s\n", usd(totalAmount(data))));
        result.append(String.format("적립 포인트: %s점", totalVolumeCredits(data)));

        return result.toString();
    }

    private int totalAmount(StatementData data) {
        int result = 0;
        for (Invoice.Performance perf : data.getPerformances()) {
            result += amountFor(perf, data);
        }
        return result;
    }

    private int totalVolumeCredits(StatementData data) {
        int result = 0;
        for (Invoice.Performance perf : data.getPerformances()) {
            result += volumeCreditsFor(data, perf);
        }
        return result;
    }

    private static String usd(int amount) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(amount / 100.0);
    }

    private int volumeCreditsFor(StatementData statementData, Invoice.Performance perf) {
        int result = Math.max(perf.getAudience() - 30, 0);
        if ("comedy".equals(statementData.getPlay(perf).getType())) {
            result += (int) Math.floor((double) perf.getAudience() / 5);
        }
        return result;
    }



    int amountFor(Invoice.Performance performance, StatementData data) {
        int performanceAmountResult = 0;

        switch (data.getPlay(performance).getType()) {
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
                throw new RuntimeException("알 수 없는 장르: " + data.getPlay(performance).getType());
        }
        return performanceAmountResult; // 값이 바뀌는 변수값 반환
    }
}
