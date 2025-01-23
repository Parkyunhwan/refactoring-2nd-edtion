package com.refactoring.chapter1;

import com.refactoring.chapter1.calculator.PerformanceCalculator;
import com.refactoring.chapter1.data.Invoice;
import com.refactoring.chapter1.data.Plays;

import static com.refactoring.chapter1.format.CurrencyFormat.*;

public class Statement {
    public String statement(Invoice invoice, Plays plays) {
        return renderPlainText(StatementData.createStatementData(invoice, plays));
    }

    public String htmlStatement(Invoice invoice, Plays plays) {
        return renderHtml(StatementData.createStatementData(invoice, plays));
    }

    private String renderHtml(StatementData data) {
        StringBuilder result = new StringBuilder(String.format("<h1> 청구내역 (고객명: %s) </h1>\n", data.getCustomer()));
        result.append("<table>\n");
        result.append("<tr><th> 연극 </th> <th>좌석 수</th> <th>금액</th>\n");
        for (EnrichPerformance performance : data.getPerformances()) {
            PerformanceCalculator performanceCalculator = PerformanceCalculator.createPerformanceCalculator(performance);
            result.append(String.format("<tr><td> %s: </td> <td> %s </td> <td> %s석 </td></tr>\n", performance.getPlay().getName(), usd(performanceCalculator.amountFor()), performance.getPerformance().getAudience()));
        }
        result.append("</table>\n");

        result.append(String.format("총액: %s\n", usd(data.totalAmount())));
        result.append(String.format("적립 포인트: %d점", data.totalVolumeCredits()));
        return result.toString();
    }

    private String renderPlainText(StatementData data) {
        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", data.getCustomer()));
        for (EnrichPerformance performance : data.getPerformances()) {
            PerformanceCalculator performanceCalculator = PerformanceCalculator.createPerformanceCalculator(performance);
            result.append(String.format("    %s: %s (%s석)\n", performance.getPlay().getName(), usd(performanceCalculator.amountFor()), performance.getPerformance().getAudience()));
        }

        result.append(String.format("총액: %s\n", usd(data.totalAmount())));
        result.append(String.format("적립 포인트: %s점", data.totalVolumeCredits()));

        return result.toString();
    }
}
