package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class RequestLineTest {

//    @Test
//    void create() {
//        RequestLine requestLine = new RequestLine("GET /calculate?operand1=10&operator=-&operand2=55 HTTP/1.1");
//
//        assertThat(requestLine).isNotNull();
//        assertThat(requestLine).isEqualTo(new RequestLine("GET", "/calculate", "operand1=10&operator=-&operand2=55"));
//    }

    @Test
    void requestLineCreateTest() {
        RequestLine requestLine = new RequestLine("GET /calculate?operand1=10&operator=-&operand2=55 HTTP/1.1");

        assertThat(requestLine.getMethod()).isEqualTo("GET");
        assertThat(requestLine.getUrlPath()).isEqualTo("/calculate");
//        assertThat(requestLine.getQueryStrings()).isEqualTo(new QueryStrings("operand1=10&operator=-&operand2=55"));

        QueryStrings queryStrings = requestLine.getQueryStrings();

        assertThat(queryStrings.getValue("operand1")).isEqualTo("10");
        assertThat(queryStrings.getValue("operator")).isEqualTo("-");
        assertThat(queryStrings.getValue("operand2")).isEqualTo("55");
    }
}
