package com.refactoring.chapter1;

public class Statement {
    public String statement(Invoice invoice, Plays plays) {
        return renderPlainText(createStatementData(invoice, plays));
    }

    private StatementData createStatementData(Invoice invoice, Plays plays) {
        return new StatementData(invoice, plays);
    }

    private String renderPlainText(StatementData data) {
        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", data.getCustomer()));
        for (Invoice.Performance perf : data.getPerformances()) {
            result.append(String.format("    %s: %s (%s석)\n", data.getPlay(perf).getName(), CurrencyFormat.usd(data.amountFor(perf)), perf.getAudience()));
        }

        result.append(String.format("총액: %s\n", CurrencyFormat.usd(data.totalAmount())));
        result.append(String.format("적립 포인트: %s점", data.totalVolumeCredits()));

        return result.toString();
    }
}
