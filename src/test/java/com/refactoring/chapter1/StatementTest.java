package com.refactoring.chapter1;

import com.refactoring.chapter1.calculator.PerformanceCalculator;
import com.refactoring.chapter1.data.Invoice;
import com.refactoring.chapter1.data.Play;
import com.refactoring.chapter1.data.Plays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.refactoring.chapter1.format.CurrencyFormat.usd;


class StatementTest {
    private static Invoice invoice;
    private static Plays plays;

    @BeforeAll
    static void setUp() {
        invoice = new Invoice(
                "BigCo",
                List.of(
                        new Invoice.Performance("hamlet", 55),
                        new Invoice.Performance("as-like", 35),
                        new Invoice.Performance("othello", 40)
                )
        );
        plays = new Plays(
                Map.of(
                        "hamlet", new Play("Hamlet", "tragedy"),
                        "as-like", new Play("As You Like it", "comedy"),
                        "othello", new Play("Othello", "tragedy")
                )
        );
    }


    @Test
    void statement_출력값_검증_테스트() {
        Statement stat = new Statement();
        String ret = stat.statement(invoice, plays);
        String answer = """
                청구내역 (고객명: BigCo)
                    Hamlet: $650.00 (55석)
                    As You Like it: $580.00 (35석)
                    Othello: $500.00 (40석)
                총액: $1,730.00
                적립 포인트: 47점""";
        Assertions.assertThat(ret).isEqualTo(answer);
    }

    @Test
    void htmlStatement_출력값_검증_테스트() {
        Statement stat = new Statement();
        String ret = stat.htmlStatement(invoice, plays);
        String answer = """
                <h1> 청구내역 (고객명: BigCo) </h1>
                <table>
                <tr><th> 연극 </th> <th>좌석 수</th> <th>금액</th>
                <tr><td> Hamlet: </td> <td> $650.00 </td> <td> 55석 </td></tr>
                <tr><td> As You Like it: </td> <td> $580.00 </td> <td> 35석 </td></tr>
                <tr><td> Othello: </td> <td> $500.00 </td> <td> 40석 </td></tr>
                </table>
                총액: $1,730.00
                적립 포인트: 47점""";
        Assertions.assertThat(ret).isEqualTo(answer);
    }

    @Test
    void amountFor_계산_로직_테스트() {
        Statement stat = new Statement();
        StatementData data = new StatementData(invoice, plays);
        List<Integer> amountResults = new ArrayList<>();
        for (EnrichPerformance performance : data.getPerformances()) {
            PerformanceCalculator performanceCalculator = PerformanceCalculator.createPerformanceCalculator(performance.getPerformance(), performance.getPlay());
            int amountFor = performanceCalculator.amountFor();
            amountResults.add(amountFor);
        }

        List<Integer> expectedResults = List.of(65000, 58000, 50000);
        Assertions.assertThat(amountResults).isEqualTo(expectedResults);
    }

}