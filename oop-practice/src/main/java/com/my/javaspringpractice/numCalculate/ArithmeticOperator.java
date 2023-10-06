package com.my.javaspringpractice.numCalculate;

import java.util.Arrays;

public enum ArithmeticOperator {
    ADDITION("+") {
        @Override
        public int arithmeticCalculate(PositiveNum num1, PositiveNum num2) {
            return num1.toInt() + num2.toInt();
        }
    }, SUBTRACTION("-") {
        @Override
        public int arithmeticCalculate(PositiveNum num1, PositiveNum num2) {
            return num1.toInt() - num2.toInt();
        }
    }, MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculate(PositiveNum num1, PositiveNum num2) {
            return num1.toInt() * num2.toInt();
        }
    }, DIVISION("/") {
        @Override
        public int arithmeticCalculate(PositiveNum num1, PositiveNum num2) {
            if(num2.isZero()) {
                throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
            }
            return num1.toInt() / num2.toInt();
        }
    };

    private final String operator;

    ArithmeticOperator(String operator) {
        this.operator = operator;
    }

    public static int calculate(PositiveNum num1, PositiveNum num2, String operator) {
        ArithmeticOperator targetOperator = Arrays.stream(values())
                .filter(arithmeticOperator -> arithmeticOperator.operator.equals(operator))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMsg.INVALID_OPERATOR.getMsg()));

        return targetOperator.arithmeticCalculate(num1, num2);
    }

    protected abstract int arithmeticCalculate(final PositiveNum num1, final PositiveNum num2);

}
