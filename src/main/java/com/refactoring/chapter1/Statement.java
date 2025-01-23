package com.refactoring.chapter1;

import static com.refactoring.chapter1.CurrencyFormat.*;

public class Statement {
    public String statement(Invoice invoice, Plays plays) {
        return renderPlainText(createStatementData(invoice, plays));
    }

    public String htmlStatement(Invoice invoice, Plays plays) {
        return renderPlainText(createStatementData(invoice, plays));
    }

    private String renderHtml(StatementData statementData) {
        StringBuilder result = new StringBuilder(String.format("<h1> 청구내역 (고객명: %s)\n </h1>", statementData.getCustomer()));
        result.append("<table> \n");
        result.append("<tr><th> 연극 </th> <th>좌석 수</th> <th>금액</th>");
        for (Invoice.Performance performance : statementData.getPerformances()) {
            result.append(String.format("<tr><td> %s: </td> <td> $%s </td> <td> %s석 </td></tr>\n", statementData.getPlay(performance).getName(), usd(statementData.amountFor(performance)), performance.getAudience()));
        }
        result.append("</table>\n");

        result.append(String.format("총액: $%s\n", usd(statementData.totalAmount())));
        result.append(String.format("적립 포인트: %d점", statementData.totalVolumeCredits()));
        return result.toString();
    }

    private String renderPlainText(StatementData data) {
        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", data.getCustomer()));
        for (Invoice.Performance perf : data.getPerformances()) {
            result.append(String.format("    %s: %s (%s석)\n", data.getPlay(perf).getName(), usd(data.amountFor(perf)), perf.getAudience()));
        }

        result.append(String.format("총액: %s\n", usd(data.totalAmount())));
        result.append(String.format("적립 포인트: %s점", data.totalVolumeCredits()));

        return result.toString();
    }

    private StatementData createStatementData(Invoice invoice, Plays plays) {
        return new StatementData(invoice, plays);
    }
}
