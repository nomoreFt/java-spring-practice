package com.my.javaspringpractice.numCalculate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class NumCalculatorTest {

    @ParameterizedTest
    @DisplayName("계산기가 사칙연산을 할 수 있다.")
    @MethodSource("operationProvider")
    void addPositive( PositiveNum num1, PositiveNum num2, String operation, int result) {
        NumCalculator calculator = new NumCalculator();
        calculator.calculate(num1, num2, operation);
        calculator.calculate2(num1, num2, operation);

        assertThatCode(() -> new NumCalculator())
                .doesNotThrowAnyException();

        assertThat(calculator.calculate(num1, num2, operation))
                .isEqualTo(result);

        assertThat(calculator.calculate2(num1, num2, operation))
                .isEqualTo(result);
    }

    @DisplayName("사칙연산 이외에 기호가 들어올 경우 IllegalArgumentException")
    @Test
    void throwTest() {
        NumCalculator calculator = new NumCalculator();
        PositiveNum num1 = new PositiveNum(1);
        PositiveNum num2 = new PositiveNum(2);
        String operation = "%";

        assertThatThrownBy(() -> calculator.calculate(num1, num2, operation))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMsg.INVALID_OPERATOR.getMsg());
    }

    private static Stream<Arguments> operationProvider() {
        return Stream.of(
                Arguments.of(new PositiveNum(1), new PositiveNum(2), "+", 3),
                Arguments.of(new PositiveNum(2), new PositiveNum(3), "-", -1),
                Arguments.of(new PositiveNum(3), new PositiveNum(4), "*", 12),
                Arguments.of(new PositiveNum(4), new PositiveNum(2), "/", 2)
        );
    }

}
