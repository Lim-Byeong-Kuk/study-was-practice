package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QueryStringsTest {

    @Test
    void createTest() {
        QueryStrings queryStrings = new QueryStrings("operand1=10&operator=-&operand2=55");

        assertThat(queryStrings).isNotNull();
    }
}
