package com.refactoring.chapter1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


class StatementTest {
    @Test
    void statement_출력값_검증_테스트() {
        Invoice invoice = new Invoice(
                "BigCo",
                List.of(
                        new Invoice.Performance("hamlet", 55),
                        new Invoice.Performance("as-like", 35),
                        new Invoice.Performance("othello", 40)
                )
        );
        Plays plays = new Plays(
                Map.of(
                        "hamlet", new Play("Hamlet", "tragedy"),
                        "as-like", new Play("As You Like it", "comedy"),
                        "othello", new Play("Othello", "tragedy")
                )
        );

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

}