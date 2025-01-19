package com.refactoring.chapter1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class StatementTest {
    @Test
    void statement_출력값_검증_테스트() {
        Jso
        Statement stat = new Statement();
        String ret = stat.statement("", "");
        Assertions.assertThat(ret).contains("총액: ");
    }

}