package com.refactoring.chapter1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class StatementTest {
    @Test
    void statement() {
        Statement statement = new Statement();
        Assertions.assertThat(statement).isInstanceOf(Statement.class);
    }

}