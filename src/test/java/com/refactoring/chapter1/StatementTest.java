package com.refactoring.chapter1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    void amountFor_계산_로직_테스트() {
        Statement stat = new Statement();
        List<Integer> amountResults = new ArrayList<>();
        for (Invoice.Performance performance : invoice.getPerformances()) {
            int amountFor = stat.amountFor(performance, plays);
            amountResults.add(amountFor);
        }

        List<Integer> expectedResults = List.of(65000, 58000, 50000);
        Assertions.assertThat(amountResults).isEqualTo(expectedResults);
    }

}