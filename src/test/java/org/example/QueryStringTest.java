package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QueryStringTest {

    // operand1=11
    @Test
    void createTest() {
        QueryString queryString = new QueryString("operand1","11");

        assertThat(queryString).isNotNull();
    }
}
