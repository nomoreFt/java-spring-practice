package com.my.javaspringpractice.numCalculate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PositiveNumTest {

    @Test
    @DisplayName("양수를 생성한다.")
    void createTest() {
        Assertions.assertThatCode(() -> new PositiveNum(1))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("음수일 경우 IllegalArgumentException")
    @ValueSource(ints = {0,-1, -2, -3})
    void throwTest() {
        Assertions.assertThatThrownBy(() -> new PositiveNum(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMsg.NEGATIVE_NUM.getMsg());
    }
}