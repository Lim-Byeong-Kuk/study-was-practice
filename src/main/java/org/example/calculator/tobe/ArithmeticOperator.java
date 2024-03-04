package org.example.calculator.tobe;

import org.example.domain.PositiveNumber;

public interface ArithmeticOperator {

    boolean supports(String operator);

    int calculate(PositiveNumber operand1, PositiveNumber operand2);

}
